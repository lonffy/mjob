package com.lonffy.mjob.common;

import org.springframework.core.env.Environment;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lonffy
 */
public class RespResult {

    private Integer code;

    private String msg;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    /**
     * 成功 不返回数据直接返回成功信息
     * @return Result
     */
    public static RespResult success() {
        RespResult result = new RespResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功 并且加上返回数据
     * @param data
     * @return Result
     */
    public static RespResult success(Object data) {
        RespResult result = new RespResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 单返回失败的状态码
     * @param resultCode
     * @return
     */
    public static RespResult failure(ResultCode resultCode) {
        RespResult result = new RespResult();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败的状态码
     * @param resultCode 状态码
     * @param message 覆盖返回的消息
     * @return
     */
    public static RespResult failure(ResultCode resultCode,String message) {
        RespResult result = new RespResult();
        result.setResultCode(resultCode);
        result.setMsg(message);
        return result;
    }

    /**
     * 发生错误时返回具体的异常
     * @param e 异常
     * @return
     */
    public static RespResult failure(Exception e) {

        RespResult result = new RespResult();
        result.setResultCode(ResultCode.FAILURE);
        //放异常信息
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        //生产环境异常信息不抛出
        result.setData(sw.toString());
        return result;
    }

    /**
     * 返回状态码和数据
     * @param resultCode 状态码
     * @param data 数据
     * @return
     */
    public static RespResult all(ResultCode resultCode, Object data) {
        RespResult result = new RespResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

}
