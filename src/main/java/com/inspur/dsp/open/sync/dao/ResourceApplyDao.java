package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.ResourceApply;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceApplyDao extends BaseMapper<ResourceApply> {

    String getLatestOperationDate();
}
