package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:29
 * @Version: 1.0
 */
@TableName("catalog_basicinfo")
public class CatalogBasicInfo implements Serializable {
    private static final long serialVersionUID = 89L;

    /**
     * 区共享平台目录主键
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 目录名称
     */
    @TableField("cata_title")
    private String cataTitle;

    /**
     * 目录编码
     */
    @TableField("cata_code")
    private String cataCode;

    /**
     * 目录分类编码
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 基础分类id（部委发布的部分目录属于某个基础信息资源目录分类，有此属性。地方发布的目录，此属性为空。）
     */
    @TableField("base_group_id")
    private String baseGroupId;

    /**
     * 主题分类id（部委发布的部分目录属于某个主题信息资源目录分类。地方发布的目录，此属性为空。
     */
    @TableField("theme_group_id")
    private String themeGroupId;

    /**
     * 信息资源摘要
     */
    @TableField("description")
    private String description;

    /**
     * 提供方名称
     */
    @TableField("register_org_name")
    private String registerOrgName;

    /**
     * 注册机构社会信用代码
     */
    @TableField("register_org_code")
    private String registerOrgCode;

    /**
     * 数据字典：共享类型
     */
    @TableField("shared_type")
    private String sharedType;

    /**
     * 数据字典：共享方式
     */
    @TableField("shared_mode")
    private String sharedMode;

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
    @TableField("use_demand")
    private String useDemand;

    /**
     * 开放条件
     */
    @TableField("open_condition")
    private String openCondition;

    /**
     * 更新周期
     */
    @TableField("update_cycle")
    private String updateCycle;

    /**
     * 数据字典：信息资源格式分类
     */
    @TableField("cata_type")
    private String cataType;

    /**
     * 版本号
     */
    @TableField("version")
    private String version;

    /**
     * 排序号
     */
    @TableField("order_num")
    private Integer orderNum;

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
     * 开放领域，数据字典：开放领域
     */
    @TableField("open_field")
    private String openField;

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getCataTitle() {
        return cataTitle;
    }

    public void setCataTitle(String cataTitle) {
        this.cataTitle = cataTitle;
    }

    public String getCataCode() {
        return cataCode;
    }

    public void setCataCode(String cataCode) {
        this.cataCode = cataCode;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBaseGroupId() {
        return baseGroupId;
    }

    public void setBaseGroupId(String baseGroupId) {
        this.baseGroupId = baseGroupId;
    }

    public String getThemeGroupId() {
        return themeGroupId;
    }

    public void setThemeGroupId(String themeGroupId) {
        this.themeGroupId = themeGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisterOrgName() {
        return registerOrgName;
    }

    public void setRegisterOrgName(String registerOrgName) {
        this.registerOrgName = registerOrgName;
    }

    public String getRegisterOrgCode() {
        return registerOrgCode;
    }

    public void setRegisterOrgCode(String registerOrgCode) {
        this.registerOrgCode = registerOrgCode;
    }

    public String getSharedType() {
        return sharedType;
    }

    public void setSharedType(String sharedType) {
        this.sharedType = sharedType;
    }

    public String getSharedMode() {
        return sharedMode;
    }

    public void setSharedMode(String sharedMode) {
        this.sharedMode = sharedMode;
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

    public String getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(String updateCycle) {
        this.updateCycle = updateCycle;
    }

    public String getCataType() {
        return cataType;
    }

    public void setCataType(String cataType) {
        this.cataType = cataType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public String getOpenField() {
        return openField;
    }

    public void setOpenField(String openField) {
        this.openField = openField;
    }
}
