package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.ResourceTableDao;
import com.inspur.dsp.open.sync.entity.ResourceTable;
import com.inspur.dsp.open.sync.service.ResourceTableService;
import com.inspur.dsp.open.sync.share.AddTableResourceParam;
import com.inspur.dsp.open.sync.share.DeleteTableResourceParam;
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
import java.util.List;


@Service
public class ResourceTableServiceImpl extends ServiceImpl<ResourceTableDao, ResourceTable> implements ResourceTableService {
    private static final Logger log = LoggerFactory.getLogger(ResourceTableServiceImpl.class);

    @Value("${share.tableResource.url}")
    private String tableResourceUrl;

    @Autowired
    private ResourceTableDao resourceTableDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

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
                        addTableResource(dealAddTableParams(resourceTable));
                        break;
                    case "U":
                        addTableResource(dealAddTableParams(resourceTable));
                        break;
                    case "D":
                        deleteTableResource(dealDeleteTableParams(resourceTable));
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
     * 调用，保存库表资源
     */
    private void addTableResource(AddTableResourceParam addTableResourceParam){
        String url = tableResourceUrl + "/admin/resource/addTableResource";
        log.debug("保存库表资源，url:{}", url);
        log.debug("保存库表资源，请求参数:{}", addTableResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(addTableResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("保存库表资源成功。");
        }else{
            String msg = result.getString("msg");
            log.error("保存库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
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
        // TODO 文档有误，对应不上
        deleteTableResourceParam.setTableId(resourceTable.getResourceId());
        deleteTableResourceParam.setCataId(resourceTable.getCataId());

        if(ValidationUtil.validate(deleteTableResourceParam)){
            return deleteTableResourceParam;
        }else{
            log.error("删除库表资源，请求参数存在必填项为空，需检查参数:{}", deleteTableResourceParam.toString());
            throw new RuntimeException("请求参数不合规");
        }
    }

    /**
     * 调用，删除库表资源
     * @return
     */
    private void deleteTableResource(DeleteTableResourceParam deleteTableResourceParam){
        String url = tableResourceUrl + "/admin/resource/deleteTableResource";
        log.debug("删除库表资源，url:{}", url);
        log.debug("删除库表资源，请求参数:{}", deleteTableResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(deleteTableResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("删除库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("删除库表资源成功。");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        }else{
            String msg = result.getString("msg");
            log.error("删除库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }
}
