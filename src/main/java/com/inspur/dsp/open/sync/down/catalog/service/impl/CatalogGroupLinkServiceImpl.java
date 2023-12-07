package com.inspur.dsp.open.sync.down.catalog.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.down.catalog.bean.CatalogGroupLink;
import com.inspur.dsp.open.sync.down.catalog.dao.CatalogGroupLinkDao;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogGroupLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/7 15:02
 * @Version: 1.0
 */
@Service
public class CatalogGroupLinkServiceImpl extends ServiceImpl<CatalogGroupLinkDao, CatalogGroupLink> implements CatalogGroupLinkService {

    @Autowired
    private CatalogGroupLinkDao catalogGroupLinkDao;

    @Override
    public boolean syncCatalogCategory() {
        return false;
    }
}
