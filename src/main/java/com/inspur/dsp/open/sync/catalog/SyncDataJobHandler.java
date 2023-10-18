package com.inspur.dsp.open.sync.catalog;

import com.alibaba.dubbo.config.annotation.Reference;
import com.inspur.dsp.open.catalog.api.IOpenCatalogInfoService;
import com.inspur.dsp.open.catalog.api.IOpenCatalogItemService;
import com.inspur.dsp.open.sync.support.DemoService;
import com.inspur.service.OrganizationService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@JobHandler("syncDataJobHandler")
@Component
public class SyncDataJobHandler extends IJobHandler {

    @Autowired
    private DemoService demoService;

    private static final Logger logger = LoggerFactory.getLogger(SyncDataJobHandler.class);

    /**
     * 目录导入任务是否在运行
     */
    private boolean task_isRun = false;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        if (task_isRun) {
            XxlJobLogger.log("任务已经运行，。。。。");
        }
        try {
            String result = demoService.getXXX();
            System.out.println(result);
        } catch (Exception e) {
            XxlJobLogger.log("任务异常");

            return ReturnT.FAIL;

        } finally {

        }
        return ReturnT.SUCCESS;
    }

}
