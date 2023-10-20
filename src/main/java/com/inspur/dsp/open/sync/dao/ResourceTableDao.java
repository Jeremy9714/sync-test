package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.ResourceTable;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTableDao extends BaseMapper<ResourceTable> {

    String getLatestOperationDate();
}
