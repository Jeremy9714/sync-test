package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:46
 * @Version: 1.0
 */
@TableName("catalog_fieldinfo")
public class CatalogFieldInfo implements Serializable {

    private static final long serialVersionUID = -10L;

    /**
     * 区共享平台信息项主键
     */
    @TableId
    @TableField("column_id")
    private String columnId;

    /**
     * 信息项名称
     */
    @TableField("column_name")
    private String columnName;

    /**
     * 区共享平台目录主键
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 数据字典：数据类型
     */
    @TableField("column_type")
    private String columnType;

    /**
     * 信息项长度
     */
    @TableField("column_length")
    private String columnLength;

    /**
     * 信息项精度
     */
    @TableField("column_prec")
    private String columnPrec;

    /**
     * 奥序号
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 数据敏感级别
     */
    @TableField("sensitive_level")
    private String sensitiveLevel;

    /**
     * 开放属性
     */
    @TableField("open_attribute")
    private String openAttribute;

    /**
     * 共享类型
     */
    @TableField("share_type")
    private String shareType;

    /**
     * 来源系统标识
     */
    @TableField("belong_app_sys")
    private String belongAppSys;

    /**
     * 不共享原因
     */
    @TableField("not_share_reason")
    private String notShareReason;

    /**
     * 开放门户信息项详情
     */
    @TableField("NormDetail")
    private String normDetail;

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnPrec() {
        return columnPrec;
    }

    public void setColumnPrec(String columnPrec) {
        this.columnPrec = columnPrec;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSensitiveLevel() {
        return sensitiveLevel;
    }

    public void setSensitiveLevel(String sensitiveLevel) {
        this.sensitiveLevel = sensitiveLevel;
    }

    public String getOpenAttribute() {
        return openAttribute;
    }

    public void setOpenAttribute(String openAttribute) {
        this.openAttribute = openAttribute;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getBelongAppSys() {
        return belongAppSys;
    }

    public void setBelongAppSys(String belongAppSys) {
        this.belongAppSys = belongAppSys;
    }

    public String getNotShareReason() {
        return notShareReason;
    }

    public void setNotShareReason(String notShareReason) {
        this.notShareReason = notShareReason;
    }

    public String getNormDetail() {
        return normDetail;
    }

    public void setNormDetail(String normDetail) {
        this.normDetail = normDetail;
    }
}
