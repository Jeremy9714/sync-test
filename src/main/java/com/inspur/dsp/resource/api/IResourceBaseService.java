package com.inspur.dsp.resource.api;

import java.util.Map;

public interface IResourceBaseService {

    /**
     * 保存文件资源
     * 支持新增和更新文件资源信息的综合接口。
     * @param map
     * @return
     */
    Map<String, Object> saveFileResource(Map<String, Object> map);

    /**
     * 删除文件资源接口。
     * @param id
     * @return
     */
    Map<String, Object> deleteResource(String id);


    Map<String, Object> findTableResource(String id);

}
