package com.inspur.dsp.open.sync.down.catalog.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:51
 * @Version: 1.0
 */
@Repository
public interface CatalogItemDao extends BaseMapper<CatalogItem> {

    List<CatalogItem> getCatalogFieldInfoByCataIds(@Param("cataIds") List<String> cataIds);
}