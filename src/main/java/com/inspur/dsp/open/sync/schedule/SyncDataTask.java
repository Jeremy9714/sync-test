package com.inspur.dsp.open.sync.schedule;

import com.inspur.dsp.open.sync.down.catalog.service.CatalogInfoService;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogGroupLinkService;
import com.inspur.dsp.open.sync.down.resource.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

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
    private CatalogGroupLinkService catalogGroupLinkService;

    @Autowired
    private CatalogInfoService catalogBasicinfoService;

    @Autowired
    private ResourceFileService resourceFileService;

    @Autowired
    private ResourceOperationserviceService resourceOperationserviceService;

    @Autowired
    private ResourceTableService resourceTableService;

    @Autowired
    private ResourceApplyReviewService resourceApplyReviewService;

    @Autowired
    private ResourceApplyService resourceApplyService;

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncCatalogData() {
        log.info("--------开始同步----目录信息下行表");
        long startTime = System.currentTimeMillis();
        catalogBasicinfoService.syncCatalogBasicInfo();
        log.info("--------结束同步----目录信息下行表,耗时为 {}毫秒", System.currentTimeMillis() - startTime);
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
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncResourceTable() {
        log.info("--------开始同步----库表资源下行表");
        resourceTableService.syncResourceTable();
        log.info("--------结束同步----库表资源下行表");
    }

    /**
     * 下行，
     */
    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncResourceApply() {
        log.info("--------开始同步----资源申请的审核结果");
        resourceApplyService.syncResourceApply();
        log.info("--------结束同步----资源申请的审核结果");
    }

    /**
     * 上行，同步申请表的增量数据
     */
    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void syncResourceApplyIncrement() {
        log.info("--------开始同步----申请表的增量数据");
        resourceApplyService.syncResourceApplyIncrement();
        log.info("--------结束同步----申请表的增量数据");
    }
}
