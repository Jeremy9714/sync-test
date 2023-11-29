package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.ResourceFileDao;
import com.inspur.dsp.open.sync.entity.ResourceFile;
import com.inspur.dsp.open.sync.service.ResourceFileService;
import com.inspur.dsp.open.sync.share.SaveFileResourceParam;
import com.inspur.dsp.open.sync.util.DubboService;
import com.inspur.dsp.open.sync.util.JsonUtil;
import com.inspur.dsp.open.sync.util.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<ResourceFile> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(ResourceFile resourceFile : resultList){
                String operateType = resourceFile.getOperateType();
                switch (operateType){
                    case "I":
                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "U":
                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "D":
                        deleteResource(resourceFile.getResourceId());
                        break;
                    default:
                        throw new RuntimeException("库表资源，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_FILE_KEY, latestOperationDate);
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
     * @param resourceFile
     * @return
     */
    private SaveFileResourceParam dealSaveFileParams(ResourceFile resourceFile){
        SaveFileResourceParam saveFileResourceParam = new SaveFileResourceParam();
        saveFileResourceParam.setId(resourceFile.getResourceId());
        saveFileResourceParam.setResName(resourceFile.getResourceName());
        saveFileResourceParam.setResDesc(resourceFile.getRemark());
        saveFileResourceParam.setCataId(resourceFile.getCataId());
        // TODO 需要调用开放平台接口查询
        saveFileResourceParam.setCataName(null);
        saveFileResourceParam.setOrgId(null);
        saveFileResourceParam.setOrgName(null);
        saveFileResourceParam.setRegionCode(null);
        // TODO 文档显示传null
        saveFileResourceParam.setShareType(null);
        // TODO 根据目录下行表的开放类型open_type
        saveFileResourceParam.setOpenType(null);
        // TODO 根据目录下行表的update_cycle
        saveFileResourceParam.setUpdateCycle(null);
        // TODO 根据中间程序自动生成
        saveFileResourceParam.setCreatorId(null);
        saveFileResourceParam.setCreatorName(null);

        // TODO 先确认resource_file和resource_file_attachinfo表的主外键关系，根据attachinfo里的信息先下载文件，再上传到开放平台
        saveFileResourceParam.setFileName(null);
        saveFileResourceParam.setFileSize(null);
        saveFileResourceParam.setFilePath(null);
        saveFileResourceParam.setFileFormat(null);

        if(ValidationUtil.validate(saveFileResourceParam)){
            return saveFileResourceParam;
        }else{
            log.error("保存文件资源，请求参数存在必填项为空，需检查参数:{}", saveFileResourceParam.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

    /**
     * 调用，保存文件资源
     * @param saveFileResourceParam
     * @return
     */
    private void saveFileResource(SaveFileResourceParam saveFileResourceParam){
        Map map = JsonUtil.obj2pojo(saveFileResourceParam, Map.class);
        Map<String, Object> result = dubboService.saveFileResource(map);
        String code = (String) result.get("code");
        if(code.equals("200")){
            String data = (String) result.get("data");
            log.info("保存文件资源成功。");
        }else{
            String error = (String) result.get("error");
            log.error("保存文件资源，接口调用失败。错误说明:{}", error);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 调用，删除文件资源
     * @param id
     */
    private void deleteResource(String id){
        Map<String, Object> result = dubboService.deleteResource(id);
        String code = (String) result.get("code");
        if(code.equals("200")){
            log.info("删除文件资源成功");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        }else{
            String error = (String) result.get("error");
            log.error("删除文件资源，接口调用失败。错误说明:{}", error);
            throw new RuntimeException("接口调用失败");
        }
    }

}
