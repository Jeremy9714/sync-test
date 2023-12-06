package com.inspur.dsp.util;

import com.inspur.dsp.open.sync.DataSyncApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import org.apache.tomcat.util.codec.binary.Base64;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSyncApplication.class)
public class RestTemplateTest {


    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testUserLogin(){
        String url = "http://10.110.16.207/rcservice/doc?doc_id=072CAEE03C5D4C7F80123B74E178A598";
//        byte[] result = restTemplate.postForObject(url, null, byte[].class);
//        byte[] base64Bytes = Base64.getEncoder().encode(result);
//        byte[] base64Bytes = Base64.encodeBase64(result);

        ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        byte[] base64Bytes = Base64.encodeBase64(result.getBody());

        String base64Str = new String(base64Bytes);
        System.out.println("-----------------------------------------------");
        System.out.println(base64Str);
    }
}
