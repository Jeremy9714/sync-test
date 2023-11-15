package com.inspur.dsp.open.sync.schedule;

import com.inspur.dsp.open.sync.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/10/19 10:04
 * @Version: 1.0
 */
@ConditionalOnProperty(name = "schedule.enable", havingValue = "1")
@EnableScheduling
@Configuration
public class SyncDataTask {

    private static final Logger log = LoggerFactory.getLogger(SyncDataTask.class);

    @Autowired
    private CatalogCategoryService catalogCategoryService;

    @Autowired
    private CatalogBasicInfoService catalogBasicinfoService;

    @Autowired
    private ResourceFileService resourceFileService;

    @Autowired
    private ResourceOperationserviceService resourceOperationserviceService;

    @Autowired
    private ResourceTableService resourceTableService;

    @Autowired
    private ResourceApplyReviewService resourceApplyReviewService;

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncTask() {
        Date startDate = new Date();
        log.info("--------同步任务开始, 开始时间为 {}", startDate);

        //1.同步目录分类数据
        log.info("--------开始同步目录分类数据");
        catalogCategoryService.syncCatalogCategory();
        log.info("--------结束同步目录分类数据");

        //2.同步目录信息数据
        log.info("--------开始同步目录信息数据");
        catalogBasicinfoService.syncCatalogBasicInfo();
        log.info("--------结束同步目录信息数据");

        //3.同步资源申请审核过程表
        log.info("--------开始同步资源申请审核过程表数据");
        resourceApplyReviewService.syncResourceApplyReview();
        log.info("--------结束同步资源申请审核过程表数据");

        Date endDate = new Date();
        long time = endDate.getTime() - startDate.getTime();
        log.info("--------同步任务开始, 耗时为 {}毫秒", time);
    }

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncResourceFile() {
        log.info("--------开始同步----文件资源下行表");
        resourceFileService.syncResourceFile();
        log.info("--------结束同步----文件资源下行表");
    }

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncResourceOperationservice() {
        log.info("--------开始同步----接口资源下行表");
        resourceOperationserviceService.syncResourceOperationservice();
        log.info("--------结束同步----接口资源下行表");
    }

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void syncResourceTable() {
        log.info("--------开始同步----库表资源下行表");
        resourceTableService.syncResourceTable();
        log.info("--------结束同步----库表资源下行表");
    }
}
