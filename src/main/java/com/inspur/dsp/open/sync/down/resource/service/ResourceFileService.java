package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFile;

public interface ResourceFileService extends IService<ResourceFile> {

    boolean syncResourceFile();
}
