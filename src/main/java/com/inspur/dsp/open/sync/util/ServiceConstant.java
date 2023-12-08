package com.inspur.dsp.open.sync.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 10:11
 * @Version: 1.0
 */
@Component
public class ServiceConstant {

    public static String SYNC_CATALOG_GROUP_LINK_KEY;

    public static String SYNC_CATALOG_INFO_KEY;

    public static String SYNC_RESOURCE_FILE_KEY;

    public static String SYNC_RESOURCE_TABLE_KEY;

    public static String SYNC_RESOURCE_DATASOURCE_KEY;


    @Value("${catalogGroupLink.key:}")
    public void setSyncCatalogGroupLinkKey(String syncCatalogGroupLinkKey) {
        SYNC_CATALOG_GROUP_LINK_KEY = syncCatalogGroupLinkKey;
    }

    @Value("${catalogInfo.key:}")
    public void setSyncCatalogInfoKey(String syncCatalogInfoKey) {
        SYNC_CATALOG_INFO_KEY = syncCatalogInfoKey;
    }

    @Value("${resourceFile.key:}")
    public void setSyncResourceFileKey(String syncResourceFileKey) {
        SYNC_RESOURCE_FILE_KEY = syncResourceFileKey;
    }

    @Value("${resourceTable.key:}")
    public void setSyncResourceTableKey(String syncResourceTableKey) {
        SYNC_RESOURCE_TABLE_KEY = syncResourceTableKey;
    }

    @Value("${resourceTable.key:}")
    public void setSyncResourceDatasourceKey(String syncResourceDatasourceKey) {
        SYNC_RESOURCE_DATASOURCE_KEY = syncResourceDatasourceKey;
    }

}
