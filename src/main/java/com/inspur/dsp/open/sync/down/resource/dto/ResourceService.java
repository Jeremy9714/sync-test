package com.inspur.dsp.open.sync.down.resource.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ResourceService implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 服务名称
     */
    @NotNull
    private String serviceName;

    /**
     * 服务编码
     */
    @NotNull
    private String serviceCode;

    /**
     * 对服务的描述
     */
    @NotNull
    private String serviceDesc;

    /**
     * 服务类型，0:数据服务，1:接入服务
     */
    @NotNull
    private String serviceType;

    /**
     * 标记服务作用域，sandbox为沙箱系统专用，默认为null
     */
    @NotNull
    private String serviceTag;

    /**
     * 服务版本号
     */
    @NotNull
    private String version;

    /**
     * 服务上下文
     */
    @NotNull
    private String context;

    /**
     * 授权方式：platform-平台授权，provider-提供方授权
     */
    @NotNull
    private String approvalAuthority;

    /**
     * 返回示例
     */
    @NotNull
    private String resultSample;

    /**
     * 服务上线的时间
     */
    @NotNull
    private Date onlineTime;

    /**
     * 下架时间，当不再使用该接口对外提供服务时，执行下架
     */
    @NotNull
    private Date offlineTime;

    /**
     * 状态，1草稿，2待审核，3审核通过，4 发布，5下架，-1删除
     */
    @NotNull
    private Integer status;

    /**
     * 目录ID
     */
    @NotNull
    private String cataId;

    /**
     * 服务的详细说明文档，以JSON格式存储
     */
    @NotNull
    private String serviceDoc;

    /**
     * 服务调用保护参数e
     */
    @NotNull
    private String serviceProtectParam;

    /**
     * 服务的输入参数，以JSON格式存储
     */
    @NotNull
    private String inputParam;

    /**
     * 服务的输出参数，以JSON格式存储
     */
    @NotNull
    private String outputParam;

    /**
     * 服务调用结果状态码，以JSON格式存储
     */
    @NotNull
    private String resultStatusCode;

    /**
     * 技术支持联系人
     */
    @NotNull
    private String contactName;

    /**
     * 技术支持联系人电话
     */
    @NotNull
    private String contactPhone;

    /**
     * 技术支持联系人邮箱
     */
    @NotNull
    private String contactEmail;

    /**
     * 创建时间
     */
    @NotNull
    private Date createTime;

    /**
     * 创建者id
     */
    @NotNull
    private String creatorId;

    /**
     * 创建者姓名
     */
    @NotNull
    private String creatorName;

    /**
     * 更新时间
     */
    @NotNull
    private Date updateTime;

    /**
     * 更新者id
     */
    @NotNull
    private String updaterId;

    /**
     * 更新者姓名
     */
    @NotNull
    private String updaterName;

    /**
     * 目录名称
     */
    @NotNull
    private String cataTitle;

    /**
     * 行政区划编码
     */
    @NotNull
    private String regionCode;

    /**
     * 行政区划名称
     */
    @NotNull
    private String regionName;

    /**
     * 组织结构编码
     */
    @NotNull
    private String orgCode;

    /**
     * 组织机构名称
     */
    @NotNull
    private String orgName;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getApprovalAuthority() {
        return approvalAuthority;
    }

    public void setApprovalAuthority(String approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
    }

    public String getResultSample() {
        return resultSample;
    }

    public void setResultSample(String resultSample) {
        this.resultSample = resultSample;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getServiceDoc() {
        return serviceDoc;
    }

    public void setServiceDoc(String serviceDoc) {
        this.serviceDoc = serviceDoc;
    }

    public String getServiceProtectParam() {
        return serviceProtectParam;
    }

    public void setServiceProtectParam(String serviceProtectParam) {
        this.serviceProtectParam = serviceProtectParam;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam;
    }

    public String getResultStatusCode() {
        return resultStatusCode;
    }

    public void setResultStatusCode(String resultStatusCode) {
        this.resultStatusCode = resultStatusCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

    public String getCataTitle() {
        return cataTitle;
    }

    public void setCataTitle(String cataTitle) {
        this.cataTitle = cataTitle;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
