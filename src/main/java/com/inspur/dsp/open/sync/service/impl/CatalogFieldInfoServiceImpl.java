package com.inspur.dsp.open.sync.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inspur.dsp.open.sync.constant.ServiceConstant;
import com.inspur.dsp.open.sync.dao.CatalogFieldInfoDao;
import com.inspur.dsp.open.sync.entity.CatalogFieldInfo;
import com.inspur.dsp.open.sync.service.CatalogFieldInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
