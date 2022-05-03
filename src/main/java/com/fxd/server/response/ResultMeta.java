package com.fxd.server.response;

public enum ResultMeta {
    SUCCESS(200, "成功"),
    FAILED(400, "请求失败"),
    OTHER(402, "其它错误");

    private Integer code;
    private String message;

    ResultMeta(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
