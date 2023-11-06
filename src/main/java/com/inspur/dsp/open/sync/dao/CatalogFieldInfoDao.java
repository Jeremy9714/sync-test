package com.inspur.dsp.open.sync.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inspur.dsp.open.sync.entity.CatalogFieldInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:51
 * @Version: 1.0
 */
@Repository
public interface CatalogFieldInfoDao extends BaseMapper<CatalogFieldInfo> {

    List<CatalogFieldInfo> getCatalogFieldInfoByCataIds(@Param("cataIds") List<String> cataIds);
}
