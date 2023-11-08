package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件资源下行表
 */
@TableName("resource_file")
public class ResourceFile implements Serializable {

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
     * 社会信用代码
     */
    @TableField("credit_code")
    private String creditCode;

    /**
     * 区共享平台目录主键
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 业务系统
     */
    @TableField("app_id")
    private Date appId;

    /**
     * 主题分类
     */
    @TableField("business")
    private String business;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public Date getAppId() {
        return appId;
    }

    public void setAppId(Date appId) {
        this.appId = appId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
