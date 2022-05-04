package com.fxd.server.response;

public enum ResultMeta {
    SUCCESS(200, "成功"),
    FAILED(400, "请求失败"),
    DEFAULT_EXCEPTION(500, "服务器错误"),
    TOKEN_ERROR_EXCEPTION(505, "无效的token"),
    TOKEN_EXPIRED_EXCEPTION(506, "token超时"),
    DATA_ACCESS_EXCEPTION(508, "数据库操作异常：不允许操作该表项！"),
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
