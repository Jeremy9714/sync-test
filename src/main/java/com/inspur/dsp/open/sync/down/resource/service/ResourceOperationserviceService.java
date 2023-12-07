package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceOperationservice;

public interface ResourceOperationserviceService extends IService<ResourceOperationservice> {

    boolean syncResourceOperationservice();
}
