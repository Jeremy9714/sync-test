package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.ResourceOperationservice;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceOperationserviceDao extends BaseMapper<ResourceOperationservice> {

    String getLatestOperationDate();
}
