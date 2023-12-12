package com.inspur.dsp.open.sync.down.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/7 17:08
 * @Version: 1.0
 */
@Data
public class ResourceTableDto implements Serializable {
    private static final long serialVersionUID = 123L;

    /**
     * 数据源id
     */
    @NotNull(message="数据源id不能为空！")
    private String dataSourceIdcheck;

    /**
     * 库表id
     */
    @NotNull(message = "库表id不能为空！")
    private String[] itemId;

    /**
     * 目录id
     */
    @NotNull(message = "目录id不能为空！")
    private String cataid;

    /**
     * 来自于数据文件导入 ，1：是
     */
    private String fromfiletable;

    /**
     * 样例数据文件
     */
    @JsonProperty("modal_file_info")
    private String modalFileInfo;

    /**
     * 英文名称
     */
    @NotNull(message="英文名称不能为空！")
    private String dataTableName;

    /**
     * 中文名称
     */
    @NotNull(message = "中文名称不能为空！")
    @JsonProperty("table_desc")
    private String tableDesc;
}
