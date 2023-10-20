package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceFile;

public interface ResourceFileService extends IService<ResourceFile> {

    boolean syncResourceFile();
}
