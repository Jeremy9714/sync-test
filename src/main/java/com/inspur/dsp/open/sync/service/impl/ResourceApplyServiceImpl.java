package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.ResourceApplyDao;
import com.inspur.dsp.open.sync.down.service.OpenApiReqService;
import com.inspur.dsp.open.sync.entity.ResourceApply;
import com.inspur.dsp.open.sync.service.ResourceApplyService;
import com.inspur.dsp.open.sync.up.bean.ResourceApplyParam;
import com.inspur.dsp.open.sync.up.service.ShareApiReqService;
import com.inspur.dsp.open.sync.util.DubboService;
import com.inspur.dsp.open.sync.util.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ResourceApplyServiceImpl extends ServiceImpl<ResourceApplyDao, ResourceApply> implements ResourceApplyService {
    private static final Logger log = LoggerFactory.getLogger(ResourceApplyServiceImpl.class);

    @Autowired
    private ResourceApplyDao resourceApplyDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;

    @Autowired
    private ShareApiReqService shareApiReqService;

    @Autowired
    private OpenApiReqService openApiReqService;

    @Transactional
    @Override
    public boolean syncResourceApply() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_APPLY_KEY);
            String latestOperationDate = resourceApplyDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增资源申请下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceApply> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增资源申请下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<ResourceApply> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(ResourceApply resourceFile : resultList){
                String operateType = resourceFile.getOperateType();
                switch (operateType){
                    case "I":
//                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "U":
//                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "D":
//                        deleteResource(resourceFile.getResourceId());
                        break;
                    default:
                        throw new RuntimeException("资源申请，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_APPLY_KEY, latestOperationDate);
            }

            return true;
        } catch (Exception e) {
            log.error("--------同步异常----资源申请下行表", e);
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 同步申请表的增量数据
     */
    public void syncResourceApplyIncrement(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_APPLY_INCREMENT_KEY);
            if (StringUtils.isBlank(lastSyncDate)) {
                lastSyncDate = sdf.format(new Date());
            }
            Result<Map<String, Object>> result = dubboService.getResourceApplyByPage(lastSyncDate, 1, 10);
            int code = result.getCode();
            if(code != 0){
                String msg = result.getMessage();
                log.error("申请表的增量数据查询失败:{}", msg);
                return;
            }
            Map<String, Object> resultMap = result.getObject();
            int total = (int) resultMap.get("total");
            if(total == 0){
                lastSyncDate = sdf.format(new Date());
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_APPLY_INCREMENT_KEY, lastSyncDate);
                log.debug("同步申请表，无，增量数据");
                return;
            }
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultMap.get("records");
            for(Map<String, Object> map : resultList){
                ResourceApplyParam resourceApplyParam = dealResourceApplyParam(map);
                if(ValidationUtil.validate(resourceApplyParam)){
                    boolean flag = shareApiReqService.resourceApply(resourceApplyParam);
                    if(flag){
                        redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_APPLY_INCREMENT_KEY, (String)map.get("applyTime"));
                    }else{
                        log.error("调共享平台接口，请求失败");
                        throw new RuntimeException("调共享平台接口，请求失败");
                    }
                }else{
                    log.error("调共享平台接口，请求参数存在必填项为空，需检查参数:{}", resourceApplyParam.toString());
                    throw new RuntimeException("请求参数不合规");
                }
            }
        }catch (Exception e){
            log.error("申请表的增量数据，同步失败", e);
        }
    }

    private ResourceApplyParam dealResourceApplyParam(Map<String, Object> map){
        ResourceApplyParam resourceApplyParam = new ResourceApplyParam();
        resourceApplyParam.setResource_id((String)map.get("resourceId"));
        resourceApplyParam.setApply_user((String)map.get("applyUserName"));
        resourceApplyParam.setContact((String)map.get("applyUserContactPhone"));
        resourceApplyParam.setCredit_code("1");
        resourceApplyParam.setEnd_date((Date)map.get("applyEndTime"));
        resourceApplyParam.setUse_scope("1");
        resourceApplyParam.setUse_desc("1");
        // 开放平台下载附件，上传到共享平台，然后获取attach信息
        JSONArray attachList = new JSONArray();
        String attachmentStr = (String)map.get("attachment");
        JSONArray attachmentList = JSONArray.parseArray(attachmentStr);
        for(int i=0;i<attachmentList.size();i++){
            JSONObject attachmentJson = attachmentList.getJSONObject(i);
            String filename = attachmentJson.getString("name");
            String docId = attachmentJson.getString("filePath");
            String cascadeguid = dealAttach(docId, filename);
            JSONObject attach = new JSONObject();
            attach.put("fileid", cascadeguid);
            attach.put("filename", filename);
            attachList.add(attach);
        }
        resourceApplyParam.setAttach(attachList);
        if(map.get("resourceType").equals("file")){
            resourceApplyParam.setApply_type("3");
        }else if(map.get("resourceType").equals("service")){
            resourceApplyParam.setApply_type("4");
        }else{
            resourceApplyParam.setApply_type("1");
        }
        resourceApplyParam.setFieldid("1");
        resourceApplyParam.setApply_id(null);
        resourceApplyParam.setSituation("1");
        resourceApplyParam.setExtranetIp("1");
        resourceApplyParam.setOtherRequire("1");
        resourceApplyParam.setDeployAddress("1");
        resourceApplyParam.setEmergencyLevel("1");
        resourceApplyParam.setEmergencyReason("1");
        resourceApplyParam.setApplyBasis((String)map.get("applyReason"));
        return resourceApplyParam;
    }

    /**
     * 从开放平台下载，上传到共享平台上
     * @param doc_id
     */
    private String dealAttach(String doc_id, String filename){
        ResponseEntity<byte[]> result = openApiReqService.fileDownload(doc_id);
        byte[] base64Bytes = Base64.encodeBase64(result.getBody());
        String base64Str = new String(base64Bytes);

        String cascadeguid = shareApiReqService.fileupload(filename, base64Str);
        if(cascadeguid == null){
            log.error("调共享平台接口，请求失败");
            throw new RuntimeException("调共享平台接口，请求失败");
        }
        return cascadeguid;
    }

}
