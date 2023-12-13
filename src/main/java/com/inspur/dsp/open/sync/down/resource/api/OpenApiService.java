package com.inspur.dsp.open.sync.down.resource.api;

import com.alibaba.fastjson.JSONObject;
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
import java.util.List;
import java.util.Map;

@Service
public class OpenApiService {
    private static final Logger log = LoggerFactory.getLogger(OpenApiService.class);

    @Value("${open.url}")
    private String openUrl;

    @Value("${global.session-id}")
    private String sessionId;

    @Autowired
    private RestTemplate restTemplate;

    public void insertOrUpdateResourceDatasource(Map<String, Object> datasourceMap) {
        String url = openUrl + "/admin/datasource/addDataSource";
        log.debug("保存数据源，url:{}", url);
        log.debug("保存数据源，请求参数:{}", datasourceMap.toString());
//        HttpHeaders httpHeaders = new HttpHeaders() {{
//            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        }};
//        HttpEntity httpEntity = new HttpEntity<>(datasourceMap, httpHeaders);
//        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        JSONObject result = restTemplate.postForObject(url, datasourceMap, JSONObject.class);
        log.debug("保存数据源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if (code == 0) {
            log.info("保存数据源成功。");
        } else {
            String msg = result.getString("msg");
            log.error("保存数据源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }


    /**
     * 调用，保存库表资源
     */
    public void insertOrUpdateResourceTable(Map<String, Object> tableMap) {
        String url = openUrl + "/oresource/admin/resource/addTableResource?dataSourceIdcheck={dataSourceIdcheck}" +
                "&itemId={itemId}&cataid={cataid}&fromfiletable={fromfiletable}&modal_file_info={modal_file_info}" +
                "&table_desc={table_desc}&dataTableName={dataTableName}&columnnameEn={columnnameEn}";
        log.debug("保存库表资源，url:{}", url);
        log.debug("保存库表资源，请求参数:{}", tableMap.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/json;charset=UTF-8");
            add("Cookie", "SESSION="+sessionId);
        }};
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class, tableMap);
//        JSONObject result = restTemplate.getForObject(url, JSONObject.class, tableMap);
        log.debug("保存库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if (code == 1) {
            log.info("保存库表资源成功。");
        } else {
            String msg = result.getString("msg");
            log.error("保存库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }


    /**
     * 调用，删除库表资源
     *
     * @return
     */
    public void deleteResourceTable(Map<String, Object> tableMap) {
        String url = openUrl + "/oresource/admin/resource/deleteTableResource?table_id={table_id}" +
                "&datasource_id={datasource_id}&cata_id={cata_id}";
        log.debug("删除库表资源，url:{}", url);
        log.debug("删除库表资源，请求参数:{}", tableMap.toString());
//        HttpHeaders httpHeaders = new HttpHeaders() {{
//            add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        }};
//        HttpEntity httpEntity = new HttpEntity<>(tableMap, httpHeaders);
//        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        JSONObject result = restTemplate.postForObject(url, tableMap, JSONObject.class);
        log.debug("删除库表资源，返回参数:{}", result.toString());
        int code = result.getIntValue("code");
        if (code == 1) {
            log.info("删除库表资源成功。");
            // TODO 资源下架后，查询一次目录，如果该目录下没有资源，就把目录也下架
        } else {
            String msg = result.getString("msg");
            log.error("删除库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
    }

//    /**
//     * 下载文件
//     *
//     * @param doc_id
//     * @return
//     */
//    public ResponseEntity<byte[]> fileDownload(String doc_id) {
//        String url = openUrl + "/rcservice/doc?doc_id=" + doc_id;
//        ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
//        return result;
//    }

    /**
     * 获取表字段信息项
     */
    public List<Map<String, Object>> getTableColumn(String id, String tableName) {
        String url = openUrl + "/oresource/admin/resource/getTableColumn?id={id}&name={name}";
        Map<String, Object> paramMap = new HashMap() {{
            put("id", id);
            put("name", tableName);
        }};

        log.debug("查询库表字段接口，url:{}", url);
        log.debug("查询库表字段接口，请求参数:{}", paramMap.toString());
        HttpHeaders httpHeaders = new HttpHeaders() {{
            add("Content-Type", "application/json;charset=UTF-8");
        }};
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class, paramMap);
//        JSONObject result = restTemplate.getForObject(url, JSONObject.class, tableMap);
        log.debug("获取库表字段信息，返回参数:{}", result.toString());

        List<Map<String, Object>> tableColumnList = (List<Map<String, Object>>) result.get("tableColumnList");
        if (null == tableColumnList || tableColumnList.size() == 0) {
            String msg = result.getString("msg");
            log.error("保存库表资源，接口调用失败。错误说明:{}", msg);
            throw new RuntimeException("接口调用失败");
        }
        log.info("保存库表资源成功。");
        return tableColumnList;
    }

}
