package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceDatasource;

public interface ResourceDatasourceService extends IService<ResourceDatasource> {

    boolean syncResourceDatasource();
}
