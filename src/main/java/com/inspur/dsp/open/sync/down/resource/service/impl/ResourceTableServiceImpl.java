package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogItem;
import com.inspur.dsp.open.sync.down.catalog.dao.CatalogItemDao;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceTable;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceTableDao;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceTableDto;
import com.inspur.dsp.open.sync.down.resource.service.ResourceTableService;
import com.inspur.dsp.open.sync.down.resource.api.OpenApiService;
import com.inspur.dsp.open.sync.util.DSPBeanUtils;
import com.inspur.dsp.open.sync.util.DubboService;
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
import java.util.*;


@Service
public class ResourceTableServiceImpl extends ServiceImpl<ResourceTableDao, ResourceTable> implements ResourceTableService {
    private static final Logger log = LoggerFactory.getLogger(ResourceTableServiceImpl.class);

    @Autowired
    private ResourceTableDao resourceTableDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OpenApiService openApiService;

    @Autowired
    private CatalogItemDao catalogItemDao;

    @Autowired
    private DubboService dubboService;

    @Transactional
    @Override
    public boolean syncResourceTable() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                wrapper.gt("operate_date", lastSyncDate);
            }

            wrapper.orderBy("operate_date");
            List<ResourceTable> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for (ResourceTable resourceTable : resultList) {
                String operateType = resourceTable.getOperateType();
                switch (operateType) {
                    case "I":
                    case "U":
                        openApiService.insertOrUpdateResourceTable(transformTableToMap(resourceTable));
                        break;
                    case "D":
                        openApiService.deleteResourceTable(transformTableToMapDelete(resourceTable));
//                        deleteOpenResourceTable(resourceTable.getItemId());
                        break;
                    default:
                        throw new RuntimeException("库表资源，无此操作类型");
                }
                String currentOperateDate = sdf.format(resourceTable.getOperateDate());
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_TABLE_KEY, currentOperateDate);
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
     *
     * @param resourceTable
     * @return
     */
    private Map<String, Object> transformTableToMap(ResourceTable resourceTable) {
        ResourceTableDto resourceTableDto = new ResourceTableDto();

        DSPBeanUtils.copyProperties(resourceTable, resourceTableDto);
        resourceTableDto.setItemId(resourceTable.getItemId());

        // 获取字段信息columnnameEn拼接
        EntityWrapper<CatalogItem> wrapper = new EntityWrapper<>();
        wrapper.eq("cata_id", resourceTable.getCataid());
        List<CatalogItem> catalogItems = catalogItemDao.selectList(wrapper);

        StringBuilder columnnameEn = new StringBuilder();
        StringBuilder itemIds = new StringBuilder();
        catalogItems.stream().forEach(item -> {
            columnnameEn.append(item.getNameEn()).append(",");
            itemIds.append(item.getItemId()).append(",");
        });

        columnnameEn.deleteCharAt(columnnameEn.length() - 1);
        itemIds.deleteCharAt(itemIds.length() - 1);

        log.info("columnnameEn字符串 {}", columnnameEn.toString());
        log.info("items字符串 {}", itemIds.toString());

//        List<Map<String, Object>> tableColumnList = openApiService.getTableColumn(resourceTableDto.getDataSourceIdcheck(), resourceTableDto.getDataTableName());
//        StringBuilder sb = new StringBuilder();
//        for (Map<String, Object> columnMap : tableColumnList) {
//            String name = MapUtils.getString(columnMap, "name");
//            String description = MapUtils.getString(columnMap, "description");
//            String isNull = MapUtils.getString(columnMap, "isNull");
//            String isPk = MapUtils.getString(columnMap, "isPk");
//            String format = MapUtils.getString(columnMap, "format");
//            String length = MapUtils.getString(columnMap, "length");
//            sb.append(name).append("@").append(description).append("@")
//                    .append(isNull).append("@").append("isPk").append("@")
//                    .append(isPk).append("@").append(format).append("@")
//                    .append("length").append(",");
//        }
//        resourceTableDto.setColumnnameEn(sb.toString());
        resourceTableDto.setColumnnameEn(columnnameEn.toString());
        resourceTableDto.setItemId(itemIds.toString());

        if (!ValidationUtil.validate(resourceTableDto)) {
            log.error("保存库表资源，请求参数存在必填项为空，需检查参数:{}", resourceTableDto);
            throw new RuntimeException("请求参数不合规");
        }

        Map<String, Object> tableMap = DSPBeanUtils.beanToMap(resourceTableDto);

        return tableMap;
    }

    /**
     * 下线库表资源 dubbo
     *
     * @param tableId
     */
    private void deleteOpenResourceTable(String tableId){
        Result<Boolean> result = dubboService.deleteOpenTableResource(tableId);
        Boolean flag = result.getObject();
        int code = result.getCode();
        if (code == 0 && flag) {
            log.info("下线库表资源成功!");
        } else {
            log.error("下线库表资源资源，接口调用失败!");
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 组装删除库表资源的入参
     *
     * @param resourceTable
     * @return
     */
    private Map<String, Object> transformTableToMapDelete(ResourceTable resourceTable) {
        Map<String, Object> tableMap = new HashMap<>();

        if (StringUtils.isBlank(resourceTable.getDataSourceIdcheck()) || StringUtils.isNotBlank(resourceTable.getItemId()) || StringUtils.isBlank(resourceTable.getCataid())) {
            log.error("删除库表资源，请求参数存在必填项为空，需检查参数:{}", resourceTable.toString());
            throw new RuntimeException("请求参数不合规");
        }
        tableMap.put("datasource_id", resourceTable.getDataSourceIdcheck());
        tableMap.put("table_id", new String[]{resourceTable.getItemId()});
        tableMap.put("cataid", resourceTable.getCataid());

        return tableMap;
    }

}
