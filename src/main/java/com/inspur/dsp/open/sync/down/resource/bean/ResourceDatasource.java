package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 8:49
 * @Version: 1.0
 */
@Data
@TableName("resource_datasource")
public class ResourceDatasource implements Serializable {
    private static final long serialVersionUID = -345L;

    /**
     *数据源id
     */
    @TableId("id")
    private String id;

    /**
     * 数据源类型：frontenddb-前置库 originaldb-原始库 opendb-开放库
     */
    @TableField("datasource_type")
    private String datasourceType;

    /**
     * 部门名称
     */
    @TableField("search_org_name")
    private String searchOrgName;

    /**
     * 部门id
     */
    @TableField("search_org_id")
    private String searchOrgId;

    /**
     * 数据源名称
     */
    @TableField("datasource_name")
    private String datasourceName;

    /**
     * 数据库类型
     */
    @TableField("db_type")
    private String dbType;

    /**
     * 连接方式
     */
    @TableField("jdbc_type")
    private String jdbcType;

    /**
     * 表schema
     */
    private String schema;

    /**
     * ip
     */
    @TableField("db_host")
    private String dbHost;

    /**
     * 端口
     */
    @TableField("db_port")
    private String dbPort;

    /**
     * 数据库名称
     */
    @TableField("db_name")
    private String dbName;

    /**
     * 用户名
     */
    @TableField("db_username")
    private String dbUsername;

    /**
     * 密码
     */
    @TableField("db_password")
    private String dbPassword;

    /**
     * 联系人
     */
    @TableField("contact_name")
    private String contactName;

    /**
     * 联系人电话
     */
    @TableField("contact_phone")
    private String contactPhone;

    /**
     * 描述
     */
    private String description;

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
