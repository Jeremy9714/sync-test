package com.inspur.dsp.open.sync.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 10:11
 * @Version: 1.0
 */
@Component
public class ServiceConstant {

    public static String SYNC_CATALOG_CATEGORY_KEY;

    public static String SYNC_CATALOG_BASICINFO_KEY;

    public static String SYNC_CATALOG_FIELDINFO_KEY;

    @Value("${catalogCategory.key:}")
    private void setSyncCatalogCategoryKey(String key) {
        SYNC_CATALOG_CATEGORY_KEY = key;
    }

    @Value("${catalogBasicinfo.key:}")
    private void setSyncCatalogBasicinfoKey(String key) {
        SYNC_CATALOG_BASICINFO_KEY = key;
    }

    @Value("${catalogFieldinfo.key:}")
    private void setSyncCatalogFieldinfoKey(String key) {
        SYNC_CATALOG_FIELDINFO_KEY = key;
    }
}
