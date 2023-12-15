package com.inspur.dsp.open.sync.down.resource.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/15 10:11
 * @Version: 1.0
 */
@Data
public class OpenResourceFileDto implements Serializable {
    private static final long serialVersionUID = 4564563L;

    private String fileId;

    private String fileName;

    private String fileDescription;

    private String fileFormat;

    private Integer fileSource;

    private Boolean analysis;

    private Integer fileSize;

    private Integer dataCount;

    private String idInRc;

    private String cataId;

    private String updaterId;

    private Date updateTime;

    private Integer status;

    private String cataTitle;

    private String regionCode;

    private String regionName;

    private String orgCode;

    private String orgName;
}
