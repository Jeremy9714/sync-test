package com.inspur.dsp.open.sync.down.catalog.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogItem;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:52
 * @Version: 1.0
 */
public interface CatalogItemService extends IService<CatalogItem> {

    List<CatalogItem> getCatalogFieldInfoByCataIds(List<String> cataIds);
}
