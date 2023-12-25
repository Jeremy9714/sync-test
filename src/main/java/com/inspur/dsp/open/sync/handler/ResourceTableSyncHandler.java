package com.inspur.dsp.open.sync.handler;

import com.inspur.dsp.open.sync.down.resource.service.ResourceTableService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/18 10:08
 * @Version: 1.0
 */
@JobHandler("resourceTableSyncJobHandler")
@Component
public class ResourceTableSyncHandler extends IJobHandler {

    @Autowired
    private ResourceTableService resourceTableService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            XxlJobLogger.log("库表资源同步任务resourceTableSyncJobHandler开始执行。。。。。。。。");
            boolean isSucceed = resourceTableService.syncResourceTable();
            if (!isSucceed) {
                XxlJobLogger.log("库表资源同步任务异常：");
                return ReturnT.FAIL;
            }
        } catch (Exception e) {
            XxlJobLogger.log("库表资源同步任务异常：");
            XxlJobLogger.log(e);
            return ReturnT.FAIL;
        }
        XxlJobLogger.log("库表资源同步任务resourceTableSyncJobHandler执行成功。。。。。。。。");
        return ReturnT.SUCCESS;
    }
}
