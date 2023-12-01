package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("resource_apply")
public class ResourceApply implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 申请记录主键
     */
    @TableField("apply_id")
    private String applyId;

    /**
     * 区共享平台目录id
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 区共享平台资源id
     */
    @TableField("resourceid")
    private String resourceId;

    /**
     * 资源名称
     */
    @TableField("resourcename")
    private String resourceName;

    /**
     * 申请时间
     */
    @TableField("apply_date")
    private Date applyDate;

    /**
     * 申请部门社会信用代码
     */
    @TableField("credit_code")
    private String creditCode;

    /**
     * 申请部门名称
     */
    @TableField("apply_ouname")
    private String applyOuname;

    /**
     * 申请人名称
     */
    @TableField("apply_user")
    private String applyUser;

    /**
     * 电话/邮箱
     */
    @TableField("contact")
    private String contact;

    /**
     * 申请有效期，开始日期
     */
    @TableField("start_date")
    private Date startDate;

    /**
     * 申请有效期，失效日期
     */
    @TableField("end_date")
    private Date endDate;

    /**
     * 资源使用范围
     */
    @TableField("use_scope")
    private String useScope;

    /**
     * 资源用途说明
     */
    @TableField("use_desc")
    private String useDesc;

    /**
     * 附件信息:
     * [{"filename": "","fileid": ""}...]
     */
    @TableField("attach")
    private String attach;

    /**
     * 申请类型
     * 库表：1
     * 文件：3
     * 接口：4
     */
    @TableField("apply_type")
    private String applyType;

    /**
     * 申请的字段名称，多个分号隔开
     */
    @TableField("fieldname")
    private String fieldName;

    /**
     * 操作时间
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 操作类型：
     * 新增：I；
     * 修改：U;
     * 删除：D
     */
    @TableField("operate_type")
    private String operateType;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 数据字典：申请状态
     */
    @TableField("apply_status")
    private String applyStatus;

    /**
     * 开放门户备案审核状态
     */
    @TableField("record_status")
    private String recordStatus;

    /**
     * 开放门户取消备案时的处理意见
     */
    @TableField("handling_advice")
    private String handlingAdvice;


    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getApplyOuname() {
        return applyOuname;
    }

    public void setApplyOuname(String applyOuname) {
        this.applyOuname = applyOuname;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUseScope() {
        return useScope;
    }

    public void setUseScope(String useScope) {
        this.useScope = useScope;
    }

    public String getUseDesc() {
        return useDesc;
    }

    public void setUseDesc(String useDesc) {
        this.useDesc = useDesc;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getHandlingAdvice() {
        return handlingAdvice;
    }

    public void setHandlingAdvice(String handlingAdvice) {
        this.handlingAdvice = handlingAdvice;
    }
}
