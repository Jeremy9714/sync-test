package com.inspur.dsp.open.upload;

import com.alibaba.fastjson.JSON;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * 基于RCService的文件存储
 *
 * @author zhfeng
 */
@Component
public class RCBasedFileStore implements IFileStore {

    private Logger logger = LoggerFactory.getLogger(RCBasedFileStore.class);

    @Value("${global.rc.inner_rcservice}")
    private String rcservice_content;

    private static final String DIR_NAME = "openportal";

    private static final String UPLOAD_FILE_FIELD_NAME = "uploadfile";

    /**
     * 当下载文件发生错误时，返回内容为{"error":"获取文件出现异常","code":"0002"}，长度为50
     */
//    private static int FILE_NOT_FIND_RESPONSE_LENGTH = 50;

    public HttpClient getHttpClient() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);

        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        return httpClient;
    }

    public String putFile(String fileName, File file, Map<String, String> otherParams) {
        try {
            FilePart uploadFilePart = new FilePart(UPLOAD_FILE_FIELD_NAME, file);

            return this.putFile(fileName, DIR_NAME, uploadFilePart, otherParams);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public String putFile(String fileName, InputStream in, Map<String, String> otherParams) {
        PartSource inputStreamPartSource = new InputStreamPartSource(in, fileName);
        FilePart uploadFilePart = new FilePart(UPLOAD_FILE_FIELD_NAME, inputStreamPartSource);

        return this.putFile(fileName, DIR_NAME, uploadFilePart, otherParams);
    }

    @SuppressWarnings("rawtypes")
    public String putFile(String fileName, String dir, FilePart filepart, Map<String, String> otherParams) {
        String RC_UPLOAD_URL = rcservice_content + "/upload";
        PostMethod postMethod = new PostMethod(RC_UPLOAD_URL);
        try {
            List<Part> partList = new ArrayList<Part>();
            partList.add(filepart);
            partList.add(new StringPart("uid", "openportal", "UTF-8"));
            partList.add(new StringPart("type", "doc", "UTF-8"));
            partList.add(new StringPart("name", fileName, "UTF-8"));
            partList.add(new StringPart("folder_name", dir, "UTF-8"));

            if (otherParams != null) {
                Set<Map.Entry<String, String>> entries = otherParams.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    partList.add(new StringPart(entry.getKey(), entry.getValue()));
                }
            }

            Part[] parts = partList.toArray(new Part[] {});
            MultipartRequestEntity entity = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(entity);
            int status = getHttpClient().executeMethod(postMethod);

            if (status == HttpStatus.SC_OK) {

                String result = postMethod.getResponseBodyAsString();

                Map resultMap = null;
                try {
                    resultMap = JSON.parseObject(result, Map.class);
                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Parse upload file result error.", e);
                        throw new RuntimeException(e);
                    }
                }
                if (resultMap != null) {
                    if (resultMap.containsKey("error")) {
                        throw new RuntimeException("Upload file error: " + resultMap);
                    }
                    // 返回的文档ID
                    String docId = String.valueOf(resultMap.get("uuid"));
                    // 返回文件的唯一标识
                    return docId;
                }
            } else {
                logger.error("upload file result: " + status);
            }

        } catch (HttpException e) {
            logger.error("Put file error.", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Put file error.", e);
            throw new RuntimeException(e);
        } finally {
            postMethod.releaseConnection();
        }

        return null;
    }

    public void getFile(String fileId, FileReader reader, ResponseHeaderReader headerReader) {
        String uri = this.getFileUrl(fileId);
        GetMethod httpGet = new GetMethod(uri);
        httpGet.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
        httpGet.addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        try {

            int status = getHttpClient().executeMethod(httpGet);

            if (status == HttpStatus.SC_OK) {

                if (headerReader != null) {
                    Header[] headers = httpGet.getResponseHeaders();
                    headerReader.readHeader(transHeader2Map(headers));
                }

                InputStream in = httpGet.getResponseBodyAsStream();
                reader.readFile(in);

            } else {
                throw new RuntimeException("Get file error " + status);
            }

        } catch (IOException e) {
            logger.error("Get file error", e);
        } catch (Exception e) {
            logger.error("Get file error", e);
        } finally {
            httpGet.releaseConnection();
        }
    }

    private List<Map<String, String>> transHeader2Map(Header[] headers) {
        List<Map<String, String>> headerList = new ArrayList<Map<String, String>>();
        for (Header header : headers) {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("name", header.getName());
            try {
                headerMap.put("value", URLDecoder.decode(header.getValue(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
            headerList.add(headerMap);
        }
        return headerList;
    }

    public String getFileUrl(String fileId) {
        return rcservice_content + "/doc?doc_id=" + fileId;
    }

    @Override
    public boolean downloadFile(String urlStr, String fileStorePath, String fileName) {
        FileOutputStream fos = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();  
            //获取自己数组
            byte[] getData = readInputStream(inputStream);    
            
            conn.disconnect();
     
            //文件保存位置
            File saveDir = new File(fileStorePath);
            if(!saveDir.exists()){
                boolean rslt = saveDir.mkdir();
                if(!rslt){
                	logger.error("######################文件夹创建失败！");
                }
            }
            File file = new File(saveDir + File.separator + fileName);    
            fos = new FileOutputStream(file);
            fos.write(getData); 

            fos.close();
            inputStream.close();

            return true;
            
        } catch (IOException e) {
            logger.error("download file error", e);
            return false;
        } catch (Exception e) {
            logger.error("download file error", e);
            return false;
        }finally{
            try {
                if (fos != null) {
                    fos.close();
                }
            }catch(Exception e){
                logger.error("关闭流数据错误!", e);
            }
        }
    }
    
    @Override
    public File downloadFileByStream(String urlStr, String fileStorePath, String fileName){
    	FileOutputStream outputStream = null;
    	File file = null;
        try {
        	//文件保存位置
            File saveDir = new File(fileStorePath);
            if(!saveDir.exists()){
                boolean rslt = saveDir.mkdir();
                if(!rslt){
                	logger.error("######################文件夹创建失败！");
                }
            }
            file = new File(saveDir + File.separator + fileName);  
            
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();  
            
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            conn.disconnect();
            outputStream.close();
            inputStream.close();

            return file;
        } catch (IOException e) {
            logger.error("download file error", e);
            return null;
        } catch (Exception e) {
            logger.error("download file error", e);
            return null;
        }finally{
            try {
                if (outputStream != null) {
                	outputStream.close();
                }
            }catch(Exception e){
                logger.error("关闭流数据错误!", e);
            }
        }
    }
    
    @Override
    public byte[] downloadFile(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();  
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            conn.disconnect();
     
            return getData;
            
        } catch (IOException e) {
            logger.error("download file bytes error", e);
            return new byte[0];
        } catch (Exception e) {
            logger.error("download file bytes error", e);
            return new byte[0];
        }
    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  

}
