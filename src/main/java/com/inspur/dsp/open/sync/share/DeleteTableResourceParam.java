package com.inspur.dsp.open.sync.share;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class DeleteTableResourceParam {
    /**
     * 数据源id
     */
    @NotNull
    @JsonProperty("datasource_id")
    private String datasourceId;
    /**
     * 库表id
     */
    @NotNull
    @JsonProperty("table_id")
    private String tableId;
    /**
     * 目录id
     */
    @NotNull
    @JsonProperty("cataid")
    private String cataId;



    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }
}
