package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.ResourceOperationserviceDao;
import com.inspur.dsp.open.sync.entity.ResourceOperationservice;
import com.inspur.dsp.open.sync.service.ResourceOperationserviceService;
import com.inspur.dsp.open.sync.share.AddApiResourceParam;
import com.inspur.dsp.open.sync.share.ResourceService;
import com.inspur.dsp.open.sync.util.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ResourceOperationserviceServiceImpl extends ServiceImpl<ResourceOperationserviceDao, ResourceOperationservice> implements ResourceOperationserviceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceOperationserviceServiceImpl.class);

    @Value("${share.apiResource.url}")
    private String apiResourceUrl;

    @Autowired
    private ResourceOperationserviceDao resourceOperationserviceDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    @Override
    public boolean syncResourceOperationservice() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_OPERATION_SERVICE_KEY);
            String latestOperationDate = resourceOperationserviceDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增接口资源下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceOperationservice> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增接口资源下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<ResourceOperationservice> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(ResourceOperationservice resourceOperationservice : resultList){
                String operateType = resourceOperationservice.getOperateType();
                switch (operateType){
                    case "I":
                        addApiResource(dealAddApiParams(resourceOperationservice));
                        break;
                    case "U":
                        addApiResource(dealAddApiParams(resourceOperationservice));
                        break;
                    case "D":
                        deleteApiResource(resourceOperationservice.getServiceId(), resourceOperationservice.getCataId());
                        break;
                    default:
                        throw new RuntimeException("接口资源，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_OPERATION_SERVICE_KEY, latestOperationDate);
            }


            return true;
        } catch (Exception e) {
            log.error("--------同步异常----接口资源下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 组装保存接口资源的入参
     * @param resourceOperationservice
     * @return
     */
    private AddApiResourceParam dealAddApiParams(ResourceOperationservice resourceOperationservice){
        AddApiResourceParam addApiResourceParam = new AddApiResourceParam();
        addApiResourceParam.setServiceId(resourceOperationservice.getServiceId());
        addApiResourceParam.setCallType(resourceOperationservice.getServiceType());
        addApiResourceParam.setEndpoint(resourceOperationservice.getUrl());
        // TODO 文档里的值对应不上，类型不匹配
        addApiResourceParam.setTimeout(resourceOperationservice.getTimeout()+"");
        addApiResourceParam.setRestRequestMethod(resourceOperationservice.getMethodType());
        addApiResourceParam.setWsNamespace(null);
        // TODO 文档里的值对应不上
        addApiResourceParam.setWsHeader("");
        addApiResourceParam.setWsMethod(null);
        ResourceService resourceService = new ResourceService();
        resourceService.setServiceName(resourceOperationservice.getCnName());
        resourceService.setServiceCode(null);
        resourceService.setServiceDesc(resourceOperationservice.getServiceDesc());
        resourceService.setServiceType(resourceOperationservice.getServiceType());
        resourceService.setServiceTag(null);
        resourceService.setVersion("1.0");
        // TODO 文档里的值对应不上
        resourceService.setContext(null);
        resourceService.setApprovalAuthority("provider");
        resourceService.setResultSample(resourceOperationservice.getOutput());
        // TODO 无
        resourceService.setOnlineTime(null);
        // TODO 默认获取资源时间
        resourceService.setOfflineTime(null);
        resourceService.setStatus(Integer.parseInt(resourceOperationservice.getOperateType()));
        resourceService.setCataId(resourceOperationservice.getCataId());
        resourceService.setServiceDoc(resourceOperationservice.getAttach());
        // TODO 无
        resourceService.setServiceProtectParam(null);
        resourceService.setInputParam(resourceOperationservice.getInputParams());
        resourceService.setOutputParam(resourceOperationservice.getOutputParams());
        // TODO 无
        resourceService.setResultStatusCode(null);
        resourceService.setContactName(resourceOperationservice.getSupporter());
        resourceService.setContactPhone(resourceOperationservice.getSupporterPhone());
        resourceService.setContactEmail(resourceOperationservice.getSupporterEmail());
        resourceService.setCreateTime(new Date());
        // TODO 根据中间程序自动生成
        resourceService.setCreatorId(null);
        resourceService.setCreatorName(null);
        resourceService.setUpdateTime(new Date());
        resourceService.setUpdaterId(null);
        resourceService.setUpdaterName(null);
        // TODO 去开放平台获取
        resourceService.setCataTitle(null);
        resourceService.setRegionCode(null);
        resourceService.setRegionName(null);
        resourceService.setOrgCode(null);
        resourceService.setOrgName(null);

        addApiResourceParam.setResourceService(resourceService);

        if(ValidationUtil.validate(addApiResourceParam) && ValidationUtil.validate(resourceService)){
            return addApiResourceParam;
        }else{
            log.error("保存接口资源，请求参数存在必填项为空，需检查参数:{} 和 {}", addApiResourceParam.toString(), resourceService.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

    /**
     * 调用，保存接口资源
     * @param addApiResourceParam
     */
    private void addApiResource(AddApiResourceParam addApiResourceParam){
        String url = apiResourceUrl + "/admin/resource/addApiResource";
        log.debug("保存接口资源，url:{}", url);
        log.debug("保存接口资源，请求参数:{}", addApiResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(addApiResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存接口资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("保存接口资源成功。");
        }else{
            String msg = result.getString("msg");
            log.error("保存接口资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 删除接口资源
     * @param apiId 接口资源id
     * @param cataId 目录id
     */
    private void deleteApiResource(String apiId, String cataId){
        String url = apiResourceUrl + "/admin/resource/deleteApiResource";
        log.debug("删除接口资源，url:{}", url);
        log.debug("删除接口资源，请求参数:{} 和 {}", apiId, cataId);
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        Map<String, String> map = new HashMap<>();
        map.put("api_id", apiId);
        map.put("cataid", cataId);
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存接口资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("删除接口资源成功。");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        }else{
            String msg = result.getString("msg");
            log.error("删除接口资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }
}
