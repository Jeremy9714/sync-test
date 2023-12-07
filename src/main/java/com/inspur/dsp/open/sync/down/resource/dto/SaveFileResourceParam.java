package com.inspur.dsp.open.sync.down.resource.dto;


import javax.validation.constraints.NotNull;

public class SaveFileResourceParam {

    /**
     * 资源id
     */
    private String id;

    /**
     * 资源名称
     */
    @NotNull
    private String resName;

    /**
     * 文件资源描述
     */
    @NotNull
    private String resDesc;

    /**
     * 挂接目录id
     */
    @NotNull
    private String cataId;

    /**
     * 挂接目录名称
     */
    @NotNull
    private String cataName;

    /**
     * 部门编码
     */
    @NotNull
    private String orgId;

    /**
     * 部门名称
     */
    @NotNull
    private String orgName;

    /**
     * 区划编码
     */
    @NotNull
    private String regionCode;

    /**
     * 共享类型
     */
    @NotNull
    private Integer shareType;

    /**
     * 来源系统编码
     */
    private String fromSystemId;

    /**
     * 来源系统名称
     */
    private String fromSystemName;

    /**
     * 共享条件
     */
    private String shareCondition;

    /**
     * 开放类型
     */
    @NotNull
    private Integer openType;

    /**
     * 开放条件
     */
    private String openCondition;

    /**
     * 更新周期
     */
    @NotNull
    private Integer updateCycle;

    /**
     * 用户更新周期
     */
    private String customUpdateCycle;

    /**
     * 用户id
     */
    @NotNull
    private String creatorId;

    /**
     * 用户名称
     */
    @NotNull
    private String creatorName;

    /**
     * 资源审核状态
     */
    private Integer status;

    /**
     * 资源开放状态
     */
    private Integer openStatus;

    /**
     * 文件名称
     */
    @NotNull
    private String fileName;

    /**
     * 文件大小
     */
    @NotNull
    private Integer fileSize;

    /**
     * 文件路径
     */
    @NotNull
    private String filePath;

    /**
     * 文件格式
     */
    @NotNull
    private String fileFormat;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getCataName() {
        return cataName;
    }

    public void setCataName(String cataName) {
        this.cataName = cataName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public String getFromSystemId() {
        return fromSystemId;
    }

    public void setFromSystemId(String fromSystemId) {
        this.fromSystemId = fromSystemId;
    }

    public String getFromSystemName() {
        return fromSystemName;
    }

    public void setFromSystemName(String fromSystemName) {
        this.fromSystemName = fromSystemName;
    }

    public String getShareCondition() {
        return shareCondition;
    }

    public void setShareCondition(String shareCondition) {
        this.shareCondition = shareCondition;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
    }

    public Integer getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(Integer updateCycle) {
        this.updateCycle = updateCycle;
    }

    public String getCustomUpdateCycle() {
        return customUpdateCycle;
    }

    public void setCustomUpdateCycle(String customUpdateCycle) {
        this.customUpdateCycle = customUpdateCycle;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
