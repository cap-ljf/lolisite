package com.lolisite.usercenter.pojo.constant;

/**
 * @author cap_ljf
 * @description: rest请求响应码
 * @create 2020/04/13 00:12
 */
public enum ResponseErrorCode {
    success(0, "请求成功"),
    param_check_fail(2, "参数校验失败");


    private Integer code;
    private String desc;

    ResponseErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
