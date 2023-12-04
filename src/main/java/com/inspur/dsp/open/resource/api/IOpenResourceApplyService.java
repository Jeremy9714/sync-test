package com.inspur.dsp.open.resource.api;

import com.inspur.dsp.open.common.Result;

import java.util.List;
import java.util.Map;

public interface IOpenResourceApplyService {

    /**
     * 更新资源申请的审核结果
     * 更新资源申请信息同时插入审核日志
     * @param resourceCheckList
     * @param resourceApplyList
     * @return
     */
    Result<Boolean> insertandUpadteResourceApply(List<Map<String, Object>> resourceCheckList, List<Map<String, Object>> resourceApplyList);

    /**
     * 查询申请表的增量数据
     * 根据参数applyTime，返回申请表中申请时间大于该时间且状态为待审核状态的数据
     * 查询时间，要求格式：yyyy-MM-dd hh:mm:ss
     * @param applyTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Map<String, Object>> getResourceApplyByPage(String applyTime, int pageNum, int pageSize);

}
