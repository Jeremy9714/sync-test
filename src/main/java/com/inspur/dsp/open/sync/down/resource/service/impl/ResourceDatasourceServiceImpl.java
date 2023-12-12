package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.resource.api.OpenApiService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceDatasource;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceDatasourceDao;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceDatasourceDto;
import com.inspur.dsp.open.sync.down.resource.service.ResourceDatasourceService;
import com.inspur.dsp.open.sync.util.DSPBeanUtils;
import com.inspur.dsp.open.sync.util.ServiceConstant;
import com.inspur.dsp.open.sync.util.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 9:25
 * @Version: 1.0
 */
@Service
public class ResourceDatasourceServiceImpl extends ServiceImpl<ResourceDatasourceDao, ResourceDatasource> implements ResourceDatasourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceDatasourceServiceImpl.class);

    @Autowired
    private ResourceDatasourceDao resourceDatasourceDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OpenApiService openApiService;

    @Override
    public boolean syncResourceDatasource() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_FILE_KEY);
            String latestOperationDate = resourceDatasourceDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增文数据源数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceDatasource> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增数据源数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            wrapper.orderBy("operate_date");
            List<ResourceDatasource> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for (ResourceDatasource resourceDatasource : resultList) {
                insertOrUpdateDatasource(transformDatasourceToMap(resourceDatasource));
                String currentOperateDate = sdf.format(resourceDatasource.getOperateDate());
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_DATASOURCE_KEY, currentOperateDate);
            }
            return true;
        } catch (Exception e) {
            log.error("--------同步异常----数据源信息下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    private Map<String, Object> transformDatasourceToMap(ResourceDatasource resourceDatasource) {
        Map<String, Object> datasourceMap = new HashMap<>();
        ResourceDatasourceDto resourceDatasourceDto = new ResourceDatasourceDto();
        DSPBeanUtils.copyProperties(resourceDatasource, resourceDatasourceDto);
        String key = String.valueOf(System.currentTimeMillis());
        resourceDatasourceDto.setKey(key);
        if (!ValidationUtil.validate(resourceDatasourceDto)) {
            log.error("保存数据源信息，请求参数resourceDatasourceDto存在必填项为空，需检查参数:{}", resourceDatasourceDto.toString());
            throw new RuntimeException("请求参数不合规");
        }

        datasourceMap = DSPBeanUtils.beanToMap(resourceDatasourceDto);
        return datasourceMap;
    }

    /**
     * 保存数据源
     *
     * @param datasourceMap
     */
    private void insertOrUpdateDatasource(Map<String, Object> datasourceMap) {
        openApiService.insertOrUpdateResourceDatasource(datasourceMap);
    }
}
