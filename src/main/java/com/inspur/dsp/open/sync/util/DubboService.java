package com.inspur.dsp.open.sync.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.catalog.api.IOpenCatalogInfoService;
import com.inspur.dsp.open.catalog.api.IOpenCatalogItemService;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.resource.api.IOpenResourceApplyService;
import com.inspur.dsp.resource.api.IResourceBaseService;
import com.inspur.service.UserAuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DubboService {

    @Reference(group = "bsp", check = false)
    private UserAuthorityService userAuthorityService;

    @Reference(group = "metaresource", check = false)
    private IResourceBaseService resourceBaseService;

    @Reference(group = "openResource", check = false)
    private IOpenResourceApplyService openResourceApplyService;

    @Reference(group = "openCatalog", check = false)
    private IOpenCatalogInfoService openCatalogInfoService;

    @Reference(group = "openCatalog", check = false)
    private IOpenCatalogItemService openCatalogItemService;


    public JSONObject userLogin(String username, String password) {
        return this.userAuthorityService.userLogin(username, password, "DSP-INTEGRATION-CONSOLE", true);
    }

    public Map<String, Object> findTableResource(String id){
        return this.resourceBaseService.findTableResource(id);
    }

    /**
     * 保存文件资源
     * 支持新增和更新文件资源信息的综合接口
     * @param map
     * @return
     */
    public Map<String, Object> saveFileResource(Map<String, Object> map){
        return resourceBaseService.saveFileResource(map);
    }

    /**
     * 删除文件资源接口。
     * @param id
     * @return
     */
    public Map<String, Object> deleteResource(String id){
        return resourceBaseService.deleteResource(id);
    }

    /**
     * 更新资源申请的审核结果
     * 更新资源申请信息同时插入审核日志。
     * @param resourceCheckList
     * @param resourceApplyList
     * @return
     */
    public Result<Boolean> insertandUpadteResourceApply(List<Map<String, Object>> resourceCheckList, List<Map<String, Object>> resourceApplyList){
        return openResourceApplyService.insertandUpadteResourceApply(resourceCheckList, resourceApplyList);
    }

    /**
     * 保存目录和信息项
     * 支持新增和更新。
     * @param catalogMap
     * @return
     */
    public Result<Map<String, Object>> insertOrUpdateCatalogInfo(Map<String, Object> catalogMap){
        return openCatalogInfoService.insertOrUpdateCatalogInfo(catalogMap);
    }

    /**
     * 删除目录。
     * @param cataId
     * @return
     */
    public Result<Boolean> deleteCatalogInfo(String cataId){
        return openCatalogInfoService.deleteCatalogInfo(cataId);
    }

    /**
     * 删除信息项
     * @param cataId
     * @return
     */
    public Result<Boolean> deleteCatalogItemsByCataId(String cataId){
        return openCatalogItemService.deleteCatalogItemsByCataId(cataId);
    }
}
