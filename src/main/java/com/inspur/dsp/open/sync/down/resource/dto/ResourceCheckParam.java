package com.inspur.dsp.open.sync.down.resource.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ResourceCheckParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID，主键
     */
    @NotNull
    private String id;

    /**
     * 申请ID
     */
    @NotNull
    private String applyId;

    /**
     * 审核人员ID
     */
    @NotNull
    private String checkUserId;

    /**
     * 审核人员姓名
     */
    @NotNull
    private String checkUserName;

    /**
     * 审核意见
     */
    @NotNull
    private String checkNote;

    /**
     * 审核状态，1审核通过，2审核驳回，3待授权，4授权通过，5授权驳回，6撤销授权，7已备案，8备案驳回，9收回授权
     */
    @NotNull
    private String checkStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public String getCheckNote() {
        return checkNote;
    }

    public void setCheckNote(String checkNote) {
        this.checkNote = checkNote;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
}
