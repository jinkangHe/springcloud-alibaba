package com.cloud.common;

public class CommonResult<T> {

    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "操作成功";
    private static final Integer FAILED_CODE = 400;
    private static final String FAILED_MESSAGE = "操作失败";

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> CommonResult<T> success(String message,T data) {
        CommonResult<T> result = new CommonResult<>();
        result.data = data;
        result.message = message;
        result.code = SUCCESS_CODE;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.data = data;
        result.message = SUCCESS_MESSAGE;
        result.code = SUCCESS_CODE;
        return result;
    }

    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<>();
        result.message = SUCCESS_MESSAGE;
        result.code = SUCCESS_CODE;
        return result;
    }

    public static <T> CommonResult<T> success(Integer code , String message,T data) {
        CommonResult<T> result = new CommonResult<>();
        result.data = data;
        result.message = message;
        result.code = code;
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
