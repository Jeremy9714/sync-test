package com.inspur.dsp.open.sync.down.resource.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceFile;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileDao extends BaseMapper<ResourceFile> {

    String getLatestOperationDate();
}
