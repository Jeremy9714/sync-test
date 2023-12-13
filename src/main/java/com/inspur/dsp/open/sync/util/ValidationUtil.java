package com.inspur.dsp.open.sync.util;

import com.inspur.dsp.open.common.exception.CommonException;
import com.inspur.dsp.open.common.exception.message.ErrorMessage;
import com.inspur.dsp.open.sync.down.catalog.dto.CatalogGroupLinkDto;
import com.inspur.dsp.open.sync.down.catalog.dto.CatalogInfoDto;
import com.inspur.dsp.open.sync.down.catalog.dto.CatalogItemDto;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceFileDto;
import com.inspur.dsp.open.sync.down.resource.dto.ResourceTableDto;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtil {
    public static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static boolean validate(Object object) {
        return validator.validate(object).isEmpty();
    }

    public static void verifyCatalogInfoDto(CatalogInfoDto catalogInfoDto) {
        if (StringUtils.isNotBlank(catalogInfoDto.getCataId())) {
            throw new CommonException(ErrorMessage.NO_CATA_ID);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getCataTitle())) {
            throw new CommonException(ErrorMessage.NO_CATA_TITLE);
        }

        if (null == catalogInfoDto.getCataType()) {
            throw new CommonException(ErrorMessage.NO_CATA_TYPE);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getDescription())) {
            throw new CommonException(ErrorMessage.NO_CATA_DESCRIPTION);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getOpenType())) {
            throw new CommonException(ErrorMessage.NO_CATA_OPEN_TYPE);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getUpdateCycle())) {
            throw new CommonException(ErrorMessage.NO_CATA_UPDATE_CYCLE);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getCataSource())) {
            throw new CommonException(ErrorMessage.NO_CATA_SOURCE);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getRegionCode())) {
            throw new CommonException(ErrorMessage.NO_CATA_REGION_CODE);
        }

        if (null == catalogInfoDto.getCataSource()) {
            throw new CommonException(ErrorMessage.NO_CATA_STATUS);
        }

        if (null == catalogInfoDto.getCreateTime()) {
            throw new CommonException(ErrorMessage.NO_CATA_CREATE_TIME);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getCreatorId())) {
            throw new CommonException(ErrorMessage.NO_CATA_CREATOR_ID);
        }

        if (null == catalogInfoDto.getUpdateTime()) {
            throw new CommonException(ErrorMessage.NO_CATA_UPDATE_TIME);
        }

        if (StringUtils.isNotBlank(catalogInfoDto.getUpdaterId())) {
            throw new CommonException(ErrorMessage.NO_CATA_UPDATER_ID);
        }

    }

    public static void verifyCatalogItemDto(CatalogItemDto catalogItemDto) {
        if (StringUtils.isNotBlank(catalogItemDto.getItemId())) {
            throw new CommonException(ErrorMessage.NO_CATAITEM_ITEM_ID);
        }

        if (StringUtils.isNotBlank(catalogItemDto.getNameCn())) {
            throw new CommonException(ErrorMessage.NO_CATAITEM_NAME_CN);
        }

        if (StringUtils.isNotBlank(catalogItemDto.getDataFormat())) {
            throw new CommonException(ErrorMessage.NO_CATAITEM_DATA_FORMAT);
        }

        if (StringUtils.isNotBlank(catalogItemDto.getCataId())) {
            throw new CommonException(ErrorMessage.NO_CATAITEM_CATA_ID);
        }

        if (null == catalogItemDto.getUpdateTime()) {
            throw new CommonException(ErrorMessage.NO_CATAITEM_UPDATE_TIME);
        }
    }

    public static void verifyCatalogGroupLinkDto(CatalogGroupLinkDto catalogGroupLinkDto) {
        if (StringUtils.isBlank(catalogGroupLinkDto.getLinkId())) {
            throw new CommonException(ErrorMessage.NO_CATAGROUP_LINK_ID);
        }

        if (StringUtils.isBlank(catalogGroupLinkDto.getCataId())) {
            throw new CommonException(ErrorMessage.NO_CATAGROUP_CATA_ID);
        }

        if (StringUtils.isBlank(catalogGroupLinkDto.getGroupId())) {
            throw new CommonException(ErrorMessage.NO_CATAGROUP_ID);
        }
    }

    public static void verifyResourceTableDto(ResourceTableDto resourceTableDto) {
        if (StringUtils.isBlank(resourceTableDto.getDataSourceIdcheck())) {
            throw new CommonException(ErrorMessage.NO_RES_TABLE_DSID);
        }

        if (null == resourceTableDto.getItemId() || resourceTableDto.getItemId().length == 0) {
            throw new CommonException(ErrorMessage.NO_RES_TABLE_ITEMID);
        }

        if (StringUtils.isBlank(resourceTableDto.getCataid())) {
            throw new CommonException(ErrorMessage.NO_RES_TABLE_CATAID);
        }

        if (StringUtils.isBlank(resourceTableDto.getDataTableName())) {
            throw new CommonException(ErrorMessage.NO_RES_TABLE_NAME);
        }

        if (StringUtils.isBlank(resourceTableDto.getTableDesc())) {
            throw new CommonException(ErrorMessage.NO_RES_TABLE_DESC);
        }
    }

    public static void verifyResourceFileDto(ResourceFileDto resourceFileDto) {
        if (StringUtils.isBlank(resourceFileDto.getId())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_ID);
        }

        if (StringUtils.isBlank(resourceFileDto.getResName())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_NAME);
        }

        if (StringUtils.isBlank(resourceFileDto.getResDesc())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_DESC);
        }

        if (StringUtils.isBlank(resourceFileDto.getCataId())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_CATAID);
        }

        if (StringUtils.isBlank(resourceFileDto.getCataName())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_CATA_NAME);
        }

        if (StringUtils.isBlank(resourceFileDto.getOrgId())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_ORGID);
        }

        if (StringUtils.isBlank(resourceFileDto.getOrgName())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_ORG_NAME);
        }

        if (StringUtils.isBlank(resourceFileDto.getRegionCode())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_REGION_CODE);
        }

        if (null == resourceFileDto.getShareType()) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_SHARE_TYPE);
        }

        if (null == resourceFileDto.getOpenType()) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_OPEN_TYPE);
        }

        if (null == resourceFileDto.getUpdateCycle()) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_UPDATE_CYCLE);
        }

        if (StringUtils.isBlank(resourceFileDto.getCreatorId())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_CREATOR_ID);
        }

        if (StringUtils.isBlank(resourceFileDto.getCreatorName())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_CREATOR_NAME);
        }

        if (null == resourceFileDto.getStatus()) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_STATUS);
        }

        if (StringUtils.isBlank(resourceFileDto.getFileName())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_FILE_NAME);
        }

        if (null == resourceFileDto.getFileSize()) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_FILE_SIZE);
        }

        if (StringUtils.isBlank(resourceFileDto.getFilePath())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_FILE_PATH);
        }

        if (StringUtils.isBlank(resourceFileDto.getFileFormat())) {
            throw new CommonException(ErrorMessage.NO_RES_FILE_FILE_FORMAT);
        }

    }
}
