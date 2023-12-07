package com.inspur.dsp.open.sync.down.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddTableResourceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源id
     */
    @NotNull
    @JsonProperty("dataSourceIdcheck")
    private String dataSourceIdCheck;

    /**
     * 库表id
     */
    @NotNull
    private String[] itemId;

    /**
     * 目录id
     */
    @NotNull
    @JsonProperty("cataid")
    private String cataId;

    /**
     * 来自于数据文件导入 ，1：是
     */
    @JsonProperty("fromfiletable")
    private String fromFileTable;

    /**
     * 样例数据文件
     */
    @JsonProperty("modal_file_info")
    private String modalFileInfo;

    /**
     * 英文名称
     */
    @NotNull
    private String dataTableName;

    /**
     * 中文名称
     */
    @NotNull
    @JsonProperty("table_desc")
    private String tableDesc;


    public String getDataSourceIdCheck() {
        return dataSourceIdCheck;
    }

    public void setDataSourceIdCheck(String dataSourceIdCheck) {
        this.dataSourceIdCheck = dataSourceIdCheck;
    }

    public String[] getItemId() {
        return itemId;
    }

    public void setItemId(String[] itemId) {
        this.itemId = itemId;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getFromFileTable() {
        return fromFileTable;
    }

    public void setFromFileTable(String fromFileTable) {
        this.fromFileTable = fromFileTable;
    }

    public String getModalFileInfo() {
        return modalFileInfo;
    }

    public void setModalFileInfo(String modalFileInfo) {
        this.modalFileInfo = modalFileInfo;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }
}
