package com.cloud.demo.web.framework.exception;


/**
  * @Author JackZhou
  * @Description
  * @Date 2020/2/11 14:14
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2020185599276671223L;

    private int code;
    private String message;

    public BusinessException(int code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
