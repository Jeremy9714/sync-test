package com.inspur.dsp.open.sync.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.CatalogFieldInfoDao;
import com.inspur.dsp.open.sync.entity.CatalogFieldInfo;
import com.inspur.dsp.open.sync.service.CatalogFieldinfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:52
 * @Version: 1.0
 */
@Service
public class CatalogFieldinfoServiceImpl extends ServiceImpl<CatalogFieldInfoDao, CatalogFieldInfo> implements CatalogFieldinfoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogFieldinfoServiceImpl.class);

    @Autowired
    private CatalogFieldInfoDao catalogFieldinfoDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean syncCatalogFieldInfo() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_CATALOG_FIELDINFO_KEY);
            String latestOperationDate = catalogFieldinfoDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无信息项数据，不需要同步");
                return true;
            }

            EntityWrapper<CatalogFieldInfo> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增信息据项数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            List<CatalogFieldInfo> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", resultList.toString());
            redisTemplate.opsForValue().set(ServiceConstant.SYNC_CATALOG_FIELDINFO_KEY, latestOperationDate);


            return true;
        } catch (Exception e) {
            log.error("同步信息项数据异常: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
