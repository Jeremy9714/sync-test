package com.inspur.dsp.open.sync.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.inspur.service.UserAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class DubboService {

    @Reference(group = "bsp", check = false)
    private UserAuthorityService userAuthorityService;


    public JSONObject userLogin(String username, String password) {
        return this.userAuthorityService.userLogin("testa", "ec2b10b0da9587a4919b65e9d5dcd28a", "DSP-INTEGRATION-CONSOLE", true);
    }
}
