package com.langting.busopen.vo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 17:49
 **/
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int SUCCESS_STATUS = 200;
    private static final int ERROR_400 = 400;
    private static final int ERROR_401 = 401;
    private static final int ERROR_403 = 403;
    private static final int ERROR_404 = 404;
    private static final int ERROR_500 = 500;

    private static final String SUCCESS_MESSAGE = "成功";
    //成功标志
    private boolean success = true;
    //返回状态码
    private Integer code = 0;
    //返回处理信息
    private String message = "操作成功！";
    //返回数据对象
    private Object result;
    //时间戳
    private long timestamp = System.currentTimeMillis();

    public Result() {
        this.success = true;
        this.code = 200;
        this.message = "成功";
    }

    public static Result result(Object data) {
        return result(data, "成功");
    }

    public static Result result(Object resultData, String tipMessage) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(tipMessage);
        result.setResult(JSONObject.toJSON(resultData));
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static Result result(int code, String resultData, String tipMessage) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(tipMessage);
        result.setResult(resultData);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static Result tipMessage(boolean isSuccess, int code, String tipMessage) {
        Result result = new Result();
        result.setSuccess(isSuccess);
        result.setMessage(tipMessage);
        result.setCode(code);
        result.setResult("");
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static Result failTipMessage(String tipMessage) {
        return tipMessage(false, 200, tipMessage);
    }

    public static Result successTipMessage() {
        return tipMessage(Boolean.TRUE, 200, "成功");
    }

    public static Result successTipMessage(String tipMessage) {
        return tipMessage(Boolean.TRUE, 200, tipMessage);
    }

    public static Result error_500(String tipMessage) {
        return error(500, tipMessage);
    }

    public static Result error_501(String tipMessage) {
        return error(501, tipMessage);
    }

    public static Result error_502(String tipMessage) {
        return error(502, tipMessage);
    }

    public static Result error_400(String tipMessage) {
        return error(400, tipMessage);
    }

    public static Result error_404(String tipMessage) {
        return error(404, tipMessage);
    }

    public static Result error(int status, String message) {
        return tipMessage(Boolean.FALSE, status, message);
    }


}