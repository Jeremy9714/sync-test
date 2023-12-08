package com.inspur.dsp.open.sync.down.resource.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceDatasource;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 9:15
 * @Version: 1.0
 */
@Repository
public interface ResourceDatasourceDao extends BaseMapper<ResourceDatasource> {

    String getLatestOperationDate();
}
