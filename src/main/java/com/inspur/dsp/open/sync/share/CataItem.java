package com.inspur.dsp.open.sync.share;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CataItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 信息项ID
     */
    @NotNull
    private String itemId;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 中文名称
     */
    @NotNull
    private String nameCn;

    /**
     * 字段在数据目录中的业务描述
     */
    private String description;

    /**
     * 数据格式
     */
    @NotNull
    private String dataFormat;

    /**
     * 长度
     */
    private String length;

    /**
     * 表明是否为主键(0:否;1:是)
     */
    private String isprimarykey;

    /**
     * 是否为空，0:可以为空；1：不可为空
     */
    private String notNull;

    /**
     * 是否展现项，1-是 0-否
     */
    private String isList;

    /**
     * 是否搜索项，1-是 0-否
     */
    private String isSearch;

    /**
     * 是否排序项，1-是 0-否
     */
    private String isOrder;

    /**
     * 是否统计项，1-是 0-否
     */
    private String isCount;

    /**
     * 是否统计项，1-是 0-否
     */
    private String isAddr;

    /**
     * 展现优先级，顺序号
     */
    private String orderId;

    /**
     * 是否脱敏， 0否1是
     */
    private String isMasking;

    /**
     * 脱敏规则， id,描述
     */
    private String rulesDes;

    /**
     * 目录ID
     */
    private String cataId;

    /**
     * 更新时间
     */
    @NotNull
    private String updateTime;

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


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getIsprimarykey() {
        return isprimarykey;
    }

    public void setIsprimarykey(String isprimarykey) {
        this.isprimarykey = isprimarykey;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getIsList() {
        return isList;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getIsCount() {
        return isCount;
    }

    public void setIsCount(String isCount) {
        this.isCount = isCount;
    }

    public String getIsAddr() {
        return isAddr;
    }

    public void setIsAddr(String isAddr) {
        this.isAddr = isAddr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIsMasking() {
        return isMasking;
    }

    public void setIsMasking(String isMasking) {
        this.isMasking = isMasking;
    }

    public String getRulesDes() {
        return rulesDes;
    }

    public void setRulesDes(String rulesDes) {
        this.rulesDes = rulesDes;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOriginCataId() {
        return originCataId;
    }

    public void setOriginCataId(String originCataId) {
        this.originCataId = originCataId;
    }

    public String getOriginCataTitle() {
        return originCataTitle;
    }

    public void setOriginCataTitle(String originCataTitle) {
        this.originCataTitle = originCataTitle;
    }

    public String getOriginItemId() {
        return originItemId;
    }

    public void setOriginItemId(String originItemId) {
        this.originItemId = originItemId;
    }

    public String getOriginItemName() {
        return originItemName;
    }

    public void setOriginItemName(String originItemName) {
        this.originItemName = originItemName;
    }
}
