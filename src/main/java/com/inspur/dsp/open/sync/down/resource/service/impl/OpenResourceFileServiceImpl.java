package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.OSSObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.common.upload.FileStoreFactory;
import com.inspur.dsp.open.common.upload.RCBasedFileStore;
import com.inspur.dsp.open.sync.down.resource.api.OpenApiService;
import com.inspur.dsp.open.sync.down.resource.bean.OpenResourceFile;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFile;
import com.inspur.dsp.open.sync.down.resource.dao.OpenResourceFileDao;
import com.inspur.dsp.open.sync.down.resource.dto.OpenResourceFileDto;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceFileDto;
import com.inspur.dsp.open.sync.down.resource.service.OpenResourceFileService;
import com.inspur.dsp.open.sync.util.DSPBeanUtils;
import com.inspur.dsp.open.sync.util.DubboService;
import com.inspur.dsp.open.sync.util.ServiceConstant;
import com.inspur.dsp.open.sync.util.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/15 10:44
 * @Version: 1.0
 */
@Service
public class OpenResourceFileServiceImpl extends ServiceImpl<OpenResourceFileDao, OpenResourceFile> implements OpenResourceFileService {
    private static final Logger log = LoggerFactory.getLogger(ResourceFileServiceImpl.class);

    @Autowired
    private OpenResourceFileDao openResourceFileDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;

    @Autowired
    private FileStoreFactory fileStoreFactory;

    @Autowired
    private OpenApiService openApiService;

    @Value("${oss.end-point}")
    private String endPoint;

