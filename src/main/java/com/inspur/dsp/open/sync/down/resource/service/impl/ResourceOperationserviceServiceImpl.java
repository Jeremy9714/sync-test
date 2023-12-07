package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceOperationserviceDao;
import com.inspur.dsp.open.sync.down.dto.AddApiResourceParam;
import com.inspur.dsp.open.sync.down.resource.service.ResourceOperationserviceService;
import com.inspur.dsp.open.sync.down.service.OpenApiReqService;
import com.inspur.dsp.open.sync.down.dto.ResourceService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceOperationservice;
import com.inspur.dsp.open.sync.util.DubboService;
import com.inspur.dsp.open.sync.util.ServiceConstant;
import com.inspur.dsp.open.sync.util.SyncDataUtil;
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


@Service
public class ResourceOperationserviceServiceImpl extends ServiceImpl<ResourceOperationserviceDao, ResourceOperationservice> implements ResourceOperationserviceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceOperationserviceServiceImpl.class);

    @Autowired
    private ResourceOperationserviceDao resourceOperationserviceDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OpenApiReqService openApiReqService;

    @Autowired
    private DubboService dubboService;

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
                        openApiReqService.addApiResource(dealAddApiParams(resourceOperationservice));
                        break;
                    case "U":
                        openApiReqService.addApiResource(dealAddApiParams(resourceOperationservice));
                        break;
                    case "D":
                        openApiReqService.deleteApiResource(resourceOperationservice.getServiceId(), resourceOperationservice.getCataId());
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
        addApiResourceParam.setTimeout(resourceOperationservice.getTimeout()+"");
        addApiResourceParam.setRestRequestMethod(resourceOperationservice.getMethodType());
        addApiResourceParam.setWsNamespace(null);
        // TODO 文档里的值对应不上
        addApiResourceParam.setWsHeader("soapHeader");
        addApiResourceParam.setWsMethod(null);
        ResourceService resourceService = new ResourceService();
        resourceService.setServiceName(resourceOperationservice.getCnName());
        resourceService.setServiceCode(null);
        resourceService.setServiceDesc(resourceOperationservice.getServiceDesc());
        resourceService.setServiceType(resourceOperationservice.getServiceType());
        resourceService.setServiceTag(null);
        resourceService.setVersion("1.0");
        resourceService.setContext("uri");
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
        // 根据中间程序自动生成
        resourceService.setCreatorId(SyncDataUtil.CURRENT_ID);
        resourceService.setCreatorName(SyncDataUtil.CURRENT_NAME);
        resourceService.setUpdateTime(new Date());
        resourceService.setUpdaterId(SyncDataUtil.CURRENT_ID);
        resourceService.setUpdaterName(SyncDataUtil.CURRENT_NAME);
        // 去开放平台获取
        String cataTitle = dubboService.getCatalogInfoById(resourceOperationservice.getCataId());
        resourceService.setCataTitle(cataTitle);
        JSONObject regionData = dubboService.getOrganInfoByOrgNum(resourceOperationservice.getCreditCode());
        resourceService.setRegionCode(regionData.getString("REGION_CODE"));
        resourceService.setRegionName(regionData.getString("REGION_NAME"));
        resourceService.setOrgCode(regionData.getString("CODE"));
        resourceService.setOrgName(regionData.getString("NAME"));

        addApiResourceParam.setResourceService(resourceService);

        if(ValidationUtil.validate(addApiResourceParam) && ValidationUtil.validate(resourceService)){
            return addApiResourceParam;
        }else{
            log.error("保存接口资源，请求参数存在必填项为空，需检查参数:{} 和 {}", addApiResourceParam.toString(), resourceService.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

}
