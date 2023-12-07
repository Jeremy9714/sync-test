package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件资源附件列表信息表
 */
@TableName("resource_file_attachinfo")
public class ResourceFileAttachinfo implements Serializable {

    private static final long serialVersionUID = 123L;

    /**
     * 文件标识
     */
    @TableField("attachGuid")
    private String attachGuid;

    /**
     * 文件名称
     */
    @TableField("attachfilename")
    private String attachfilename;

    /**
     * 文件大小
     */
    @TableField("attachlength")
    private Integer attachlength;

    /**
     * 文件来源
     */
    @TableField("filetype")
    private String filetype;

    /**
     * 上传时间
     */
    @TableField("uploaddatetime")
    private Date uploaddatetime;

    /**
     * 文件集标识
     */
    @TableField("filesetguid")
    private String filesetguid;

    /**
     * 文件资源标识
     */
    @TableId
    @TableField("resourcefileguid")
    private String resourcefileguid;

    /**
     * 目录标识
     */
    @TableField("editionguid")
    private String editionguid;



    public String getAttachGuid() {
        return attachGuid;
    }

    public void setAttachGuid(String attachGuid) {
        this.attachGuid = attachGuid;
    }

    public String getAttachfilename() {
        return attachfilename;
    }

    public void setAttachfilename(String attachfilename) {
        this.attachfilename = attachfilename;
    }

    public Integer getAttachlength() {
        return attachlength;
    }

    public void setAttachlength(Integer attachlength) {
        this.attachlength = attachlength;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Date getUploaddatetime() {
        return uploaddatetime;
    }

    public void setUploaddatetime(Date uploaddatetime) {
        this.uploaddatetime = uploaddatetime;
    }

    public String getFilesetguid() {
        return filesetguid;
    }

    public void setFilesetguid(String filesetguid) {
        this.filesetguid = filesetguid;
    }

    public String getResourcefileguid() {
        return resourcefileguid;
    }

    public void setResourcefileguid(String resourcefileguid) {
        this.resourcefileguid = resourcefileguid;
    }

    public String getEditionguid() {
        return editionguid;
    }

    public void setEditionguid(String editionguid) {
        this.editionguid = editionguid;
    }
}
