package com.inspur.dsp.open.sync.schedule;

import com.inspur.dsp.open.sync.down.catalog.service.CatalogInfoService;
import com.inspur.dsp.open.sync.down.catalog.service.CatalogGroupLinkService;
import com.inspur.dsp.open.sync.down.resource.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


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
    private CatalogInfoService catalogInfoService;

    @Autowired
    private ResourceFileService resourceFileService;

    @Autowired
    private ResourceTableService resourceTableService;

//    @Autowired
//    private ResourceDatasourceService resourceDatasourceService;

    @Value("${catalog.schedule.enable:1}")
    private String catalogScheduleFlag;

    @Value("${resource.table.schedule.enable:1}")
    private String resourceTableScheduleFlag;

    @Value("${resource.file.schedule.enable:1}")
    private String resourceFileScheduleFlag;

//    @Autowired
//    private ResourceDatasourceService resourceDatasourceService;

    @Async
    @Scheduled(cron = "${task.schedule.cron:0 0/1 * * * ?}")
    public void syncCatalogData() {
        if (!"1".equals(catalogScheduleFlag)){
            return;
        }
        log.info("--------开始同步----目录信息下行表");
        long startTime = System.currentTimeMillis();
        catalogInfoService.syncCatalogBasicInfo();
        log.info("--------结束同步----目录信息下行表,耗时为 {}毫秒", System.currentTimeMillis() - startTime);
    }

    @Async
    @Scheduled(cron = "${task.schedule.cron:0 0/1 * * * ?}")
    public void syncResourceFile() {
        if (!"1".equals(resourceFileScheduleFlag)){
            return;
        }
        log.info("--------开始同步----文件资源下行表");
        resourceFileService.syncResourceFile();
        log.info("--------结束同步----文件资源下行表");
    }

    @Async
    @Scheduled(cron = "${task.schedule.cron:0 0/1 * * * ?}")
    public void syncResourceTable() {
        if (!"1".equals(resourceTableScheduleFlag)){
            return;
        }
//        log.info("--------开始同步----数据源下行表");
//        resourceDatasourceService.syncResourceDatasource();
//        log.info("--------结束同步----数据源下行表");

        log.info("--------开始同步----库表资源下行表");
        resourceTableService.syncResourceTable();
        log.info("--------结束同步----库表资源下行表");
    }
}
