package com.inspur.dsp.open.sync.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:42
 * @Version: 1.0
 */
@TableName("catalog_category")
public class CatalogCategory implements Serializable {

    private static final long serialVersionUID = 123L;

    /**
     * 目录分类编码
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 目录分类名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 上级分类id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 排序编号
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 新增:I 修改:U 删除:D
     */
    @TableField("operate_type")
    private String operateType;

    /**
     * 同步时间戳
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
