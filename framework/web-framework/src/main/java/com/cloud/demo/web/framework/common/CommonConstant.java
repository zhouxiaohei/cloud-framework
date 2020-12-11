package com.cloud.demo.web.framework.common;


public class CommonConstant {
    /*
      * @Author JackZhou
      * @Description  响应参数
     **/
    public static final int SUCCESS_CODE = 200;
    /**  拒绝请求  **/
    public static final int ERROR_CODE_FORBIDDEN = 403;
    /**  实体不存在  **/
    public static final int ERROR_CODE_ENTITY_NOT_EXIST = 404;
    /**  传入参数异常  **/
    public static final int ERROR_CODE_INPUT_PARAM_ERROR = 441;
    /**  不支持的逻辑操作  */
    public static final int ERROR_CODE_NOT_SUPPORT_ACTION = 442;
    /**  无权操作  */
    public static final int ERROR_CODE_NOT_UNAUTHORIZED_OPERATION = 401;
    /**  未登陆  */
    public static final int ERROR_CODE_NOT_LOGIN = 411;
    /**  操作失败  **/
    public static final int ERROR_CODE = 500;

}
