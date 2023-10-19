package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.CatalogBasicInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:42
 * @Version: 1.0
 */
@Repository
public interface CatalogBasicInfoDao extends BaseMapper<CatalogBasicInfo> {

    String getLatestOperationDate();
}
