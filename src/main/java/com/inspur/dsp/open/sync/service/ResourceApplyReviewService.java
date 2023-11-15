package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceApplyReview;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/11/15 8:48
 * @Version: 1.0
 */
public interface ResourceApplyReviewService extends IService<ResourceApplyReview> {

    boolean syncResourceApplyReview();
}
