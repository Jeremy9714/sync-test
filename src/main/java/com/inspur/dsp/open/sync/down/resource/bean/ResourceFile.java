package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 8:56
 * @Version: 1.0
 */
@Data
@TableName("resource_file")
public class ResourceFile implements Serializable {

    private static final long serialVersionUID = 456456L;

    /**
     * 资源id
     */
    @TableField("id")
    private String id;

    /**
     * 资源名称
     */
    @TableField("res_name")
    private String resName;

    /**
     * 文件资源描述
     */
    @TableField("res_desc")
    private String resDesc;

    /**
     * 挂接目录id
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * 挂接目录名称
     */
    @TableField("cata_name")
    private String cataName;

    /**
     * 部门编码
     */
    @TableField("org_id")
    private String orgId;

    /**
     * 部门名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 区划编码
     */
    @TableField("region_code")
    private String regionCode;

    /**
     * 共享类型
     */
    @TableField("share_type")
    private Integer shareType;

    /**
     * 来源系统编码
     */
    @TableField("from_system_id")
    private String fromSystemId;

    /**
     * 来源系统名称
     */
    @TableField("from_system_name")
    private String fromSystemName;

    /**
     * 共享条件
     */
    @TableField("share_condition")
    private String shareCondition;

    /**
     * 开放类型
     */
    @TableField("open_type")
    private Integer openType;

    /**
     * 开放条件
     */
    @TableField("open_condition")
    private String openCondition;

    /**
     * 更新周期
     */
    @TableField("update_cycle")
    private Integer updateCycle;

    /**
     * 用户更新周期
     */
    @TableField("custom_update_cycle")
    private String customUpdateCycle;

    /**
     * 用户id
     */
    @TableField("creator_id")
    private String creatorId;

    /**
     * 用户名称
     */
    @TableField("creator_name")
    private String creatorName;

    /**
     * 资源审核状态
     */
    private Integer status;

    /**
     * 资源开放状态
     */
    @TableField("open_status")
    private Integer openStatus;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Integer fileSize;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件格式
     */
    @TableField("file_format")
    private String fileFormat;

    /**
     * 同步时间戳
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 操作类型：
     * 新增：I；
     * 修改：U;
     * 删除：D
     */
    @TableField("operate_type")
    private String operateType;
}
