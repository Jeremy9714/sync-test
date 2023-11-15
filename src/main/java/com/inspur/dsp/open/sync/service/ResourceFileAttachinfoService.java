package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.ResourceFileAttachinfo;

public interface ResourceFileAttachinfoService extends IService<ResourceFileAttachinfo> {

    boolean syncResourceFileAttachinfo();
}
