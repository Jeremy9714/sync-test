package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.ResourceApplyDao;
import com.inspur.dsp.open.sync.entity.ResourceApply;
import com.inspur.dsp.open.sync.service.ResourceApplyService;
import com.inspur.dsp.open.sync.util.DubboService;
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
public class ResourceApplyServiceImpl extends ServiceImpl<ResourceApplyDao, ResourceApply> implements ResourceApplyService {
    private static final Logger log = LoggerFactory.getLogger(ResourceApplyServiceImpl.class);

    @Autowired
    private ResourceApplyDao resourceApplyDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DubboService dubboService;


    @Transactional
    @Override
    public boolean syncResourceApply() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_APPLY_KEY);
            String latestOperationDate = resourceApplyDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增资源申请下行表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceApply> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增资源申请下行表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate)
                        .orderBy("operate_date")
                        .last("limit 0,10");
            }

            List<ResourceApply> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            for(ResourceApply resourceFile : resultList){
                String operateType = resourceFile.getOperateType();
                switch (operateType){
                    case "I":
//                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "U":
//                        saveFileResource(dealSaveFileParams(resourceFile));
                        break;
                    case "D":
//                        deleteResource(resourceFile.getResourceId());
                        break;
                    default:
                        throw new RuntimeException("资源申请，无此操作类型");
                }
                redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_APPLY_KEY, latestOperationDate);
            }

            return true;
        } catch (Exception e) {
            log.error("--------同步异常----资源申请下行表", e);
            e.printStackTrace();
        }
        return false;
    }


}
