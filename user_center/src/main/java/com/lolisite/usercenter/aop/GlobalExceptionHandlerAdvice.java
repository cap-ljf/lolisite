package com.lolisite.usercenter.aop;

import com.lolisite.usercenter.pojo.constant.ResponseErrorCode;
import com.lolisite.usercenter.util.ResponseUtil;
import com.lolisite.usercenter.vo.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author cap_ljf
 * @description 全局错误异常切面
 * @create 2020/04/13 00:16
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse handler(HttpServletRequest request, ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            builder.append(constraintViolation.getMessage());
        }
        return ResponseUtil.fail(ResponseErrorCode.param_check_fail.getCode(), builder.toString(), null);
    }

}
