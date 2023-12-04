package com.inspur.dsp.open.sync.up;

import com.alibaba.fastjson.JSONArray;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 示例
 * {
 *     "resource_id": "bfce1857-8eed-4d13-a52b-99ab5f448ae3",
 *     "credit_code": "91510100MA6C8A7M4M",
 *     "apply_user": "张三",
 *     "contact": "zhagnsan@162.com",
 *     "end_date": "2019-01-01",
 *     "use_scope": "1",
 *     "use_desc": "主要用途",
 *     "apply_type": "1",
 *     "fieldid": "bfce1857-8eed-4d13-a52b-99ab5f448ae3;bfce1857-8eed-4d13-a52b-99ab5f448ae3",
 *     "applyBasis": "申请依据",
 *     "situation": "001",
 *     "otherRequire": "其他要求",
 *     "extranetIp": "127.0.0.1",
 *     "deployAddress": "部署地点",
 *     "emergencyLevel": "1",
 *     "emergencyReason": "",
 *     "attach": [
 *         {
 *             "fileid": "5a55c49b-e766-4204-a622-24ae98ad3ff2",
 *             "filename": "资源申请表6.txt"
 *         }
 *     ]
 * }
 */
public class ResourceApplyParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资源Id
     * 区共享平台资源id,格式：UUID,多个申请以 ;号隔开,多个申请只支持文件和接口申请.并只能申请同一目录下的多个资源
     */
    @NotNull
    private String resource_id;

    /**
     * 申请人姓名
     */
    @NotNull
    private String apply_user;

    /**
     * 申请人联系方式
     * 电话/邮箱（只需要填其中之一，电话或者邮箱）
     */
    @NotNull
    private String contact;

    /**
     * 申请部门社会信用代码
     */
    @NotNull
    private String credit_code;

    /**
     * 申请有效期，失效日期
     * 不填为永久有效
     */
    private Date end_date;

    /**
     * 资源使用范围
     * 见数据字典：申请使用范围
     */
    @NotNull
    private String use_scope;

    /**
     * 资源用途说明
     */
    @NotNull
    private String use_desc;

    /**
     * JSONs数组见表:
     * attach_list
     * {
     *     fileid:文件唯一标识，附件上传返回，，uuid
     *     filename:带上后缀
     * }
     */
    @NotNull
    private JSONArray attach;

    /**
     * 申请类型
     * 库表：1
     * 文件：3
     * 接口：4
     */
    @NotNull
    private String apply_type;

    /**
     * 申请的信息项唯一标识，信息项下行表中的column_id，多个分号隔开
     * 库表资源必填
     */
    @NotNull
    private String fieldid;

    /**
     * 区共享平台申请记录标识
     * 如果不为空则为驳回补正,可以修改申请信息重新提交申请
     */
    private String apply_id;

    /**
     * 办事场景
     * 数据字典：办事场景
     */
    @NotNull
    private String situation;

    /**
     * 业务系统的政务外网IP
     */
    @NotNull
    private String extranetIp;

    /**
     * 其他要求
     */
    @NotNull
    private String otherRequire;

    /**
     * 业务系统部署地点
     */
    @NotNull
    private String deployAddress;

    /**
     * 加急级别
     * 1：普通
     * 2：加急
     * 3：特急
     */
    @NotNull
    private String emergencyLevel;

    /**
     * 加急理由
     * 特急和加急 时为必填，“普通”级别传空值
     */
    private String emergencyReason;

    /**
     * 申请依据
     */
    @NotNull
    private String applyBasis;


    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getApply_user() {
        return apply_user;
    }

    public void setApply_user(String apply_user) {
        this.apply_user = apply_user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCredit_code() {
        return credit_code;
    }

    public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getUse_scope() {
        return use_scope;
    }

    public void setUse_scope(String use_scope) {
        this.use_scope = use_scope;
    }

    public String getUse_desc() {
        return use_desc;
    }

    public void setUse_desc(String use_desc) {
        this.use_desc = use_desc;
    }

    public JSONArray getAttach() {
        return attach;
    }

    public void setAttach(JSONArray attach) {
        this.attach = attach;
    }

    public String getApply_type() {
        return apply_type;
    }

    public void setApply_type(String apply_type) {
        this.apply_type = apply_type;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getExtranetIp() {
        return extranetIp;
    }

    public void setExtranetIp(String extranetIp) {
        this.extranetIp = extranetIp;
    }

    public String getOtherRequire() {
        return otherRequire;
    }

    public void setOtherRequire(String otherRequire) {
        this.otherRequire = otherRequire;
    }

    public String getDeployAddress() {
        return deployAddress;
    }

    public void setDeployAddress(String deployAddress) {
        this.deployAddress = deployAddress;
    }

    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public String getEmergencyReason() {
        return emergencyReason;
    }

    public void setEmergencyReason(String emergencyReason) {
        this.emergencyReason = emergencyReason;
    }

    public String getApplyBasis() {
        return applyBasis;
    }

    public void setApplyBasis(String applyBasis) {
        this.applyBasis = applyBasis;
    }
}
