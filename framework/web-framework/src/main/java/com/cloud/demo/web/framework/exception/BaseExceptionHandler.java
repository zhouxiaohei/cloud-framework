package com.cloud.demo.web.framework.exception;


import com.cloud.demo.web.framework.common.CommonConstant;
import com.cloud.demo.web.framework.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;


/**
  * @Author JackZhou
  * @Description 统一异常处理类
  * @Date 2019/5/6 17:56
  * @Param 
  * @return 
 **/
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler{

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse handleException(Exception e) {
        log.error("C11-APP-Web 接口层统一捕获：", e);
        WebResponse webResponse = new WebResponse(500, "服务器内部异常", e);
        if(e.getCause() != null){
            webResponse.setResult(e.getCause());
        }
        return webResponse;
    }

    @ExceptionHandler({ServletException.class})
    @ResponseBody
    public WebResponse handleException(ServletException e) {
        log.error("接口层Servlet异常：", e);
        WebResponse webResponse = new WebResponse(500, "Servlet异常", e);
        if(e.getCause() != null){
            webResponse.setResult(e.getCause());
        }
        return webResponse;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public WebResponse handleException(BusinessException e) {
        log.error("C11-APP-Web 业务异常：", e);
        WebResponse webResponse = new WebResponse(e.getCode(), e.getMessage());
        if(e.getCause() != null){
            webResponse.setResult(e.getCause());
        }
        return webResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public WebResponse handleException(MethodArgumentNotValidException e) {
        log.error("C11-APP-Web controller参数异常处理：", e);
        String errorMessage = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                 errorMessage = "参数异常：" + error.getField() + "->" + error.getDefaultMessage();
                log.error(errorMessage);
                break;
            }
        }
        return new WebResponse(CommonConstant.ERROR_CODE_INPUT_PARAM_ERROR, errorMessage, bindingResult.getAllErrors());
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public WebResponse handleException(BindException e) {
        log.error("C11-APP-Web controller参数异常处理：", e);
        String errorMessage = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage = "参数异常：" + error.getField() + "->" + error.getDefaultMessage();
                log.error(errorMessage);
                break;
            }
        }
        return new WebResponse(CommonConstant.ERROR_CODE_INPUT_PARAM_ERROR, errorMessage, bindingResult.getAllErrors());
    }


}
