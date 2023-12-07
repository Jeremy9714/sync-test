package com.inspur.dsp.open.sync.down.resource.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口资源下行表
 */
@TableName("resource_operationservice")
public class ResourceOperationservice implements Serializable {

    private static final long serialVersionUID = 123L;

    /**
     * 目录唯一标识
     */
    @TableField("cata_id")
    private String cataId;

    /**
     * Paramtype:参数类型
     * Mustfill：是否必填（0：否，1：是）
     * Remark：备注
     * ParamName：参数名称
     * ServiceGuid：对应接口资源service_id
     */
    @TableField("inputParams")
    private String inputParams;

    /**
     * Paramtype:参数类型
     * Mustfill：是否必填（0：否，1：是）
     * Remark：备注
     * ParamName：参数名称
     * ServiceGuid：对应接口资源service_id
     */
    @TableField("outputParams")
    private String outputParams;

    /**
     * 服务英文名称
     */
    @TableField("enName")
    private String enName;

    /**
     * 统一社会信用代码
     */
    @TableField("credit_code")
    private String creditCode;

    /**
     * 数据字典：方法类型
     */
    @TableField("methodType")
    private String methodType;

    /**
     * 1：开放;2：共享
     */
    @TableField("serviceShareType")
    private String serviceShareType;

    /**
     * 0:RestAPI;1:WebService
     */
    @TableField("serviceType")
    private String serviceType;

    /**
     * 服务ID
     */
    @TableId
    @TableField("service_id")
    private String serviceId;

    /**
     * 中文名称
     */
    @TableField("cnName")
    private String cnName;

    /**
     * 服务地址url
     */
    @TableField("url")
    private String url;

    /**
     * 服务描述
     */
    @TableField("serviceDesc")
    private String serviceDesc;

    /**
     * 请求示例
     */
    @TableField("callExample")
    private String callExample;

    /**
     * 响应示例
     */
    @TableField("output")
    private String output;

    /**
     * 操作时间
     */
    @TableField("operate_date")
    private Date operateDate;

    /**
     * 操作类型：
     * 新增：I；
     * 修改：U;
     * 删除：D
     */
    @TableField("operate_type")
    private String operateType;

    /**
     * 附件信息:
     * [{"filename": "","fileid": ""}...]
     */
    @TableField("attach")
    private String attach;

    /**
     * requestSample:请求参数模板
     * method：方法名称
     * url：请求地址
     */
    @TableField("webServiceData")
    private String webServiceData;

    /**
     * 技术支持联系人
     */
    @TableField("supporter")
    private String supporter;

    /**
     * 技术支持联系人联系方式
     */
    @TableField("supporterPhone")
    private String supporterPhone;

    /**
     * 技术支持联系人联系方式
     */
    @TableField("supporteremail")
    private String supporterEmail;

    /**
     * 开放门户调用超时时间
     */
    @TableField("timeout")
    private Integer timeout;

    /**
     * 开放门户熔断的执行超时时间(秒）
     */
    @TableField("executionTimeout")
    private String executionTimeout;

    /**
     * 开放门户熔断采样时间窗口(毫秒)
     */
    @TableField("rollingStatisticalWindow")
    private String rollingStatisticalWindow;

    /**
     * 开放门户熔断的错误率
     */
    @TableField("errorThresholdPercentage")
    private String errorThresholdPercentage;

    /**
     *
     */
    @TableField("soapHeader")
    private String soapHeader;

    /**
     *
     */
    @TableField("soapBody")
    private String soapBody;


    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    public String getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(String outputParams) {
        this.outputParams = outputParams;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getServiceShareType() {
        return serviceShareType;
    }

    public void setServiceShareType(String serviceShareType) {
        this.serviceShareType = serviceShareType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getCallExample() {
        return callExample;
    }

    public void setCallExample(String callExample) {
        this.callExample = callExample;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getWebServiceData() {
        return webServiceData;
    }

    public void setWebServiceData(String webServiceData) {
        this.webServiceData = webServiceData;
    }

    public String getSupporter() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }

    public String getSupporterPhone() {
        return supporterPhone;
    }

    public void setSupporterPhone(String supporterPhone) {
        this.supporterPhone = supporterPhone;
    }

    public String getSupporterEmail() {
        return supporterEmail;
    }

    public void setSupporterEmail(String supporterEmail) {
        this.supporterEmail = supporterEmail;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getExecutionTimeout() {
        return executionTimeout;
    }

    public void setExecutionTimeout(String executionTimeout) {
        this.executionTimeout = executionTimeout;
    }

    public String getRollingStatisticalWindow() {
        return rollingStatisticalWindow;
    }

    public void setRollingStatisticalWindow(String rollingStatisticalWindow) {
        this.rollingStatisticalWindow = rollingStatisticalWindow;
    }

    public String getErrorThresholdPercentage() {
        return errorThresholdPercentage;
    }

    public void setErrorThresholdPercentage(String errorThresholdPercentage) {
        this.errorThresholdPercentage = errorThresholdPercentage;
    }

    public String getSoapHeader() {
        return soapHeader;
    }

    public void setSoapHeader(String soapHeader) {
        this.soapHeader = soapHeader;
    }

    public String getSoapBody() {
        return soapBody;
    }

    public void setSoapBody(String soapBody) {
        this.soapBody = soapBody;
    }
}
