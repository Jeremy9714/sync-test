package com.inspur.dsp.open.sync.down.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DeleteTableResourceParam implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private String[] itemId;
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
}
