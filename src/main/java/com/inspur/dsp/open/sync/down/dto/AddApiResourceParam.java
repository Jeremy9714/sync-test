package com.inspur.dsp.open.sync.down.dto;

import com.inspur.dsp.open.sync.down.service.ResourceService;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddApiResourceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务的ID
     */
    @NotNull
    private String serviceId;

    /**
     * 代理服务调用类型
     */
    @NotNull
    private String callType;

    /**
     * 服务调用地址
     */
    @NotNull
    private String endpoint;

    /**
     * 超时时间
     */
    @NotNull
    private String timeout;

    /**
     * rest请求方法名包括：get,post,put,delete
     */
    private String restRequestMethod;

    /**
     * webservice的命名空间namespace
     */
    @NotNull
    private String wsNamespace;

    /**
     * webservice的soapbody
     */
    private String wsSoapbody;

    /**
     * webservice的soapheader信息
     */
    @NotNull
    private String wsHeader;

    /**
     * webservice的注册方法信息
     */
    @NotNull
    private String wsMethod;

    /**
     * 代理服务基本信息
     */
    @NotNull
    private ResourceService resourceService;


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getRestRequestMethod() {
        return restRequestMethod;
    }

    public void setRestRequestMethod(String restRequestMethod) {
        this.restRequestMethod = restRequestMethod;
    }

    public String getWsNamespace() {
        return wsNamespace;
    }

    public void setWsNamespace(String wsNamespace) {
        this.wsNamespace = wsNamespace;
    }

    public String getWsSoapbody() {
        return wsSoapbody;
    }

    public void setWsSoapbody(String wsSoapbody) {
        this.wsSoapbody = wsSoapbody;
    }

    public String getWsHeader() {
        return wsHeader;
    }

    public void setWsHeader(String wsHeader) {
        this.wsHeader = wsHeader;
    }

    public String getWsMethod() {
        return wsMethod;
    }

    public void setWsMethod(String wsMethod) {
        this.wsMethod = wsMethod;
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
