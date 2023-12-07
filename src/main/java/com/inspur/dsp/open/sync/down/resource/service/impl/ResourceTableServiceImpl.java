package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceTableDao;
import com.inspur.dsp.open.sync.down.dto.AddTableResourceParam;
import com.inspur.dsp.open.sync.down.dto.DeleteTableResourceParam;
import com.inspur.dsp.open.sync.down.resource.service.ResourceTableService;
import com.inspur.dsp.open.sync.down.service.OpenApiReqService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceTable;
import com.inspur.dsp.open.sync.util.ServiceConstant;
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
public class ResourceTableServiceImpl extends ServiceImpl<ResourceTableDao, ResourceTable> implements ResourceTableService {
    private static final Logger log = LoggerFactory.getLogger(ResourceTableServiceImpl.class);


    @Autowired
    private ResourceTableDao resourceTableDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OpenApiReqService openApiReqService;

    @Transactional
    @Override
    public boolean syncResourceTable() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_TABLE_KEY);
            String latestOperationDate = resourceTableDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增库表资源下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceTable> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增库表资源下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<ResourceTable> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(ResourceTable resourceTable : resultList){
                String operateType = resourceTable.getOperateType();
                switch (operateType){
                    case "I":
                        openApiReqService.addTableResource(dealAddTableParams(resourceTable));
                        break;
                    case "U":
                        openApiReqService.addTableResource(dealAddTableParams(resourceTable));
                        break;
                    case "D":
                        openApiReqService.deleteTableResource(dealDeleteTableParams(resourceTable));
                        break;
                    default:
                        throw new RuntimeException("库表资源，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_TABLE_KEY, latestOperationDate);
            }

            return true;
        } catch (Exception e) {
            log.error("--------同步异常----库表资源下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 组装保存库表资源的入参
     * @param resourceTable
     * @return
     */
    private AddTableResourceParam dealAddTableParams(ResourceTable resourceTable){
        AddTableResourceParam addTableResourceParam = new AddTableResourceParam();
        // TODO 等待数据源信息提供
        addTableResourceParam.setDataSourceIdCheck(null);
        addTableResourceParam.setItemId(new String[]{resourceTable.getResourceId()});
        addTableResourceParam.setCataId(resourceTable.getCataId());
        String json = resourceTable.getTablejson();
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        String tableName = jsonObject.getString("table_sqlname");
        String tableDesc = jsonObject.getString("table_name");
        addTableResourceParam.setDataTableName(tableName);
        addTableResourceParam.setTableDesc(tableDesc);

        if(ValidationUtil.validate(addTableResourceParam)){
            return addTableResourceParam;
        }else{
            log.error("保存库表资源，请求参数存在必填项为空，需检查参数:{}", addTableResourceParam.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

    /**
     * 组装删除库表资源的入参
     * @param resourceTable
     * @return
     */
    private DeleteTableResourceParam dealDeleteTableParams(ResourceTable resourceTable){
        DeleteTableResourceParam deleteTableResourceParam = new DeleteTableResourceParam();
        // TODO 等待数据源信息提供
        deleteTableResourceParam.setDatasourceId(null);
        deleteTableResourceParam.setItemId(new String[]{resourceTable.getResourceId()});
        deleteTableResourceParam.setCataId(resourceTable.getCataId());

        if(ValidationUtil.validate(deleteTableResourceParam)){
            return deleteTableResourceParam;
        }else{
            log.error("删除库表资源，请求参数存在必填项为空，需检查参数:{}", deleteTableResourceParam.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

}
