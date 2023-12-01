package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.CatalogBasicInfoDao;
import com.inspur.dsp.open.sync.entity.CatalogBasicInfo;
import com.inspur.dsp.open.sync.entity.CatalogFieldInfo;
import com.inspur.dsp.open.sync.service.CatalogBasicInfoService;
import com.inspur.dsp.open.sync.service.CatalogFieldInfoService;
import com.inspur.dsp.open.sync.share.CataItem;
import com.inspur.dsp.open.sync.share.CatalogInfo;
import com.inspur.dsp.open.sync.util.DubboService;
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
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:44
 * @Version: 1.0
 */
@Service
public class CatalogBasicInfoServiceImpl extends ServiceImpl<CatalogBasicInfoDao, CatalogBasicInfo> implements CatalogBasicInfoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogBasicInfoServiceImpl.class);

    @Autowired
    private CatalogBasicInfoDao catalogBasicInfoDao;

    @Autowired
    private CatalogFieldInfoService catalogFieldInfoService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;

    @Transactional
    @Override
    public boolean syncCatalogBasicInfo() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_CATALOG_BASIC_INFO_KEY);
            String latestOperationDate = catalogBasicInfoDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无目录信息数据，不需要同步");
                return true;
            }

            EntityWrapper<CatalogBasicInfo> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增目录信息数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<CatalogBasicInfo> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(CatalogBasicInfo catalogBasicInfo : resultList){
                String operateType = catalogBasicInfo.getOperateType();
                switch (operateType){
                    case "I":
                        insertOrUpdateCatalogInfo(dealUpdateCatalogInfoParam(catalogBasicInfo));
                        break;
                    case "U":
                        insertOrUpdateCatalogInfo(dealUpdateCatalogInfoParam(catalogBasicInfo));
                        break;
                    case "D":
//                        deleteTableResource(dealDeleteTableParams(resourceTable));
                        break;
                    default:
                        throw new RuntimeException("目录信息，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_CATALOG_BASIC_INFO_KEY, latestOperationDate);
            }

        } catch (Exception e) {
            log.error("--------同步异常----目录信息下行表", e);
            e.printStackTrace();
        }
        return false;
    }

    private Map<String,Object> dealUpdateCatalogInfoParam(CatalogBasicInfo catalogBasicInfo){
        Map<String,Object> catalogMap = new HashMap<>();
        CatalogFieldInfo catalogFieldInfo = catalogFieldInfoService.selectById(catalogBasicInfo.getCataId());
        if(catalogFieldInfo == null){
            log.error("保存目录信息，查询信息项下行表数据为空，CataId为:{}", catalogBasicInfo.getCataId());
            throw new RuntimeException("查询信息项下行表数据为空");
        }
        CatalogInfo catalogInfo = new CatalogInfo();
        catalogInfo.setCataId(catalogBasicInfo.getCataId());
        catalogInfo.setCataCode(catalogBasicInfo.getCataCode());
        catalogInfo.setCataTitle(catalogBasicInfo.getCataTitle());
        catalogInfo.setCataType(10);
        catalogInfo.setDescription(catalogBasicInfo.getDescription());
        catalogInfo.setOrgName(catalogBasicInfo.getRegisterOrgName());
        // TODO 文档不一致
        catalogInfo.setOpenType(null);
        catalogInfo.setOpenCondition(catalogBasicInfo.getOpenCondition());
        catalogInfo.setResourceFormat(catalogBasicInfo.getCataType());
        catalogInfo.setUpdateCycle(catalogBasicInfo.getUpdateCycle());
        catalogInfo.setCataSource("1");
        // TODO 根据统一社会信用代码查询
        catalogInfo.setRegionCode(null);
        catalogInfo.setRegionName(null);
        catalogInfo.setCataStatus(7);
        // TODO 中间程序生成
        catalogInfo.setCreateTime(null);
        catalogInfo.setCreatorId(null);
        catalogInfo.setCreatorName(null);
        catalogInfo.setUpdateTime(null);
        catalogInfo.setUpdaterId(null);
        catalogInfo.setUpdaterName(null);
        if(!ValidationUtil.validate(catalogInfo)){
            log.error("保存目录信息，请求参数catalogInfo存在必填项为空，需检查参数:{}", catalogInfo.toString());
            throw new RuntimeException("请求参数不合规");
        }
        catalogMap.put("catalogInfo", catalogInfo);

        List<CataItem> cataItemList = new ArrayList<>();
        CataItem cataItem = new CataItem();
        cataItem.setItemId(catalogFieldInfo.getColumnId());
        cataItem.setNameEn(null);
        cataItem.setNameCn(catalogFieldInfo.getColumnName());
        cataItem.setDescription(catalogFieldInfo.getColumnName());
        cataItem.setDataFormat(catalogFieldInfo.getColumnType());
        cataItem.setLength(catalogFieldInfo.getColumnLength());
        cataItem.setCataId(catalogFieldInfo.getCataId());
        // TODO 中间程序生成
        cataItem.setUpdateTime(null);
        if(!ValidationUtil.validate(cataItem)){
            log.error("保存目录信息，请求参数cataItem存在必填项为空，需检查参数:{}", cataItem.toString());
            throw new RuntimeException("请求参数不合规");
        }
        cataItemList.add(cataItem);
        catalogMap.put("cataItemList", cataItemList);

        Map<String, Object> cataGroup = new HashMap<>();
        // TODO 中间程序生成
        cataGroup.put("linkId", null);
        cataGroup.put("cataId", catalogFieldInfo.getCataId());
        catalogMap.put("cataGroupList", cataGroup);

        return catalogMap;
    }

    private void insertOrUpdateCatalogInfo(Map<String,Object> catalogMap){
        Result<Map<String, Object>> result = dubboService.insertOrUpdateCatalogInfo(catalogMap);
        int code = result.getCode();
        // TODO code等于啥
    }

}
