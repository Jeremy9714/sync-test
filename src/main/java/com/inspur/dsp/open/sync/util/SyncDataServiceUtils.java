package com.inspur.dsp.open.sync.util;

import com.inspur.dsp.open.sync.service.CatalogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:59
 * @Version: 1.0
 */
@Component
public class SyncDataServiceUtils {

    @Autowired
    private static CatalogCategoryService catalogCategoryService;

    public static CatalogCategoryService getCatalogCategoryService() {
        return catalogCategoryService;
    }
}
