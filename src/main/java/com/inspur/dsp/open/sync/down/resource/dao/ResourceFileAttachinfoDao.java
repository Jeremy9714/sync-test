package com.inspur.dsp.open.sync.down.resource.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFileAttachinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileAttachinfoDao extends BaseMapper<ResourceFileAttachinfo> {

    String getLatestOperationDate();
}
