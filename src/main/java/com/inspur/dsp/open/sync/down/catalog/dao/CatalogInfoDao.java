package com.inspur.dsp.open.sync.down.catalog.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:42
 * @Version: 1.0
 */
@Repository
public interface CatalogInfoDao extends BaseMapper<CatalogInfo> {

    String getLatestOperationDate();
}