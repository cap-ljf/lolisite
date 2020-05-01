package com.lolisite.usercenter.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cap_ljf
 * @description rest请求返回数据
 * @create 2020/04/12 23:53
 */
@Builder
@Data
public class RestResponse implements Serializable {

    private Integer errorCode;

    private Object data;

    private String errorMessage;

}
