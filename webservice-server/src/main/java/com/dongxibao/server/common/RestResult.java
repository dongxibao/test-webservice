package com.dongxibao.server.common;

import org.springframework.http.HttpStatus;

/**
 * Restful 结果
 *
 * @author Dongxibao
 * @date 2020-05-30
 */
public class RestResult<T> {

    /** 状态 */
    private boolean status;

    /** 消息 */
    private String message;

    /** 数据 */
    private T data;

    /** 响应代码 */
    private int code;

    public RestResult() {
        this(null, true);
    }

    public RestResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.status = false;
    }

    public RestResult(String message) {
        this(message, true);
    }

    public RestResult(String message, boolean status) {
        this(message, null, status);
    }

    public RestResult(String message, T data, boolean status) {
        this.status = status;
        this.message = message;
        this.data = data;
        if (this.status || this.data != null) {
            this.code = HttpStatus.OK.value();
        } else {
            this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    public RestResult(String message, boolean status, int code, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
