package com.inspur.dsp.open.sync.down.catalog.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogGroupLink;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:55
 * @Version: 1.0
 */
public interface CatalogGroupLinkService extends IService<CatalogGroupLink> {

    boolean syncCatalogCategory();
}
