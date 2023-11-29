package com.inspur.dsp.util;

import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.sync.DataSyncApplication;
import com.inspur.dsp.open.sync.util.DubboService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSyncApplication.class)
public class DubboServiceTest {

    @Autowired
    private DubboService dubboService;

    @Test
    public void testUserLogin(){
        JSONObject json = dubboService.userLogin("testa","ec2b10b0da9587a4919b65e9d5dcd28a");
        System.out.println(json);
    }

    @Test
    public void testFindTableResource(){
        Map<String, Object> map = dubboService.findTableResource("c1132fc913894cea973694268a76e64f");
        System.out.println(map);
    }

}
