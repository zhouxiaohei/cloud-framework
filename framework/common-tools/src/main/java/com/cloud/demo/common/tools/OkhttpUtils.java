package com.cloud.demo.common.tools;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SimpleOkhttpUtils
 * @Author JackZhou
 * @Date 2019/11/21  14:16
 **/
@Slf4j
public class OkhttpUtils {

   private static OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(6, TimeUnit.SECONDS).build();

   private static final MediaType JSON = MediaType.parse("application/json");

   private static final String MEDIATYPE_NONE = "none";
   private static final String MEDIATYPE_JSON = "application/json";
   private static final String MEDIATYPE_FORM = "form-data";
   private static final String MEDIATYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
      * @Author JackZhou
      * @Description  执行get请求
     **/
    public static String execRequest(String url, Map<String, String> headers){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if(headers != null && headers.size() >0 ){
            headers.entrySet().stream().forEach(entry -> requestBuilder.header(entry.getKey(), entry.getValue()));
        }
        Request request = requestBuilder.url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();
        } catch (IOException e) {
            log.info("执行http请求出错,地址:{}", url, e);
            return null;
        }
    }

    /**
     * @Author JackZhou
     * @Description  执行post请求
     **/
    public static String execPostRequest(String url, Map<String, String> headers, RequestBody requestBody){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if(headers != null && headers.size() >0 ){
            headers.entrySet().stream().forEach(entry -> requestBuilder.header(entry.getKey(), entry.getValue()));
        }
        Request request = requestBuilder.url(url).post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();
        } catch (IOException e) {
            log.info("执行http请求出错,地址:{}", url, e);
            return null;
        }
    }

    /**
     * @Author JackZhou
     * @Description  得到post请求的RequestBody
     **/
    public static RequestBody getBody(String type, Map<String, String> formParam, String body){
        switch (type) {
            case MEDIATYPE_JSON:
                if(body == null){
                    RequestBody.create(null, "");
                }
                return RequestBody.create(JSON, body);
            case MEDIATYPE_FORM:
                MultipartBody.Builder formBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                if(formParam != null && formParam.size() >0 ){
                    formParam.entrySet().stream().forEach(entry -> formBuilder.addFormDataPart(entry.getKey(), entry.getValue()));
                }
                return formBuilder.build();
            case MEDIATYPE_FORM_URLENCODED:
                FormBody.Builder builder = new FormBody.Builder();
                if(formParam != null && formParam.size() >0 ){
                    formParam.entrySet().stream().forEach(entry -> builder.add(entry.getKey(), entry.getValue()));
                }
                return builder.build();
            case MEDIATYPE_NONE:
                return RequestBody.create(null, "");
            default:
                throw new IllegalArgumentException("不支持的mediaType：" + type);
        }
    }

    /**
     * @Author JackZhou
     * @Description  得到拼接后的url  @RequestParam参数
     **/
    public static String getUrl(String url, Map<String, String> params){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if(params != null && params.size() > 0){
            params.entrySet().stream().forEach(entry -> builder.addQueryParameter(entry.getKey(), entry.getValue()));
        }
        return builder.build().toString();
    }

}