    @Value("${oss.bucket-name}")
    private String bucketName;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.secretAccessKey}")
    private String secretAccessKey;

    @Override
    @Transactional
    public boolean syncOpenResourceFile() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_FILE_KEY);
            String latestOperationDate = openResourceFileDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增文件资源下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<OpenResourceFile> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增文件资源下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);

            }
            wrapper.orderBy("operate_date").last("limit 0,20");
            List<OpenResourceFile> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for (OpenResourceFile openResourceFile : resultList) {
                String operateType = openResourceFile.getOperateType();
                switch (operateType) {
                    case "I":
                        saveOpenResourceFile(transformFileToMap(openResourceFile));
                        break;
                    case "U":
                        OpenResourceFileDto openResourceFileDto = new OpenResourceFileDto();
                        DSPBeanUtils.copyProperties(openResourceFile, openResourceFileDto);
                        String docId = uploadResourceAttachment(openResourceFile.getFilePath(), openResourceFile.getFileName());
                        openResourceFileDto.setIdInRc(docId);
                        openApiService.updateOpenFileResource(openResourceFileDto);
//                        updateOpenResourceFile(transformFileToMap(openResourceFile));
                        break;
                    case "D":
                        openApiService.deleteOpenFileResource(openResourceFile.getFileId(), openResourceFile.getCataId());
//                        deleteOpenResourceFile(openResourceFile.getFileId());
                        break;
                    default:
                        throw new RuntimeException("文件资源，无此操作类型");
                }
                String currentOperateDate = sdf.format(openResourceFile.getOperateDate());
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
     * @param openResourceFile
     * @return
     */
    private Map<String, Object> transformFileToMap(OpenResourceFile openResourceFile) throws Exception {
        OpenResourceFileDto openResourceFileDto = new OpenResourceFileDto();
        DSPBeanUtils.copyProperties(openResourceFile, openResourceFileDto);

        String docId = uploadResourceAttachment(openResourceFile.getFilePath(), openResourceFile.getFileName());
        openResourceFileDto.setIdInRc(docId);

//        if (!ValidationUtil.validate(openResourceFileDto)) {
//            log.error("保存文件资源，请求参数存在必填项为空，需检查参数:{}", openResourceFileDto);
//            throw new RuntimeException("请求参数不合规");
//        }

        Map<String, Object> fileMap = DSPBeanUtils.beanToMap(openResourceFileDto);
        return fileMap;
    }

    /**
     * 开放文件资源推送
     *
     * @param fileMap
     */
    private void saveOpenResourceFile(Map<String, Object> fileMap) {
        Result<Boolean> result = dubboService.saveOpenFileResource(fileMap);
        Boolean flag = result.getObject();
        if (flag) {
            log.info("保存文件资源成功!");
        } else {
            log.error("保存文件资源，接口调用失败!");
            throw new RuntimeException("接口调用失败");
        }
    }

//    /**
//     * 更新文件资源 dubbo
//     *
//     * @param fileMap
//     */
//    private void updateOpenResourceFile(Map<String,Object> fileMap){
//        Result<Boolean> result = dubboService.updateOpenResourceFile(fileMap);
//        Boolean flag = result.getObject();
//        if (flag) {
//            log.info("更新文件资源成功!");
//        } else {
//            log.error("更新文件资源，接口调用失败!");
//            throw new RuntimeException("接口调用失败");
//        }
//    }

//    /**
//     * 删除文件资源 dubbo
//     *
//     * @param fileId
//     */
//    private void deleteOpenResourceFile(String fileId){
//        Result<Boolean> result = dubboService.deleteOpenResourceFile(fileId);
//        Boolean flag = result.getObject();
//        if (flag) {
//            log.info("下架文件资源成功!");
//        } else {
//            log.error("下架文件资源，接口调用失败!");
//            throw new RuntimeException("接口调用失败");
//        }
//    }

    private String uploadResourceAttachment(String filePath, String fileName) throws Exception {
        RCBasedFileStore fileStore = (RCBasedFileStore) fileStoreFactory.getFileStore();

        log.info("endpoint: {}", endPoint);

        // TODO 第三方文件下载
//        EnvironmentVariableCredentialsProvider credentialProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        OSS ossClient = new OSSClientBuilder().build(endPoint, credentialProvider);
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);

        String tempFilePath = "";
        String osType = System.getProperty("os.name");
        if (osType.contains("Windows")) {
            tempFilePath = "tmp" + File.separator + fileName;
        } else {
            tempFilePath = "/tmp" + File.separator + fileName;
        }
        File tmpFile = new File(tempFilePath);
        log.info("临时文件路径: {}", tempFilePath);
        if (!tmpFile.exists()) {
            tmpFile.createNewFile();
        }

        try {
            log.info("bucketName: {}", bucketName);

            String[] split = filePath.split(endPoint);
            if (split.length > 1) {
                filePath = split[1].substring(1);
            } else {
                filePath = split[0];
            }
            log.info("objectName: {}", filePath);
            OSSObject ossObject = ossClient.getObject(bucketName, filePath);
            InputStream content = ossObject.getObjectContent();
            if (content != null) {
                OutputStream os = new FileOutputStream(tmpFile);
                byte[] bytes = new byte[1024];
                int bytesRead;
                while ((bytesRead = content.read(bytes)) != -1) {
                    os.write(bytes, 0, bytesRead);
                }
                String docId = fileStore.putFile(tmpFile.getName(), tmpFile, null);

                if (StringUtils.isBlank(docId)) {
                    log.error("rc上传失败: {}", fileName);
                    throw new Exception("rc上传失败");
                }
                log.info("rc上传成功！doc_id: {}", docId);

                os.close();
                content.close();
                return docId;
            } else {
                log.error("第三方文件下载失败, bucketName: {}  objectName: {}", bucketName, filePath);
                throw new RuntimeException("第三方文件下载失败");
            }
        } catch (OSSException oe) {
            log.error("oss下载失败!");
            log.error("Error Message: {}", oe.getErrorMessage());
            log.error("Error Code: {}", oe.getErrorCode());
            log.error("Request ID: {}", oe.getRequestId());
            log.error("Host ID: {}", oe.getHostId());
            throw new Exception(oe);
        } catch (Exception e) {
            log.error("上传文件失败");
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }

            // 删除临时文件
            if (tmpFile.exists()) {
                if (tmpFile.delete()) {
                    log.info("临时文件删除成功");
                }
            }
        }
    }
}
