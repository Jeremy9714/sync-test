package com.inspur.dsp.open.sync.down.catalog.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.catalog.dao.CatalogFieldInfoDao;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogFieldInfo;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogFieldInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 14:52
 * @Version: 1.0
 */
@Service
public class CatalogFieldInfoServiceImpl extends ServiceImpl<CatalogFieldInfoDao, CatalogFieldInfo> implements CatalogFieldInfoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogFieldInfoServiceImpl.class);

    @Autowired
    private CatalogFieldInfoDao catalogFieldinfoDao;

    @Override
    public List<CatalogFieldInfo> getCatalogFieldInfoByCataIds(List<String> cataIds) {
        return catalogFieldinfoDao.getCatalogFieldInfoByCataIds(cataIds);
    }
}
