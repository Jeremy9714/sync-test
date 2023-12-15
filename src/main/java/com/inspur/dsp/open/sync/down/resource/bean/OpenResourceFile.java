package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/15 10:35
 * @Version: 1.0
 */
@Data
@TableName("resource_file_v2")
public class OpenResourceFile implements Serializable {
    private static final long serialVersionUID = -34563L;

    @TableId("file_id")
    private String fileId;

    @TableField("file_name")
    private String fileName;

    @TableField("file_description")
    private String fileDescription;

    @TableField("file_format")
    private String fileFormat;

    @TableField("file_source")
    private Integer fileSource;

    @TableField("is_analysis")
    private Boolean analysis;

    @TableField("file_size")
    private Integer fileSize;

    @TableField("data_count")
    private Integer dataCount;

    @TableField(exist = false)
    private String idInRc;

    @TableField("cata_id")
    private String cataId;

    @TableField("updater_id")
    private String updaterId;

    @TableField("update_time")
    private Date updateTime;

    @TableField("status")
    private Integer status;

    @TableField("cata_title")
    private String cataTitle;

    @TableField("region_code")
    private String regionCode;

    @TableField("region_name")
    private String regionName;

    @TableField("org_code")
    private String orgCode;

    @TableField("org_name")
    private String orgName;

    @TableField("file_path")
    private String filePath;

    @TableField("operate_date")
    private Date operateDate;

    @TableField("operate_type")
    private String operateType;
}
