package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceTable;

public interface ResourceTableService extends IService<ResourceTable> {

    boolean syncResourceTable();
}