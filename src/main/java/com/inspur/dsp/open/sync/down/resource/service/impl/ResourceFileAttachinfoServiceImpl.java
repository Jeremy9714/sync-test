package com.inspur.dsp.open.sync.down.resource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceFileAttachinfoDao;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFileAttachinfo;
import com.inspur.dsp.open.sync.down.resource.service.ResourceFileAttachinfoService;
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


@Service
public class ResourceFileAttachinfoServiceImpl extends ServiceImpl<ResourceFileAttachinfoDao, ResourceFileAttachinfo> implements ResourceFileAttachinfoService {
    private static final Logger log = LoggerFactory.getLogger(ResourceFileAttachinfoServiceImpl.class);

    @Autowired
    private ResourceFileAttachinfoDao resourceFileAttachinfoDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public boolean syncResourceFileAttachinfo() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String lastSyncDate = redisTemplate.opsForValue().get(ServiceConstant.SYNC_RESOURCE_FILE_KEY);
            String latestOperationDate = resourceFileAttachinfoDao.getLatestOperationDate();
            // 无数据
            if (StringUtils.isBlank(latestOperationDate)) {
                log.info("下行库无新增文件资源附件列表信息表数据，不需要同步");
                return true;
            }

            EntityWrapper<ResourceFileAttachinfo> wrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(lastSyncDate)) {
                Date lastSyncTime = sdf.parse(lastSyncDate);
                Date latestOperationTime = sdf.parse(latestOperationDate);
                if (lastSyncTime.compareTo(latestOperationTime) >= 0) {
                    log.info("下行库无新增文件资源附件列表信息表数据，不需要同步");
                    return true;
                }
                wrapper.gt("operate_date", lastSyncDate);
            }

            List<ResourceFileAttachinfo> resultList = this.selectList(wrapper);
            log.debug("查询结果: {}", JSONObject.toJSONString(resultList));

            redisTemplate.opsForValue().set(ServiceConstant.SYNC_RESOURCE_FILE_KEY, latestOperationDate);


            return true;
        } catch (Exception e) {
            log.error("--------同步异常----文件资源附件列表信息表", e);
            e.printStackTrace();
        }
        return false;
    }
}
