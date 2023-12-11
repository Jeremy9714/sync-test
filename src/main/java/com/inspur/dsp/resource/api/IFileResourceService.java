package com.inspur.dsp.resource.api;

import java.util.Map;

public interface IFileResourceService {

    /**
     * 保存文件资源
     * 支持新增和更新文件资源信息的综合接口。
     * @param map
     * @return
     */
    Map<String, Object> saveFileResource(Map<String, Object> map);

}
