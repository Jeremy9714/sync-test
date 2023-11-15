package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.ResourceApplyReview;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/11/15 8:48
 * @Version: 1.0
 */
@Repository
public interface ResourceApplyReviewDao extends BaseMapper<ResourceApplyReview> {

    String getLatestOperationDate();
}
