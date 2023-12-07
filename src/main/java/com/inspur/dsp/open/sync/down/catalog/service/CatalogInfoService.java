package com.inspur.dsp.open.sync.down.catalog.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogInfo;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:43
 * @Version: 1.0
 */
public interface CatalogInfoService extends IService<CatalogInfo> {

    boolean syncCatalogBasicInfo();
}
