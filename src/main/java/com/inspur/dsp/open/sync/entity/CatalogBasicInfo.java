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
     * 不可发布理由
     */
    @TableField("not_publish_explain")
    private String notPublishExplain;

    /**
     * 是否为可发布目录
     */
    @TableField("is_publish")
    private String isPublish;

    /**
     * 是否电子证照
     */
    @TableField("is_certificate")
    private String isCertificate;

    /**
     * 实施清单编码
     */
    @TableField("task_code")
    private String taskCode;

    /**
     * 数据所属事项名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 是否绑定事项
     */
    @TableField("is_binding_task")
    private String isBindingTask;

    /**
     * 提供渠道
     */
    @TableField("provide_channel")
    private String provideChannel;

    /**
     * 应用场景
     */
    @TableField("app_scene")
    private String appScene;

    /**
     * 其他应用场景
     */
    @TableField("other_app_scene")
    private String otherAppScene;

    /**
     * 国家所属领域
     */
    @TableField("nation_field")
    private String nationField;

    /**
     * 其他国家所属领域
     */
    @TableField("other_nation_field")
    private String otherNationField;

    /**
     * 办理结果
     */
    @TableField("approval_result")
    private String approvalResult;

    /**
     * 政府数据目录编码
     */
    @TableField("gov_catalog_encode")
    private String govCatalogEncode;

    /**
     * 不更新理由
     */
    @TableField("not_update_reason")
    private String notUpdateReason;

    /**
     * 业务办理项编码
     */
    @TableField("yw_code")
    private String ywCode;

    /**
     * 系统的参数数据
     */
    @TableField("event_data")
    private String eventData;

    /**
     * 开放门户更新频率
     */
    @TableField("openFrequency")
    private String openFrequency;

    /**
     * 开放门户其他更新频率
     */
    @TableField("openOtherFrequency")
    private String openOtherFrequency;

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

    public String getNotPublishExplain() {
        return notPublishExplain;
    }

    public void setNotPublishExplain(String notPublishExplain) {
        this.notPublishExplain = notPublishExplain;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getIsCertificate() {
        return isCertificate;
    }

    public void setIsCertificate(String isCertificate) {
        this.isCertificate = isCertificate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getIsBindingTask() {
        return isBindingTask;
    }

    public void setIsBindingTask(String isBindingTask) {
        this.isBindingTask = isBindingTask;
    }

    public String getProvideChannel() {
        return provideChannel;
    }

    public void setProvideChannel(String provideChannel) {
        this.provideChannel = provideChannel;
    }

    public String getAppScene() {
        return appScene;
    }

    public void setAppScene(String appScene) {
        this.appScene = appScene;
    }

    public String getOtherAppScene() {
        return otherAppScene;
    }

    public void setOtherAppScene(String otherAppScene) {
        this.otherAppScene = otherAppScene;
    }

    public String getNationField() {
        return nationField;
    }

    public void setNationField(String nationField) {
        this.nationField = nationField;
    }

    public String getOtherNationField() {
        return otherNationField;
    }

    public void setOtherNationField(String otherNationField) {
        this.otherNationField = otherNationField;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getGovCatalogEncode() {
        return govCatalogEncode;
    }

    public void setGovCatalogEncode(String govCatalogEncode) {
        this.govCatalogEncode = govCatalogEncode;
    }

    public String getNotUpdateReason() {
        return notUpdateReason;
    }

    public void setNotUpdateReason(String notUpdateReason) {
        this.notUpdateReason = notUpdateReason;
    }

    public String getYwCode() {
        return ywCode;
    }

    public void setYwCode(String ywCode) {
        this.ywCode = ywCode;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getOpenFrequency() {
        return openFrequency;
    }

    public void setOpenFrequency(String openFrequency) {
        this.openFrequency = openFrequency;
    }

    public String getOpenOtherFrequency() {
        return openOtherFrequency;
    }

    public void setOpenOtherFrequency(String openOtherFrequency) {
        this.openOtherFrequency = openOtherFrequency;
    }
}
