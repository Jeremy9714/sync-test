package com.inspur.dsp.open.sync.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.resource.api.IResourceBaseService;
import com.inspur.service.UserAuthorityService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DubboService {

    @Reference(group = "bsp", check = false)
    private UserAuthorityService userAuthorityService;

    @Reference(group = "metaresource", check = false)
    private IResourceBaseService resourceBaseService;


    public JSONObject userLogin(String username, String password) {
        return this.userAuthorityService.userLogin(username, password, "DSP-INTEGRATION-CONSOLE", true);
    }

    public Map<String, Object> findTableResource(String id){
        return this.resourceBaseService.findTableResource(id);
    }

    /**
     * 保存文件资源
     * 支持新增和更新文件资源信息的综合接口
     * @param map
     * @return
     */
    public Map<String, Object> saveFileResource(Map<String, Object> map){
        return resourceBaseService.saveFileResource(map);
    }

    /**
     * 删除文件资源接口。
     * @param id
     * @return
     */
    public Map<String, Object> deleteResource(String id){
        return resourceBaseService.deleteResource(id);
    }
}
