package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceApply;

public interface ResourceApplyService extends IService<ResourceApply> {

    boolean syncResourceApply();
}
