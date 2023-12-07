package com.inspur.dsp.open.sync.down.catalog.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogItem;
import com.inspur.dsp.open.sync.down.catalog.dao.CatalogItemDao;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogItemService;
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
public class CatalogItemServiceImpl extends ServiceImpl<CatalogItemDao, CatalogItem> implements CatalogItemService {

    private static final Logger log = LoggerFactory.getLogger(CatalogItemServiceImpl.class);

    @Autowired
    private CatalogItemDao catalogFieldinfoDao;

    @Override
    public List<CatalogItem> getCatalogFieldInfoByCataIds(List<String> cataIds) {
        return catalogFieldinfoDao.getCatalogFieldInfoByCataIds(cataIds);
    }
}
