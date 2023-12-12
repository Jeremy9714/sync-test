package com.inspur.dsp.open.sync.down.catalog.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 9:42
 * @Version: 1.0
 */
@Data
@TableName("catalog_group_link")
public class CatalogGroupLink implements Serializable {

    private static final long serialVersionUID = 123L;

    /**
     * 关联ID，为主键
     */
    @TableId("link_id")
    private String linkId;

    /**
     * 分组id
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 目录id
     */
    @TableField("cata_id")
    private String cataId;

//    /**
//     * 同步时间戳
//     */
//    @TableField("operate_date")
//    private Date operateDate;
//
//    /**
//     * 新增：I
//     * 修改：U
//     * 删除：D
//     */
//    @TableField("operate_type")
//    private String operateType;
}
