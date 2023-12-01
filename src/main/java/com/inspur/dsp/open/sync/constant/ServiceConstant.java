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

    public static String SYNC_CATALOG_BASIC_INFO_KEY;

    public static String SYNC_CATALOG_FIELD_INFO_KEY;

    public static String SYNC_RESOURCE_FILE_KEY;

    public static String SYNC_RESOURCE_OPERATION_SERVICE_KEY;

    public static String SYNC_RESOURCE_TABLE_KEY;

    public static String SYNC_RESOURCE_APPLY_REVIEW_KEY;

    public static String SYNC_RESOURCE_APPLY_KEY;

    @Value("${catalogCategory.key:}")
    private void setSyncCatalogCategoryKey(String key) {
        SYNC_CATALOG_CATEGORY_KEY = key;
    }

    @Value("${catalogBasicInfo.key:}")
    private void setSyncCatalogBasicInfoKey(String key) {
        SYNC_CATALOG_BASIC_INFO_KEY = key;
    }

    @Value("${catalogFieldInfo.key:}")
    private void setSyncCatalogFieldInfoKey(String key) {
        SYNC_CATALOG_FIELD_INFO_KEY = key;
    }

    @Value("${resourceFile.key:}")
    public void setSyncResourceFileKey(String syncResourceFileKey) {
        SYNC_RESOURCE_FILE_KEY = syncResourceFileKey;
    }

    @Value("${resourceOperationservice.key:}")
    public void setSyncResourceOperationServiceKey(String syncResourceOperationServiceKey) {
        SYNC_RESOURCE_OPERATION_SERVICE_KEY = syncResourceOperationServiceKey;
    }

    @Value("${resourceTable.key:}")
    public void setSyncResourceTableKey(String syncResourceTableKey) {
        SYNC_RESOURCE_TABLE_KEY = syncResourceTableKey;
    }

    @Value("${resourceApplyReview.key:}")
    public void setSyncResourceApplyReviewKey(String syncResourceApplyReviewKey) {
        SYNC_RESOURCE_APPLY_REVIEW_KEY = syncResourceApplyReviewKey;
    }

    @Value("${resourceApply.key:}")
    public void setSyncResourceApplyKey(String syncResourceApplyKey) {
        SYNC_RESOURCE_APPLY_KEY = syncResourceApplyKey;
    }
}
