package com.inspur.dsp.open.sync.up.service;

import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.sync.up.bean.ResourceApplyParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShareApiReqService {
    private static final Logger log = LoggerFactory.getLogger(ShareApiReqService.class);

    @Value("${share.url}")
    private String shareUrl;

    @Value("${share.token.url}")
    private String shareTokenUrl;

    @Value("${share.token.client_id}")
    private String clientId;

    @Value("${share.token.client_secret}")
    private String clientSecret;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 资源申请接口
     * 区共享交换平台对地市平台提供资源申请接口
     */
    public boolean resourceApply(ResourceApplyParam resourceApplyParam){
        String url = shareUrl + "/basic/resource/apply";
        log.debug("资源申请接口，url:{}", url);
        String access_token = getToken();
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Authorization", access_token);
        }};
        log.debug("资源申请接口，请求参数:{}", resourceApplyParam.toString());
        HttpEntity httpEntity = new HttpEntity<>(resourceApplyParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("资源申请接口，返回参数:{}", result.toString());
        String code = result.getString("code");
        if("1".equals(code)){
            log.debug("资源申请接口，请求成功");
            return true;
        }else{
            String message = result.getString("message");
            log.debug("资源申请接口，请求失败:{}", message);
            return false;
        }
    }

    private String getToken(){
        String url = shareTokenUrl;
        log.debug("获取token，url:{}", url);
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("grant_type", "client_credentials");
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("获取token，返回参数:{}", result.toString());
        JSONObject custom = result.getJSONObject("custom");
        String access_token = custom.getString("access_token");
        return access_token;
    }

    /**
     * 资源申请附件上传接口
     */
    public String fileupload(String filename, String filecontent){
        String url = shareUrl + "/basic/apply/fileupload";
        log.debug("资源申请附件上传接口，url:{}", url);
        String access_token = getToken();
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Authorization", access_token);
        }};
        Map<String, String> params = new HashMap<>();
        params.put("filename", filename);
        params.put("filecontent", filecontent);
        HttpEntity httpEntity = new HttpEntity<>(params, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("资源申请附件上传接口，返回参数:{}", result.toString());
        String code = result.getString("code");
        if("1".equals(code)){
            JSONObject dataMap = result.getJSONObject("data");
            String cascadeguid = dataMap.getString("cascadeguid");
            log.debug("资源申请附件上传接口，请求成功");
            return cascadeguid;
        }else{
            String message = result.getString("message");
            log.debug("资源申请附件上传接口，请求失败:{}", message);
            return null;
        }
    }

}
