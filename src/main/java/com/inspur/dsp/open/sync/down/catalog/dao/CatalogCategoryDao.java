package com.inspur.dsp.open.sync.down.catalog.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogCategory;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:51
 * @Version: 1.0
 */
@Repository
public interface CatalogCategoryDao extends BaseMapper<CatalogCategory> {

    String getLatestOperationDate();
}
