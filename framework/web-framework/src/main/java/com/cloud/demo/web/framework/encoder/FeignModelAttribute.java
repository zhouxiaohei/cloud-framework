package com.cloud.demo.web.framework.encoder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName FeignModelAttribute
 * @Description feign ModelAttribute接口请求参数注解
 * @Author JackZhou
 * @Date 2020/2/25  10:38
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FeignModelAttribute {

}
