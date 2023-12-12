package com.inspur.dsp.open.sync.down.catalog.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogGroupLink;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/28 16:16
 * @Version: 1.0
 */
@Repository
public interface ApiMapper extends BaseMapper<CatalogGroupLink> {

    @Select("select own_id from catalog_group where group_id = #{group_id}")
    String getOwnId(@Param("group_id") String groupId);
}
