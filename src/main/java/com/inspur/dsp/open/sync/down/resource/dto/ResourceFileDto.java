package com.inspur.dsp.open.sync.down.resource.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/7 17:14
 * @Version: 1.0
 */
@Data
public class ResourceFileDto implements Serializable {

    private static final long serialVersionUID = 1239L;

    /**
     * 资源id
     */
    private String id;

    /**
     * 资源名称
     */
    @NotNull(message = "资源名称不能为空！")
    private String resName;

    /**
     * 文件资源描述
     */
    @NotNull(message = "文件资源描述不能为空！")
    private String resDesc;

    /**
     * 挂接目录id
     */
    @NotNull(message = "挂接目录id不能为空！")
    private String cataId;

    /**
     * 挂接目录名称
     */
    @NotNull(message = "挂接目录名称不能为空！")
    private String cataName;

    /**
     * 部门编码
     */
    @NotNull(message = "部门编码不能为空！")
    private String orgId;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空！")
    private String orgName;

    /**
     * 区划编码
     */
    @NotNull(message = "区划编码不能为空！")
    private String regionCode;

    /**
     * 共享类型
     */
    @NotNull(message = "共享类型不能为空！")
    private Integer shareType;

    /**
     * 来源系统编码
     */
    private String fromSystemId;

    /**
     * 来源系统名称
     */
    private String fromSystemName;

    /**
     * 共享条件
     */
    private String shareCondition;

    /**
     * 开放类型
     */
    @NotNull(message = "开放类型不能为空！")
    private Integer openType;

    /**
     * 开放条件
     */
    private String openCondition;

    /**
     * 更新周期
     */
    @NotNull(message = "更新周期不能为空！")
    private Integer updateCycle;

    /**
     * 用户更新周期
     */
    private String customUpdateCycle;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空！")
    private String creatorId;

    /**
     * 用户名称
     */
    @NotNull(message = "用户名称不能为空！")
    private String creatorName;

    /**
     * 资源审核状态
     */
    private Integer status;

    /**
     * 资源开放状态
     */
    private Integer openStatus;

    /**
     * 文件名称
     */
    @NotNull(message = "文件名称不能为空！")
    private String fileName;

    /**
     * 文件大小
     */
    @NotNull(message = "文件大小不能为空！")
    private Integer fileSize;

    /**
     * 文件路径
     */
    @NotNull(message = "文件路径不能为空！")
    private String filePath;

    /**
     * 文件格式
     */
    @NotNull(message = "文件格式不能为空！")
    private String fileFormat;

}
