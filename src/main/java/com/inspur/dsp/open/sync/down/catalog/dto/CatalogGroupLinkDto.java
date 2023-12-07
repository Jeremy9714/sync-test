package com.inspur.dsp.open.sync.down.catalog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/7 16:23
 * @Version: 1.0
 */
@Data
public class CatalogGroupLinkDto implements Serializable {
    private static final long serialVersionUID = -12L;

    /**
     * 关联id，为主键
     */
    @NotNull(message = "关联id不能为空！")
    private String linkId;

    /**
     * 目录id
     */
    private String cataId;

    /**
     * 分组id
     */
    private String groupId;
}
