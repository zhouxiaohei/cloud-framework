package com.cloud.demo.web.framework.response;

import java.io.Serializable;

/**
  * @Author JackZhou
  * @Description  web请求响应
  * @Date 2019/5/6 17:54
 **/
public class WebResponse<T> implements Serializable {

   private static final long serialVersionUID = -3799117183776657761L;

    private int code = 200;
    private String message = "success";
    private T result;

    public WebResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public WebResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public WebResponse(T result) {
        this.result = result;
    }

    public WebResponse(){
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
