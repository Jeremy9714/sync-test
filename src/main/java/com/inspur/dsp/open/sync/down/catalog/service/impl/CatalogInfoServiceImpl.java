package com.inspur.dsp.open.sync.down.catalog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogInfo;
import com.inspur.dsp.open.sync.down.catalog.dao.CatalogInfoDao;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogItem;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogInfoService;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogItemService;
import com.inspur.dsp.open.sync.down.catalog.dto.CatalogItemDto;
import com.inspur.dsp.open.sync.down.catalog.dto.CatalogInfoDto;
import com.inspur.dsp.open.sync.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:44
 * @Version: 1.0
 */
@Service
public class CatalogInfoServiceImpl extends ServiceImpl<CatalogInfoDao, com.inspur.dsp.open.sync.down.catalog.bean.CatalogInfo> implements CatalogInfoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogInfoServiceImpl.class);

    @Autowired
    private CatalogInfoDao catalogInfoDao;

    @Autowired
    private CatalogItemService catalogItemService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;

    @Transactional
    @Override
    public boolean syncCatalogBasicInfo() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_CATALOG_INFO_KEY);
            String latestOperationDate = catalogInfoDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无目录信息数据，不需要同步");
                return true;
            }

            EntityWrapper<CatalogInfo> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增目录信息数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            wrapper.orderBy("operate_date").last("limit 100");
            List<CatalogInfo> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for (CatalogInfo catalogInfo : resultList) {
                String operateType = catalogInfo.getOperateType();
                switch (operateType) {
                    case "I":
                        insertOrUpdateCatalogInfo(transformCatalogInfoToMap(catalogInfo));
                        break;
                    case "U":
                        insertOrUpdateCatalogInfo(transformCatalogInfoToMap(catalogInfo));
                        break;
                    case "D":
                        deleteCatalog(catalogInfo.getCataId());
                        break;
                    default:
                        throw new RuntimeException("目录信息，无此操作类型");
                }
                String currentOperateDate = sdf.format(catalogInfo.getOperateDate());
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_CATALOG_INFO_KEY, currentOperateDate);
            }

            return true;
        } catch (Exception e) {
            log.error("--------同步异常----目录信息下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    private Map<String, Object> transformCatalogInfoToMap(CatalogInfo catalogInfo) {
        Map<String, Object> catalogMap = new HashMap<>();
        EntityWrapper<CatalogItem> wrapper = new EntityWrapper<>();
        wrapper.eq("cata_id", catalogInfo.getCataId());
        List<CatalogItem> catalogItemList = catalogItemService.selectList(wrapper);
        if (catalogItemList == null || catalogItemList.size() == 0) {
            log.error("保存目录信息，查询信息项下行表数据为空，CataId为:{}", catalogInfo.getCataId());
            throw new RuntimeException("查询信息项下行表数据为空");
        }
        CatalogInfoDto catalogInfoDto = new CatalogInfoDto();
        DSPBeanUtils.copyProperties(catalogInfo, catalogInfoDto);
        catalogInfoDto.setCataType(10);
        catalogInfoDto.setCataSource("1");
        catalogInfoDto.setCataStatus(7);
        // 中间程序生成
        catalogInfoDto.setCreateTime(SyncDataUtil.getCurrentTime());
        catalogInfoDto.setCreatorId(SyncDataUtil.CURRENT_ID);
        catalogInfoDto.setCreatorName(SyncDataUtil.CURRENT_NAME);
        catalogInfoDto.setUpdateTime(SyncDataUtil.getCurrentTime());
        catalogInfoDto.setUpdaterId(SyncDataUtil.CURRENT_ID);
        catalogInfoDto.setUpdaterName(SyncDataUtil.CURRENT_NAME);
        if (!ValidationUtil.validate(catalogInfoDto)) {
            log.error("保存目录信息，请求参数catalogInfo存在必填项为空，需检查参数:{}", catalogInfoDto.toString());
            throw new RuntimeException("请求参数不合规");
        }
        catalogMap.put("catalogInfo", catalogInfoDto);

        // cataItemList
        List<CatalogItemDto> catalogItemDtoList = new ArrayList<>();
        for (CatalogItem catalogItem : catalogItemList) {
            CatalogItemDto catalogItemDto = new CatalogItemDto();
            DSPBeanUtils.copyProperties(catalogItem, catalogItemDto);
            catalogItemDto.setNameEn(null);
            catalogItemDto.setUpdateTime(SyncDataUtil.getCurrentTime());
            if (!ValidationUtil.validate(catalogItemDto)) {
                log.error("保存目录信息，请求参数cataItem存在必填项为空，需检查参数:{}", catalogItemDto.toString());
                throw new RuntimeException("请求参数不合规");
            }
            catalogItemDtoList.add(catalogItemDto);
        }
        catalogMap.put("cataItemList", catalogItemDtoList);

        // cataGroupList
        Map<String, Object> cataGroup = new HashMap<>();
        // 中间程序生成
        cataGroup.put("linkId", SyncDataUtil.getUUID());
        cataGroup.put("cataId", catalogInfoDto.getCataId());
        catalogMap.put("cataGroupList", cataGroup);

        return catalogMap;
    }

    /**
     * 保存目录和信息项
     *
     * @param catalogMap
     */
    private void insertOrUpdateCatalogInfo(Map<String, Object> catalogMap) {
        Result<Map<String, Object>> result = dubboService.insertOrUpdateCatalogInfo(catalogMap);
        log.debug("保存目录和信息项，返回结果:{}", result.toString());
        int code = result.getCode();
        if (code == 200) {
            log.info("保存目录和信息项成功");
        } else {
            String msg = result.getMessage();
            log.error("保存目录和信息项失败，{}", msg);
            throw new RuntimeException("保存目录和信息项失败");
        }
    }

    /**
     * 删除目录和信息项
     *
     * @param cataId
     */
    private void deleteCatalog(String cataId) {
        Result<Boolean> catalogInfoResult = dubboService.deleteCatalogInfo(cataId);
        log.debug("删除目录，返回结果:{}", catalogInfoResult.toString());
        Result<Boolean> catalogItemsResult = dubboService.deleteCatalogItemsByCataId(cataId);
        log.debug("删除信息项，返回结果:{}", catalogInfoResult.toString());
        if (catalogInfoResult.getObject() && catalogItemsResult.getObject()) {
            log.info("删除目录和信息项成功，{}", cataId);
        } else {
            log.error("删除目录和信息项失败");
            throw new RuntimeException("删除目录和信息项失败");
        }
    }

}
