package com.inspur.dsp.open.sync.down.catalog.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class CatalogInfoDto implements Serializable {
    private static final long serialVersionUID = 1957547L;

    /**
     * 目录id
     */
    @NotNull(message = "目录id不能为空！")
    private String cataId;

    /**
     * 目录编码
     */
    private String cataCode;

    /**
     * 目录名称
     */
    @NotNull(message = "目录名城不能为空！")
    private String cataTitle;

    /**
     * 目录logo
     */
    private String cataLogo;

    /**
     * 目录类型（10-政务目录 11-社会目录  12互联网目录）
     */
    @NotNull(message = "目录类型不能为空！")
    private Integer cataType;

    /**
     * 目录标签，多个用逗号（,）隔开
     */
    private String cataTags;

    /**
     * 目录描述
     */
    @NotNull(message = "目录描述不能为空！")
    private String description;

    /**
     * 数据使用说明文档ID
     */
    private String descDocId;

    /**
     * 部门编码
     */
    private String orgCode;

    /**
     * 部门类型
     */
    private Integer organType;

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 提供方内部部门
     */
    private String providerInternalOrgan;

    /**
     * 开放类型，1-无条件开放 2-有条件开放
     */
    @NotNull(message = "开放类型不能为空！")
    private String openType;

    /**
     * 开放条件，开放类型为有条件开放时填写
     */
    private String openCondition;

    /**
     * 资源格式，41-mysql 44-GBase等
     */
    private String resourceFormat;

    /**
     * 资源格式类型，1-电子文件 2-电子表格 3-数据库 4-图形图像 5-流媒体 6-自描述格式
     */
    private String resourceFormatType;

    /**
     * 更新频率，1-不定期 2-每天 3-每周 4-每月 5-每季度 6-没半年 7-每年 8-实时
     */
    @NotNull(message = "更新频率不能为空！")
    private String updateCycle;

    /**
     * 用户自定义频率，当更新频率为不定期时填写
     */
    private String updateCycleUser;

    /**
     * 接入类型，多种时以逗号分隔，1-库表 2-文件 3-API
     */
    private String joinType;

    /**
     * 开放方式，多种时以逗号分隔，1-数据集 2-文件集 3-API接口 4-地图
     */
    private String useType;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releasedTime;

    /**
     * 下架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlineTime;

    /**
     * 数据更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataUpdateTime;

    /**
     * 数据文件更新生成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datafileUpdateTime;

    /**
     * 地图配置更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datamapUpdateTime;

    /**
     * 服务更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataapiUpdateTime;

    /**
     * 目录来源：1-共享交换的目录，2-对接系统上报的目录，3-接入系统注册的目录，4-目录系统新增目录
     */
    @NotNull(message = "目录来源不能为空！")
    private String cataSource;

    /**
     * 行政区划码
     */
    @NotNull(message = "行政区划编码不能为空！")
    private String regionCode;

    /**
     * 行政区划名称
     */
    private String regionName;

    /**
     * 目录状态，（0：草稿；1：待接入审核 ；2：接入审核驳回；3：待汇聚； 4：待发布审核；5：发布审核驳回；6：待发布；7：已发布；8：已下架；-1：删除）
     */
    @NotNull(message = "目录状态不能为空")
    private Integer cataStatus;

    /**
     *
     */
    private String statusCn;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建时间不能为空！")
    private Date createTime;

    /**
     * 创建者id
     */
    @NotNull(message = "创建者id不能为空！")
    private String creatorId;

    /**
     * 创建者姓名
     */
    private String creatorName;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "更新时间不能为空！")
    private Date updateTime;

    /**
     * 更新者id
     */
    @NotNull(message = "更新者id不能为空！")
    private String updaterId;

    /**
     * 更新者姓名
     */
    private String updaterName;

    /**
     * 标准目录id
     */
    private String standardId;

}
