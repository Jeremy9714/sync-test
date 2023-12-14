package com.inspur.dsp.open.sync.down.catalog.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:46
 * @Version: 1.0
 */
@Data
@TableName("catalog_item")
public class CatalogItem implements Serializable {

    private static final long serialVersionUID = -10L;

    /**
     * 详细项主键
     */
    @TableId("item_id")
    private String itemId;

    /**
     * 英文名称
     */
    @TableField("name_en")
    private String nameEn;

    /**
     * 中文名称
     */
    @TableField("name_cn")
    private String nameCn;

    /**
     * 字段在数据目录中的业务描述
     */
    @TableField("description")
    private String description;

    /**
     * 数据格式
     */
    @TableField("data_format")
    private String dataFormat;

    /**
     * 字段长度
     */
    @TableField("length")
    private String length;

    /**
     * 表明是否为主键(0:否;1:是)
     */
    @TableField("isprimarykey")
    private Integer isprimarykey;

    /**
     * 是否为空，0:可以为空；1：不可为空
     */
    @TableField("not_null")
    private Integer notNull;

    /**
     * 是否展现项，1-是0-否
     */
    @TableField("is_list")
    private Integer isList;

    /**
     * 是否搜索项，1-是0-否
     */
    @TableField("is_search")
    private Integer isSearch;

    /**
     * 是否排序项，1-是0-否
     */
    @TableField("is_order")
    private Integer isOrder;

    /**
     * 是否统计项，1-是0-否
     */
    @TableField("is_count")
    private Integer isCount;

    /**
     * 是否统计项，1-是0-否
     */
    @TableField("is_addr")
    private Integer isAddr;

    /**
     * 展现优先级，顺序号
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 是否脱敏，0否1是
     */
    @TableField("is_masking")
    private Integer isMasking;

    /**
     * 脱敏规则，id,描述
     */
    @TableField("rules_des")
    private String rulesDes;

    /**
     * 目录ID
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 来源目录id
     */
    @TableField("origin_cata_id")
    private String originCataId;

    /**
     * 来源目录标题
     */
    @TableField("origin_cata_title")
    private String originCataTitle;

    /**
     * 来源信息项id
     */
    @TableField("origin_item_id")
    private String originItemId;

    /**
     * 来源信息名称
     */
    @TableField("origin_item_name")
    private String originItemName;

}
