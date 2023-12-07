package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceApplyReviewDao;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceApplyReview;
import com.inspur.dsp.open.sync.down.resource.service.ResourceApplyReviewService;
import com.inspur.dsp.open.sync.util.ServiceConstant;
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
 * @Author: zhangchy05 on 2023/11/15 8:49
 * @Version: 1.0
 */
@Service
public class ResourceApplyReviewServiceImpl extends ServiceImpl<ResourceApplyReviewDao, ResourceApplyReview> implements ResourceApplyReviewService {

    private static final Logger log = LoggerFactory.getLogger(ResourceApplyReviewServiceImpl.class);

    @Autowired
    private ResourceApplyReviewDao resourceApplyReviewDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public boolean syncResourceApplyReview() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_APPLY_REVIEW_KEY);
            String latestOperationDate = resourceApplyReviewDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增资源申请审核过程表，不需要同步");
                return true;
            }

            EntityWrapper<ResourceApplyReview> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增资源申请审核过程表，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            List<ResourceApplyReview> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));
            redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_APPLY_REVIEW_KEY, latestOperationDate);


            return true;
        } catch (Exception e) {
            log.error("同步目录分类数据异常: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
