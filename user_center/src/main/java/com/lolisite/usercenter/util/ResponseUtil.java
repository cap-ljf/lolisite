package com.lolisite.usercenter.util;

import com.lolisite.usercenter.vo.RestResponse;

/**
 * @author cap_ljf
 * @description rest请求返回响应数据工具
 * @create 2020/04/12 23:47
 */
public final class ResponseUtil {

    public static RestResponse success(Object data) {
        return RestResponse.builder().errorCode(0).errorMessage("success").data(data).build();
    }

    public static RestResponse fail(Integer errorCode, String errorMessage, Object data) {
        return RestResponse.builder().errorCode(errorCode).errorMessage(errorMessage).data(data).build();
    }

    public static RestResponse defaultFail() {
        return RestResponse.builder().errorCode(-1).errorMessage("请求失败!").build();
    }

}
