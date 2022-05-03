package com.fxd.server.response;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Result(ResultMeta meta, Object data) {
        this.code = meta.getCode();
        this.message = meta.getMessage();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(ResultMeta.SUCCESS, data);
    }

    public static Result failed(ResultMeta resultMeta) {
        return failed(resultMeta, null);
    }

    public static Result failed(ResultMeta resultMeta, Object data) {
        return new Result(resultMeta, data);
    }

    public static Result failed(String msg) {
        ResultMeta meta = ResultMeta.OTHER;
        meta.setMessage(msg);

        return new Result(meta, null);
    }

}
