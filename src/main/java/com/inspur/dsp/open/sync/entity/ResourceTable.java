package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 库表资源下行表
 */
@TableName("resource_table")
public class ResourceTable implements Serializable {

    private static final long serialVersionUID = 123L;

    /**
     * 区共享平台资源主键
     */
    @TableField("resource_id")
    private String resourceId;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 提供机构社会信用代码
     */
    @TableField("register_org_code")
    private String registerOrgCode;

    /**
     * 提供机构名称
     */
    @TableField("res_org_name")
    private String resOrgName;

    /**
     * 区共享平台目录主键
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 目录名称
     */
    @TableField("cata_name")
    private Date cataName;

    /**
     * 数据表的json
     */
    @TableField("tablejson")
    private String tablejson;

    /**
     * 来源系统id
     */
    @TableField("resour_system_id")
    private String resourSystemId;

    /**
     * 数据字典：共享类型
     */
    @TableField("shared_type")
    private String sharedType;

    /**
     * 共享条件
     */
    @TableField("shared_condition")
    private String sharedCondition;

    /**
     * 数据字典：开放类型
     */
    @TableField("open_type")
    private String openType;

    /**
     * 使用条件
     */
    @TableField("UseDemand")
    private String useDemand;

    /**
     * 开放条件
     */
    @TableField("open_condition")
    private String openCondition;

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
     * 实际库表资源对应表名
     */
    @TableField("targetTableName")
    private String targetTableName;



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

    public String getRegisterOrgCode() {
        return registerOrgCode;
    }

    public void setRegisterOrgCode(String registerOrgCode) {
        this.registerOrgCode = registerOrgCode;
    }

    public String getResOrgName() {
        return resOrgName;
    }

    public void setResOrgName(String resOrgName) {
        this.resOrgName = resOrgName;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public Date getCataName() {
        return cataName;
    }

    public void setCataName(Date cataName) {
        this.cataName = cataName;
    }

    public String getTablejson() {
        return tablejson;
    }

    public void setTablejson(String tablejson) {
        this.tablejson = tablejson;
    }

    public String getResourSystemId() {
        return resourSystemId;
    }

    public void setResourSystemId(String resourSystemId) {
        this.resourSystemId = resourSystemId;
    }

    public String getSharedType() {
        return sharedType;
    }

    public void setSharedType(String sharedType) {
        this.sharedType = sharedType;
    }

    public String getSharedCondition() {
        return sharedCondition;
    }

    public void setSharedCondition(String sharedCondition) {
        this.sharedCondition = sharedCondition;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getUseDemand() {
        return useDemand;
    }

    public void setUseDemand(String useDemand) {
        this.useDemand = useDemand;
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
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

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }
}
