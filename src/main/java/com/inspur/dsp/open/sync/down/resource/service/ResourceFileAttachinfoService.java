package com.inspur.dsp.open.sync.down.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFileAttachinfo;

public interface ResourceFileAttachinfoService extends IService<ResourceFileAttachinfo> {

    boolean syncResourceFileAttachinfo();
}
