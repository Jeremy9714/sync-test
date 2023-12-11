package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.common.file.FileStoreFactory;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFile;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceFileDao;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceFileDto;
import com.inspur.dsp.open.sync.down.resource.service.ResourceFileService;
import com.inspur.dsp.open.sync.util.*;
import com.inspur.dsp.open.upload.RCBasedFileStore;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ResourceFileServiceImpl extends ServiceImpl<ResourceFileDao, ResourceFile> implements ResourceFileService {
    private static final Logger log = LoggerFactory.getLogger(ResourceFileServiceImpl.class);

    @Autowired
    private ResourceFileDao resourceFileDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;

    @Autowired
    private FileStoreFactory fileStoreFactory;

    @Transactional
    @Override
    public boolean syncResourceFile() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_FILE_KEY);
            String latestOperationDate = resourceFileDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增文件资源下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceFile> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增文件资源下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);

            }
            wrapper.orderBy("operate_date").last("limit 0,100");
            List<ResourceFile> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for (ResourceFile resourceFile : resultList) {
                String operateType = resourceFile.getOperateType();
                switch (operateType) {
                    case "I":
                        saveResourceFile(transformFileToMap(resourceFile));
                        break;
                    case "U":
                        saveResourceFile(transformFileToMap(resourceFile));
                        break;
                    case "D":
                        deleteResourceFile(resourceFile.getId());
                        break;
                    default:
                        throw new RuntimeException("库表资源，无此操作类型");
                }
                String currentOperateDate = sdf.format(resourceFile.getOperateDate());
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_FILE_KEY, currentOperateDate);
            }

            return true;
        } catch (Exception e) {
            log.error("--------同步异常----文件资源下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 组装，保存文件资源的入参
     *
     * @param resourceFile
     * @return
     */
    private Map<String, Object> transformFileToMap(ResourceFile resourceFile) throws RuntimeException {
        ResourceFileDto resourceFileDto = new ResourceFileDto();
        DSPBeanUtils.copyProperties(resourceFile, resourceFileDto);

        String docId = uploadResourceAttachment(resourceFile.getFilePath(),resourceFile.getFileName());
        resourceFileDto.setFilePath(docId);

        if (!ValidationUtil.validate(resourceFileDto)) {
            log.error("保存文件资源，请求参数存在必填项为空，需检查参数:{}", resourceFileDto);
            throw new RuntimeException("请求参数不合规");
        }

        Map<String, Object> fileMap = DSPBeanUtils.beanToMap(resourceFileDto);
        return fileMap;
    }

    /**
     * 调用，保存文件资源
     *
     * @param fileMap
     * @return
     */
    private void saveResourceFile(Map<String, Object> fileMap) {
        Map<String, Object> result = dubboService.saveFileResource(fileMap);
        String code = (String) result.get("code");
        if (code.equals("200")) {
            String data = (String) result.get("data");
            log.info("保存文件资源成功。");
        } else {
            String error = (String) result.get("error");
            log.error("保存文件资源，接口调用失败。错误说明:{}", error);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 调用，删除文件资源
     *
     * @param id
     */
    private void deleteResourceFile(String id) {
        Map<String, Object> result = dubboService.deleteResource(id);
        String code = (String) result.get("code");
        if (code.equals("200")) {
            log.info("删除文件资源成功");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        } else {
            String error = (String) result.get("error");
            log.error("删除文件资源，接口调用失败。错误说明:{}", error);
            throw new RuntimeException("接口调用失败");
        }
    }

    private String uploadResourceAttachment(String thirdPartyFilePath, String thirdPartyFileName) {
        RCBasedFileStore fileStore = (RCBasedFileStore) fileStoreFactory.getFileStore();
        // TODO 第三方文件下载

        String fileContent = ""; //文件内容
        if (fileContent == null) {
            log.error("下载第三方文件资源失败！");
            throw new RuntimeException("下载第三方文件资源失败！");
        }
        byte[] bytes = Base64.decodeBase64(fileContent.getBytes());
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);) {
            String docId = fileStore.putFile(thirdPartyFileName, bis, null);
            return docId;
        } catch (Exception e) {
            log.error("文件上传开放平台失败！");
            throw new RuntimeException(e);
        }
    }

}


