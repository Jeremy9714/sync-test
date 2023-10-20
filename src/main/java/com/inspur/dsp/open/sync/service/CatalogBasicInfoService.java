package com.inspur.dsp.open.sync.service;

import com.baomidou.mybatisplus.service.IService;
import com.inspur.dsp.open.sync.entity.CatalogBasicInfo;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:43
 * @Version: 1.0
 */
public interface CatalogBasicInfoService extends IService<CatalogBasicInfo> {

    boolean syncCatalogBasicInfo();
}
