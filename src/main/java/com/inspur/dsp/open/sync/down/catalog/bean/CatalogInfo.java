package com.inspur.dsp.open.sync.down.catalog.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:29
 * @Version: 1.0
 */
@Data
@TableName("catalog_info")
public class CatalogInfo implements Serializable {
    private static final long serialVersionUID = 89L;

    /**
     * 区共享平台目录主键
     */
    @TableId("cata_id")
    private String cataId;

    /**
     * 目录编码
     */
    @TableField("cata_code")
    private String cataCode;

    /**
     * 目录名称
     */
    @TableField("cata_title")
    private String cataTitle;

    /**
     * 目录logo
     */
    @TableField("cata_logo")
    private String cataLogo;

    /**
     * 目录类型（10-政务目录11-社会目录12互联网目录）
     */
    @TableField("cata_type")
    private int cataType;

    /**
     * 目录标签，多个用逗号（,）隔开
     */
    @TableField("cata_tags")
    private String cataTags;

    /**
     * 信息资源摘要
     */
    @TableField("description")
    private String description;

    /**
     * 数据使用说明文档ID
     */
    @TableField("desc_doc_id")
    private String descDocId;

    /**
     * 部门编码
     */
    @TableField("org_code")
    private String orgCode;

    /**
     * 部门类型
     */
    @TableField("organ_type")
    private int organType;

    /**
     * 部门名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * provider_internal_organ
     */
    @TableField("provider_interal_organ")
    private String providerInternalOrgan;

    /**
     * 开放类型，1-无条件开放2-有条件开放
     */
    @TableField("open_type")
    private String openType;

    /**
     * 开放条件，开放类型为有条件开放时填写
     */
    @TableField("open_condition")
    private String openCondition;

    /**
     * 资源格式，41-mysql44-GBase等
     */
    @TableField("resource_format")
    private String resourceFormat;

    /**
     * 资源格式类型，1-电子文件2-电子表格3-数据库4-图形图像5-流媒体6-自描述格式
     */
    @TableField("resource_format_type")
    private String resourceFormatType;

    /**
     * 更新频率，1-不定期2-每天3-每周4-每月5-每季度6-没半年7-每年8-实时
     */
    @TableField("update_cycle")
    private String updateCycle;

    /**
     * 用户自定义频率，当更新频率为不定期时填写
     */
    @TableField("update_cycle_user")
    private String updateCycleUser;

    /**
     * 接入类型，多种时以逗号分隔，1-库表2-文件3-API
     */
    @TableField("join_type")
    private String joinType;

    /**
     * 开放方式，多种时以逗号分隔，1-数据集2-文件集3-API接口4-地图
     */
    @TableField("user_type")
    private String useType;

    /**
     * 发布时间
     */
    @TableField("released_time")
    private String releasedTime;

    /**
     * 下架时间
     */
    @TableField("offline_time")
    private String offlineTime;

    /**
     * 数据更新时间
     */
    @TableField("data_update_time")
    private String dataUpdateTime;

    /**
     * 数据文件更新生成时间
     */
    @TableField("datafile_update_time")
    private String datafileUpdateTime;

    /**
     * 地图配置更新时间
     */
    @TableField("datamap_update_time")
    private String datamapUpdateTime;

    /**
     * 服务更新时间
     */
    @TableField("daatapi_update_time")
    private String dataapiUpdateTime;

    /**
     * 目录来源：1-共享交换的目录，2-对接系统上报的目录，3-接入系统注册的目录，4-目录系统新增目录
     */
    @TableField("cata_source")
    private String cataSource;

    /**
     * 行政区划码
     */
    @TableField("region_code")
    private String regionCode;

    /**
     * 行政区划名称
     */
    @TableField("region_name")
    private String regionName;

    /**
     * 目录状态，（0：草稿；1：待接入审核；2：接入审核驳回；3：待汇聚；4：待发布审核；5：发布审核驳回；6：待发布；7：已发布；8：已下架；-1：删除）|
     */
    @TableField("cata_status")
    private int cataStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 创建者id
     */
    @TableField("creator_id")
    private String creatorId;

    /**
     * 创建者姓名
     */
    @TableField("creator_name")
    private String creatorName;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 更新者id
     */
    @TableField("updatre_id")
    private String updaterId;

    /**
     * 更新者姓名
     */
    @TableField("updater_name")
    private String updaterName;

    /**
     * 标准目录id
     */
    @TableField("standard_id")
    private String standardId;

    /**
     * 同步时间戳
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 新增：I
     * 修改：U
     * 删除：D
     */
    @TableField("operate_type")
    private String operateType;

}
