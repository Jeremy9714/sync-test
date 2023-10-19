package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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

    @TableField("column_id")
    private String columnId;

    @TableField("column_name")
    private String columnName;

    @TableField("column_id")
    private String cataId;

    @TableField("column_type")
    private String columnType;

    @TableField("column_length")
    private String columnLength;

    @TableField("column_prec")
    private String columnPrec;

    @TableField("operate_date")
    private Date operateDate;

    @TableField("operate_type")
    private String operateType;

    @TableField("order_num")
    private Integer orderNum;

    @TableField("remark")
    private String remark;

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
}
