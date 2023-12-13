package com.inspur.dsp.open.common.exception.message;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/13 14:14
 * @Version: 1.0
 */
public interface ExceptionResponseMessage extends Serializable {

    String getStatusCode();

    String getMessage();
}
