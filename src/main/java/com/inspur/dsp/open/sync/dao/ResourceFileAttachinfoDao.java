package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.ResourceFileAttachinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileAttachinfoDao extends BaseMapper<ResourceFileAttachinfo> {

    String getLatestOperationDate();
}
