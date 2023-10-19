package com.inspur.dsp.open.sync.service.impl;

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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public boolean syncCatalogCategory() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String flag = redisTemplate.opsForValue().get(ServiceConstant.SYNC_CATALOG_CATEGORY_KEY);
            String latestOperationDate = categoryDao.getLatestOperationDate();
            if (StringUtils.isNotBlank(flag) && sdf.parse(flag).compareTo(sdf.parse(latestOperationDate)) <= 0) {
                log.info("目录分类数据为最新，不需要更新");
            } else {

            }
            redisTemplate.opsForValue().set(ServiceConstant.SYNC_CATALOG_CATEGORY_KEY, sdf.format(new Date()));
            return true;
        } catch (Exception e) {
            log.error("同步目录分类数据异常: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
