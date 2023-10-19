package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.CatalogCategoryDao;
import com.inspur.dsp.open.sync.entity.CatalogCategory;
import com.inspur.dsp.open.sync.service.CatalogCategoryService;
import com.inspur.dsp.open.sync.util.SyncDataServiceUtils;
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

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:57
 * @Version: 1.0
 */
@Service
public class CatalogCategoryServiceImpl extends ServiceImpl<CatalogCategoryDao, CatalogCategory> implements CatalogCategoryService {
    private static final Logger log = LoggerFactory.getLogger(CatalogCategoryServiceImpl.class);

    @Autowired
    private CatalogCategoryDao categoryDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public boolean syncCatalogCategory() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_CATALOG_CATEGORY_KEY);
            String latestOperationDate = categoryDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增目录分类数据，不需要同步");
                return true;
            }

            EntityWrapper<CatalogCategory> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增目录分类数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            List<CatalogCategory> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));
            redisTemplate.opsForValue().set(ServiceConstant.SYNC_CATALOG_CATEGORY_KEY, latestOperationDate);


            return true;
        } catch (Exception e) {
            log.error("同步目录分类数据异常: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
