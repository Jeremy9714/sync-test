package com.inspur.dsp.open.sync.down.catalog.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class CatalogItemDto implements Serializable {
    private static final long serialVersionUID = 1734534L;

    /**
     * 信息项ID
     */
    @NotNull(message = "信息项id不能为空！")
    private String itemId;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 中文名称
     */
    @NotNull(message = "中文名称长度不能空！")
    private String nameCn;

    /**
     * 字段在数据目录中的业务描述
     */
    private String description;

    /**
     * 数据格式
     */
    @NotNull(message = "数据格式不能为空！")
    private String dataFormat;

    /**
     * 长度
     */
    private String length;

    /**
     * 表明是否为主键(0:否;1:是)
     */
    private Integer isprimarykey;

    /**
     * 是否为空，0:可以为空；1：不可为空
     */
    private Integer notNull;

    /**
     * 是否展现项，1-是 0-否
     */
    private Integer isList;

    /**
     * 是否搜索项，1-是 0-否
     */
    private Integer isSearch;

    /**
     * 是否排序项，1-是 0-否
     */
    private Integer isOrder;

    /**
     * 是否统计项，1-是 0-否
     */
    private Integer isCount;

    /**
     * 是否统计项，1-是 0-否
     */
    private Integer isAddr;

    /**
     * 展现优先级，顺序号
     */
    private Integer orderId;

    /**
     * 是否脱敏， 0否1是
     */
    private Integer isMasking;

    /**
     * 脱敏规则， id,描述
     */
    private String rulesDes;

    /**
     * 目录ID
     */
    @NotNull(message = "目录id不能为空！")
    private String cataId;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "更新时间不能为空！")
    private Date updateTime;

    /**
     * 来源目录id
     */
    private String originCataId;

    /**
     * 来源目录标题
     */
    private String originCataTitle;

    /**
     * 来源信息项id
     */
    private String originItemId;

    /**
     * 来源信息名称
     */
    private String originItemName;

}
