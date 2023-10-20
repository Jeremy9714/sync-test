package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceTable;

public interface ResourceTableService extends IService<ResourceTable> {

    boolean syncResourceTable();
}
