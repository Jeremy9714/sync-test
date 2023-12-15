package com.inspur.dsp.open.sync.down.resource.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.resource.bean.OpenResourceFile;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/15 10:43
 * @Version: 1.0
 */
@Repository
public interface OpenResourceFileDao extends BaseMapper<OpenResourceFile> {
    String getLatestOperationDate();
}
