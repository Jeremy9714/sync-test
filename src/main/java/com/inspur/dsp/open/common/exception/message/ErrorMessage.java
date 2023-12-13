package com.inspur.dsp.open.common.exception.message;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/13 14:13
 * @Version: 1.0
 */
public enum ErrorMessage implements ExceptionResponseMessage {
    NO_CATA_ID("cata-001", "目录id不能为空！"),
    NO_CATA_TITLE("cata-002", "目录名称不能为空！"),
    NO_CATA_TYPE("cata-003", "目录类型不能为空！"),
    NO_CATA_DESCRIPTION("cata-004", "目录描述不能为空！"),
    NO_CATA_OPEN_TYPE("cata-005", "目录开放类型不能为空！"),
    NO_CATA_UPDATE_CYCLE("cata-006", "目录更新频率不能为空！"),
    NO_CATA_SOURCE("cata-007", "目录来源不能为空！"),
    NO_CATA_REGION_CODE("cata-008", "目录区划编码不能为空！"),
    NO_CATA_STATUS("cata-009", "目录状态不能为空！"),
    NO_CATA_CREATE_TIME("cata-010", "目录创建时间不能为空！"),
    NO_CATA_CREATOR_ID("cata-011", "目录创建者id不能为空！"),
    NO_CATA_UPDATE_TIME("cata-012", "目录更新时间不能为空！"),
    NO_CATA_UPDATER_ID("cata-013", "目录更新者id不能为空！"),
    NO_CATAITEM_ITEM_ID("cata-014", "目录信息项id不能为空！"),
    NO_CATAITEM_NAME_CN("cata-015", "目录信息项中文名称不能为空！"),
    NO_CATAITEM_DATA_FORMAT("cata-016", "目录信息项数据格式不能为空！"),
    NO_CATAITEM_CATA_ID("cata-017", "目录信息项目录id不能为空！"),
    NO_CATAITEM_UPDATE_TIME("cata-018", "目录信息项更新时间不能为空！"),
    NO_CATAGROUP_LINK_ID("cata-019", "目录分组关联id不能为空！"),
    NO_CATAGROUP_CATA_ID("cata-020", "目录分组关联目录id不能为空！"),
    NO_CATAGROUP_ID("cata-021", "目录分组关联分组id不能为空！"),

    NO_RES_TABLE_DSID("res-001", "库表资源数据源id不能为空！"),
    NO_RES_TABLE_ITEMID("res-002", "库表资源库表id不能为空！"),
    NO_RES_TABLE_CATAID("res-003", "库表资源目录id不能为空！"),
    NO_RES_TABLE_NAME("res-004", "库表资源英文名称不能为空！"),
    NO_RES_TABLE_DESC("res-005", "库表资源中文名称不能为空！"),
    NO_RES_FILE_ID("res-006", "文件资源id不能为空！"),
    NO_RES_FILE_NAME("res-007", "文件资源名称不能为空！"),
    NO_RES_FILE_DESC("res-008", "文件资源描述不能为空！"),
    NO_RES_FILE_CATAID("res-009", "文件资源挂接目录id不能为空！"),
    NO_RES_FILE_CATA_NAME("res-010", "文件资源挂接目录名称不能为空！"),
    NO_RES_FILE_ORGID("res-011", "文件资源部门编码不能为空！"),
    NO_RES_FILE_ORG_NAME("res-012", "文件资源部门名称不能为空！"),
    NO_RES_FILE_REGION_CODE("res-013", "文件资源区划编码不能为空！"),
    NO_RES_FILE_SHARE_TYPE("res-014", "文件资源共享类型不能为空！"),
    NO_RES_FILE_OPEN_TYPE("res-015", "文件资源挂开放类型不能为空！"),
    NO_RES_FILE_UPDATE_CYCLE("res-016", "文件资源更新周期不能为空！"),
    NO_RES_FILE_CREATOR_ID("res-017", "文件资源创建者id不能为空！"),
    NO_RES_FILE_CREATOR_NAME("res-018", "文件资源创建者姓名不能为空！"),
    NO_RES_FILE_STATUS("res-019", "文件资源状态不能为空！"),
    NO_RES_FILE_FILE_NAME("res-020", "文件资源文件名称不能为空！"),
    NO_RES_FILE_FILE_PATH("res-021", "文件资源文件id不能为空！"),
    NO_RES_FILE_FILE_SIZE("res-022", "文件资源文件大小不能为空！"),
    NO_RES_FILE_FILE_FORMAT("res-023", "文件资源文件格式不能为空！")
    ;

    private final String code;

    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getStatusCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
