package com.inspur.dsp.open.sync.down.service;

import com.alibaba.fastjson.JSONObject;
import com.inspur.dsp.open.sync.down.resource.dto.AddApiResourceParam;
import com.inspur.dsp.open.sync.down.resource.dto.AddTableResourceParam;
import com.inspur.dsp.open.sync.down.resource.dto.DeleteTableResourceParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenApiReqService {
    private static final Logger log = LoggerFactory.getLogger(OpenApiReqService.class);

    @Value("${open.url}")
    private String openUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用，保存库表资源
     */
    public void addTableResource(AddTableResourceParam addTableResourceParam){
        String url = openUrl + "/oresource/admin/resource/addTableResource";
        log.debug("保存库表资源，url:{}", url);
        log.debug("保存库表资源，请求参数:{}", addTableResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(addTableResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("保存库表资源成功。");
        }else{
            String msg = result.getString("msg");
            log.error("保存库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }


    /**
     * 调用，删除库表资源
     * @return
     */
    public void deleteTableResource(DeleteTableResourceParam deleteTableResourceParam){
        String url = openUrl + "/oresource/admin/resource/deleteTableResource";
        log.debug("删除库表资源，url:{}", url);
        log.debug("删除库表资源，请求参数:{}", deleteTableResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(deleteTableResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("删除库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("删除库表资源成功。");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        }else{
            String msg = result.getString("msg");
            log.error("删除库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 调用，保存接口资源
     * @param addApiResourceParam
     */
    public void addApiResource(AddApiResourceParam addApiResourceParam){
        String url = openUrl + "/oresource/admin/resource/addApiResource";
        log.debug("保存接口资源，url:{}", url);
        log.debug("保存接口资源，请求参数:{}", addApiResourceParam.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(addApiResourceParam, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存接口资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("保存接口资源成功。");
        }else{
            String msg = result.getString("msg");
            log.error("保存接口资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 删除接口资源
     * @param apiId 接口资源id
     * @param cataId 目录id
     */
    public void deleteApiResource(String apiId, String cataId){
        String url = openUrl + "/oresource/admin/resource/deleteApiResource";
        log.debug("删除接口资源，url:{}", url);
        log.debug("删除接口资源，请求参数:{} 和 {}", apiId, cataId);
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }};
        Map<String, String> map = new HashMap<>();
        map.put("api_id", apiId);
        map.put("cataid", cataId);
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        log.debug("保存接口资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if(code == 0){
            log.info("删除接口资源成功。");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        }else{
            String msg = result.getString("msg");
            log.error("删除接口资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }

    /**
     * 下载文件
     * @param doc_id
     * @return
     */
    public ResponseEntity<byte[]> fileDownload(String doc_id){
        String url = openUrl + "/rcservice/doc?doc_id=" + doc_id;
        ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        return result;
    }

}
