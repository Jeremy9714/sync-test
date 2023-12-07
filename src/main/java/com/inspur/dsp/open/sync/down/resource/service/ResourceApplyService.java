package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceApply;

public interface ResourceApplyService extends IService<ResourceApply> {

    boolean syncResourceApply();

    void syncResourceApplyIncrement();
}
