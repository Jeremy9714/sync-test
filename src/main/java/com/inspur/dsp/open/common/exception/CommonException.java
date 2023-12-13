package com.inspur.dsp.open.common.exception;

import com.inspur.dsp.open.common.exception.message.ErrorMessage;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/13 14:11
 * @Version: 1.0
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -97234L;

    private String code;
    private String message;

    public CommonException() {
    }

    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(ErrorMessage errorMessage) {
        this.code = errorMessage.getStatusCode();
        this.message = errorMessage.getMessage();
    }

    public CommonException(ErrorMessage errorMessage, Throwable cause) {
        super(cause);
        this.code = errorMessage.getStatusCode();
        this.message = errorMessage.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
