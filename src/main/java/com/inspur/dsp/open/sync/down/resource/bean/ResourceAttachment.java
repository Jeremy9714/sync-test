package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 10:40
 * @Version: 1.0
 */
@Data
@TableName("resource_attachment")
public class ResourceAttachment implements Serializable {
    private static final long serialVersionUID = 87345L;

    /**
     * 附件id
     */
    @TableId("attach_guid")
    private String attachGuid;

    /**
     * 文件名称
     */
    @TableField("attach_filename")
    private String attachFilename;

    /**
     * 文件大小
     */
    @TableField("attach_length")
    private int attachLength;

    /**
     * 文件来源
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 上传时间
     */
    @TableField("upload_datetime")
    private Date uploadDatetime;

    /**
     * 文件集标识
     */
    @TableField("fileset_guid")
    private String filesetGuid;

    /**
     * 文件资源标识
     */
    @TableField("resource_file_guid")
    private String resourceFileGuid;

    /**
     * 目录标识
     */
    @TableField("edition_guid")
    private String editionGuid;

}
