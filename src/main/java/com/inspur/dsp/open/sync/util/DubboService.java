package com.inspur.dsp.open.sync.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.catalog.api.IOpenCatalogInfoService;
import com.inspur.dsp.open.catalog.api.IOpenCatalogItemService;
import com.inspur.dsp.open.catalog.api.IOpenStatisticCatalogService;
import com.inspur.dsp.open.common.Result;
import com.inspur.dsp.open.resource.api.IOpenResourceApplyService;
import com.inspur.dsp.open.resource.api.IOpenResourceFileService;
import com.inspur.dsp.resource.api.IFileResourceService;
import com.inspur.dsp.resource.api.IResourceBaseService;
import com.inspur.service.OrganizationService;
import com.inspur.service.UserAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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


    public JSONObject userLogin(String username, String password) {
        return this.userAuthorityService.userLogin(username, password, "DSP-INTEGRATION-CONSOLE", true);
    }

    public Map<String, Object> findTableResource(String id) {
//        return this.resourceBaseService.findTableResource(id);
        return null;
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

//    /**
//     * 开放文件资源更新
//     * @param map
//     * @return
//     */
//    public Result<Boolean> updateOpenResourceFile(Map<String,Object> map){
//        return openResourceFileService.updateResourceFile(map);
//    }

//    /**
//     * 开放文件资源下架
//     * @param fileId
//     * @return
//     */
//    public Result<Boolean> deleteOpenResourceFile(String fileId){
//        return openResourceFileService.updateFileStatusByFileId(fileId,5);
//    }

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
     * 更新资源申请的审核结果
     * 更新资源申请信息同时插入审核日志。
     *
     * @param resourceCheckList
     * @param resourceApplyList
     * @return
     */
    public Result<Boolean> insertandUpadteResourceApply(List<Map<String, Object>> resourceCheckList, List<Map<String, Object>> resourceApplyList) {
        return openResourceApplyService.insertandUpadteResourceApply(resourceCheckList, resourceApplyList);
    }

    /**
     * 查询申请表的增量数据
     * 根据参数applyTime，返回申请表中申请时间大于该时间且状态为待审核状态的数据
     * 查询时间，要求格式：yyyy-MM-dd hh:mm:ss
     *
     * @param applyTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Result<Map<String, Object>> getResourceApplyByPage(String applyTime, int pageNum, int pageSize) {
        return openResourceApplyService.getResourceApplyByPage(applyTime, pageNum, pageSize);
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

    /**
     * 根据统一社会信用代码查询组织机构的organ_code/name/region/region_name
     * 根据参数统一社会信用代码（org_num），查询组织机构详细信息
     * <p>
     * {
     * "msg": "成功",
     * "code": "200",
     * "data": {
     * "CODE": "370000000098",
     * "ORGAN_LEVEL": "2",
     * "REGION_NAME": "山东省",
     * "REGION_CODE": "370000000000",
     * "ID": "07C88413D8584C1B9E7109956DB179E9",
     * "SHORT_CODE": "370098",
     * "SHORT_NAME": "省水利厅",
     * "NAME": "山东省水利厅"
     * }
     * }
     *
     * @param orgNum
     * @return
     */
    public JSONObject getOrganInfoByOrgNum(String orgNum) {
        JSONObject regionInfo = organizationService.getOrganInfoByOrgNum(orgNum);
        int code = regionInfo.getInteger("code");
        if (code != 200) {
            log.error("统一社会信用代码查询失败，{}", orgNum);
            throw new RuntimeException("统一社会信用代码查询失败");
        }
        JSONObject regionData = regionInfo.getJSONObject("data");
        return regionData;
    }

    /**
     * 获取目录下挂载的已发布资源数量
     *
     * @param cataId
     * @return
     */
    public Result<Map<String, Object>> queryByCataId(String cataId) {
        return openStatisticCatalogService.queryByCataId(cataId);
    }

    /**
     * 根据目录ID获取目录名称
     *
     * @param cataId
     * @return
     */
    public String getCatalogInfoById(String cataId) {
        Result<Map<String, Object>> result = openCatalogInfoService.getCatalogInfoById(cataId);
        int code = result.getCode();
        if (code != 200) {
            log.error("根据目录ID获取目录名称查询失败，{}", cataId);
            throw new RuntimeException("根据目录ID获取目录名称查询失败");
        }
        Map<String, Object> catalogInfo = result.getObject();
        return (String) catalogInfo.get("cataTitle");
    }
}
