package com.inspur.dsp.open.sync.down.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 目录id
     */
    @NotNull
    private String cataId;

    /**
     * 目录编码
     */
    private String cataCode;

    /**
     * 目录名称
     */
    @NotNull
    private String cataTitle;

    /**
     * 目录logo
     */
    private String cataLogo;

    /**
     * 目录类型（10-政务目录 11-社会目录  12互联网目录）
     */
    @NotNull
    private Integer cataType;

    /**
     * 目录标签，多个用逗号（,）隔开
     */
    private String cataTags;

    /**
     * 目录描述
     */
    @NotNull
    private String description;

    /**
     * 数据使用说明文档ID
     */
    private String descDocId;

    /**
     * 部门编码
     */
    private String orgCode;

    /**
     * 部门类型
     */
    private Integer organType;

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 提供方内部部门
     */
    private String providerInternalOrgan;

    /**
     * 开放类型，1-无条件开放 2-有条件开放
     */
    @NotNull
    private String openType;

    /**
     * 开放条件，开放类型为有条件开放时填写
     */
    private String openCondition;

    /**
     * 资源格式，41-mysql 44-GBase等
     */
    private String resourceFormat;

    /**
     * 资源格式类型，1-电子文件 2-电子表格 3-数据库 4-图形图像 5-流媒体 6-自描述格式
     */
    private String resourceFormatType;

    /**
     * 更新频率，1-不定期 2-每天 3-每周 4-每月 5-每季度 6-没半年 7-每年 8-实时
     */
    @NotNull
    private String updateCycle;

    /**
     * 用户自定义频率，当更新频率为不定期时填写
     */
    private String updateCycleUser;

    /**
     * 接入类型，多种时以逗号分隔，1-库表 2-文件 3-API
     */
    private String joinType;

    /**
     * 开放方式，多种时以逗号分隔，1-数据集 2-文件集 3-API接口 4-地图
     */
    private String useType;

    /**
     * 发布时间
     */
    private String releasedTime;

    /**
     * 下架时间
     */
    private String offlineTime;

    /**
     * 数据更新时间
     */
    private String dataUpdateTime;

    /**
     * 数据文件更新生成时间
     */
    private String datafileUpdateTime;

    /**
     * 地图配置更新时间
     */
    private String datamapUpdateTime;

    /**
     * 服务更新时间
     */
    private String dataapiUpdateTime;

    /**
     * 目录来源：1-共享交换的目录，2-对接系统上报的目录，3-接入系统注册的目录，4-目录系统新增目录
     */
    @NotNull
    private String cataSource;

    /**
     * 行政区划码
     */
    @NotNull
    private String regionCode;

    /**
     * 行政区划名称
     */
    private String regionName;

    /**
     * 目录状态，（0：草稿；1：待接入审核 ；2：接入审核驳回；3：待汇聚； 4：待发布审核；5：发布审核驳回；6：待发布；7：已发布；8：已下架；-1：删除）
     */
    @NotNull
    private Integer cataStatus;

    /**
     *
     */
    private String statusCn;

    /**
     * 创建时间
     */
    @NotNull
    private String createTime;

    /**
     * 创建者id
     */
    @NotNull
    private String creatorId;

    /**
     * 创建者姓名
     */
    private String creatorName;

    /**
     * 更新时间
     */
    @NotNull
    private String updateTime;

    /**
     * 更新者id
     */
    @NotNull
    private String updaterId;

    /**
     * 更新者姓名
     */
    private String updaterName;

    /**
     * 标准目录id
     */
    private String standardId;


    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getCataCode() {
        return cataCode;
    }

    public void setCataCode(String cataCode) {
        this.cataCode = cataCode;
    }

    public String getCataTitle() {
        return cataTitle;
    }

    public void setCataTitle(String cataTitle) {
        this.cataTitle = cataTitle;
    }

    public String getCataLogo() {
        return cataLogo;
    }

    public void setCataLogo(String cataLogo) {
        this.cataLogo = cataLogo;
    }

    public Integer getCataType() {
        return cataType;
    }

    public void setCataType(Integer cataType) {
        this.cataType = cataType;
    }

    public String getCataTags() {
        return cataTags;
    }

    public void setCataTags(String cataTags) {
        this.cataTags = cataTags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescDocId() {
        return descDocId;
    }

    public void setDescDocId(String descDocId) {
        this.descDocId = descDocId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getOrganType() {
        return organType;
    }

    public void setOrganType(Integer organType) {
        this.organType = organType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProviderInternalOrgan() {
        return providerInternalOrgan;
    }

    public void setProviderInternalOrgan(String providerInternalOrgan) {
        this.providerInternalOrgan = providerInternalOrgan;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
    }

    public String getResourceFormat() {
        return resourceFormat;
    }

    public void setResourceFormat(String resourceFormat) {
        this.resourceFormat = resourceFormat;
    }

    public String getResourceFormatType() {
        return resourceFormatType;
    }

    public void setResourceFormatType(String resourceFormatType) {
        this.resourceFormatType = resourceFormatType;
    }

    public String getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(String updateCycle) {
        this.updateCycle = updateCycle;
    }

    public String getUpdateCycleUser() {
        return updateCycleUser;
    }

    public void setUpdateCycleUser(String updateCycleUser) {
        this.updateCycleUser = updateCycleUser;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(String releasedTime) {
        this.releasedTime = releasedTime;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    public String getDatafileUpdateTime() {
        return datafileUpdateTime;
    }

    public void setDatafileUpdateTime(String datafileUpdateTime) {
        this.datafileUpdateTime = datafileUpdateTime;
    }

    public String getDatamapUpdateTime() {
        return datamapUpdateTime;
    }

    public void setDatamapUpdateTime(String datamapUpdateTime) {
        this.datamapUpdateTime = datamapUpdateTime;
    }

    public String getDataapiUpdateTime() {
        return dataapiUpdateTime;
    }

    public void setDataapiUpdateTime(String dataapiUpdateTime) {
        this.dataapiUpdateTime = dataapiUpdateTime;
    }

    public String getCataSource() {
        return cataSource;
    }

    public void setCataSource(String cataSource) {
        this.cataSource = cataSource;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCataStatus() {
        return cataStatus;
    }

    public void setCataStatus(Integer cataStatus) {
        this.cataStatus = cataStatus;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }
}
