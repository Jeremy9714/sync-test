package com.inspur.dsp.resource.api;

import java.util.Map;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/11 11:05
 * @Version: 1.0
 */
public interface IResourceBaseService {

    /**
     * 删除文件资源接口。
     * @param id
     * @return
     */
    Map<String, Object> deleteResource(String id);
}
