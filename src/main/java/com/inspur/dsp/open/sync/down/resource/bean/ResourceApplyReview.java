package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/11/15 8:39
 * @Version: 1.0
 */
@TableName("resource_apply_review")
public class ResourceApplyReview implements Serializable {
    private final static long serialVersionUID = -23532L;

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 申请记录主键
     */
    @TableField("apply_id")
    private String applyId;

    /**
     * 步骤名称
     */
    @TableField("step_name")
    private String stepName;

    /**
     * 审核机构名称
     */
    @TableField("organ_name")
    private String organName;

    /**
     * 审核人
     */
    @TableField("revirew_user")
    private String revirewUser;

    /**
     * 审核时间
     */
    @TableField("revirew_date")
    private Date revirewDate;

    /**
     * 处理意见
     */
    private String opinion;

    /**
     * 审核状态 0驳回补正 1审核通过
     */
    @TableField("revirew_status")
    private Integer revirewStatus;

    /**
     * 区共享平台目录id
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 区共享平台资源id
     */
    @TableField("resource_id")
    private String resourceId;

    /**
     * 资源名城
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 操作时间
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 操作类型
     */
    @TableField("operate_type")
    private String operateType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getRevirewUser() {
        return revirewUser;
    }

    public void setRevirewUser(String revirewUser) {
        this.revirewUser = revirewUser;
    }

    public Date getRevirewDate() {
        return revirewDate;
    }

    public void setRevirewDate(Date revirewDate) {
        this.revirewDate = revirewDate;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getRevirewStatus() {
        return revirewStatus;
    }

    public void setRevirewStatus(Integer revirewStatus) {
        this.revirewStatus = revirewStatus;
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
