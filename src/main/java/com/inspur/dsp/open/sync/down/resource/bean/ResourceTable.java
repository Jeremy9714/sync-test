package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/8 9:00
 * @Version: 1.0
 */
@Data
@TableName("resource_table")
public class ResourceTable implements Serializable {
    private static final long serialVersionUID = 23523L;

    /**
     * 数据源id
     */
    @TableField("datasource_id_check")
    private String dataSourceIdCheck;

    /**
     * 库表id
     */
    @TableField("item_id")
    private String itemId;

    /**
     * 目录id
     */
    @TableField("cata_id")
    private String cataid;

//    /**
//     * 来自于数据文件导入 ，1：是
//     */
//    @TableField("from_file_table")
//    private String fromfiletable;
//
//    /**
//     * 样例数据文件
//     */
//    @TableField("modal_file_info")
//    private String modalFileInfo;

    /**
     * 英文名称
     */
    @TableField("data_table_name")
    private String dataTableName;

    /**
     * 中文名称
     */
    @TableField("table_desc")
    private String tableDesc;

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
