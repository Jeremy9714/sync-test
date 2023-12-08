package com.inspur.dsp.open.sync.down.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 8:20
 * @Version: 1.0
 */
@Data
public class ResourceDatasourceDto implements Serializable {

    private static final long serialVersionUID = 89L;

    /**
     * 获取当前毫秒时间戳转为string类型
     */
    @NotNull(message = "key不能为空！")
    private String key;

    /**
     *数据源id
     */
    @NotNull(message = "数据源id不能为空！")
    private String id;

    /**
     * 数据源类型：frontenddb-前置库 originaldb-原始库 opendb-开放库
     */
    @NotNull(message = "数据源类型不能为空！")
    @JsonProperty("datasource_type")
    private String datasourceType;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空！")
    @JsonProperty("search_org_name")
    private String searchOrgName;

    /**
     * 部门id
     */
    @NotNull(message = "部门id不能为空！")
    @JsonProperty("search_org_id")
    private String searchOrgId;

    /**
     * 数据源名称
     */
    @NotNull(message = "数据源名称不能为空！")
    @JsonProperty("datasource_name")
    private String datasourceName;

    /**
     * 数据库类型
     */
    @NotNull(message = "数据库类型不能为空！")
    @JsonProperty("db_type")
    private String dbType;

    /**
     * 连接方式
     */
    @JsonProperty("jdbc_type")
    private String jdbcType;

    /**
     * 表schema
     */
    private String schema;

    /**
     * ip
     */
    @NotNull(message = "ip不能为空！")
    @JsonProperty("db_host")
    private String dbHost;

    /**
     * 端口
     */
    @NotNull(message = "端口不能为空！")
    @JsonProperty("db_port")
    private String dbPort;

    /**
     * 数据库名称
     */
    @NotNull(message = "数据库名称不能为空！")
    @JsonProperty("db_name")
    private String dbName;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空！")
    @JsonProperty("db_username")
    private String dbUsername;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空！")
    @JsonProperty("db_password")
    private String dbPassword;

    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空！")
    @JsonProperty("contact_name")
    private String contactName;

    /**
     * 联系人电话
     */
    @NotNull(message = "联系人电话不能为空！")
    @JsonProperty("contact_phone")
    private String contactPhone;

    /**
     * 描述
     */
    private String description;
}
