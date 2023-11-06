package com.inspur.dsp.redis;

import com.inspur.dsp.open.sync.DataSyncApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/11/6 15:04
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataSyncApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void removeKey() {
        Boolean flag = redisTemplate.expire("catalog_basic_info_key", 0, TimeUnit.SECONDS);
        String time = redisTemplate.opsForValue().get("catalog_basic_info_key");
        System.out.println(time);
    }
}
