package com.inspur.dsp.open.sync.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.catalog.api.IOpenCatalogInfoService;
import com.inspur.dsp.open.catalog.api.IOpenCatalogItemService;
import com.inspur.dsp.open.catalog.api.IOpenStatisticCatalogService;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.resource.api.IOpenResourceApplyService;
import com.inspur.dsp.open.resource.api.IOpenResourceFileService;
import com.inspur.dsp.open.resource.api.IOpenResourceTableService;
import com.inspur.dsp.resource.api.IFileResourceService;
import com.inspur.dsp.resource.api.IResourceBaseService;
import com.inspur.service.OrganizationService;
import com.inspur.service.UserAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DubboService {
    private static final Logger log = LoggerFactory.getLogger(DubboService.class);

    @Reference(group = "bsp", check = false)
    private UserAuthorityService userAuthorityService;

    @Reference(group = "bsp", check = false)
    private OrganizationService organizationService;

    @Reference(group = "metaresource", check = false)
    private IFileResourceService fileResourceService;

    @Reference(group = "metaresource", check = false)
    private IResourceBaseService resourceBaseService;

    @Reference(group = "openResource", check = false)
    private IOpenResourceApplyService openResourceApplyService;

    @Reference(group = "openCatalog", check = false)
    private IOpenCatalogInfoService openCatalogInfoService;

    @Reference(group = "openCatalog", check = false)
    private IOpenCatalogItemService openCatalogItemService;

    @Reference(group = "openCatalog", check = false)
    private IOpenStatisticCatalogService openStatisticCatalogService;

    @Reference(group = "openResource", check = false)
    private IOpenResourceFileService openResourceFileService;

    @Reference(group = "openResource", check = false)
    private IOpenResourceTableService openResourceTableService;


    public JSONObject userLogin(String username, String password) {
        return this.userAuthorityService.userLogin(username, password, "DSP-INTEGRATION-CONSOLE", true);
    }

    /**
     * 开放库表资源下线
     * @param tableId
     * @return
     */
    public Result<Boolean> deleteOpenTableResource(String tableId) {
        return openResourceTableService.updateTableStatusByTableId(tableId,5);
    }

    /**
     * 保存文件资源
     * 支持新增和更新文件资源信息的综合接口
     *
     * @param map
     * @return
     */
    public Map<String, Object> saveFileResource(Map<String, Object> map) {
        return fileResourceService.saveFileResource(map);
    }


    /**
     * 开放文件资源推送
     * @param map
     * @return
     */
    public Result<Boolean> saveOpenFileResource(Map<String, Object> map) {
        return openResourceFileService.addResourceFile(map);
    }

    /**
     * 开放文件资源更新
     * @param map
     * @return
     */
    public Result<Boolean> updateOpenResourceFile(Map<String,Object> map){
//        return openResourceFileService.updateResourceFile(map);
        return null;
    }

    /**
     * 开放文件资源下架
     * @param fileId
     * @return
     */
    public Result<Boolean> deleteOpenResourceFile(String fileId){
        return openResourceFileService.updateFileStatusByFileId(fileId,5);
    }

    /**
     * 删除文件资源接口。
     *
     * @param id
     * @return
     */
    public Map<String, Object> deleteResource(String id) {
        return resourceBaseService.deleteResource(id);
    }

    /**
     * 保存目录和信息项
     * 支持新增和更新。
     *
     * @param catalogMap
     * @return
     */
    public Result<Map<String, Object>> insertOrUpdateCatalogInfo(Map<String, Object> catalogMap) {
        return openCatalogInfoService.insertOrUpdateCatalogInfo(catalogMap);
    }

    /**
     * 删除目录。
     *
     * @param cataId
     * @return
     */
    public Result<Boolean> deleteCatalogInfo(String cataId) {
        return openCatalogInfoService.deleteCatalogInfo(cataId);
    }

    /**
     * 删除信息项
     *
     * @param cataId
     * @return
     */
    public Result<Boolean> deleteCatalogItemsByCataId(String cataId) {
        return openCatalogItemService.deleteCatalogItemsByCataId(cataId);
    }
}
