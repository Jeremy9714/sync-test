package com.inspur.dsp.open.sync.down.resource.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceTable;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTableDao extends BaseMapper<ResourceTable> {

    String getLatestOperationDate();
}
