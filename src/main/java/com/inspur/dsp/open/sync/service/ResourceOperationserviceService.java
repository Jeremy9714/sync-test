package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceOperationservice;

public interface ResourceOperationserviceService extends IService<ResourceOperationservice> {

    boolean syncResourceOperationservice();
}
