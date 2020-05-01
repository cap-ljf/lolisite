package com.lolisite.usercenter.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author cap_ljf
 * @description 注册密码校验器
 * @create 2020/04/12 23:37
 */
public class RegisterPwdCheckValidator implements ConstraintValidator<RegisterPwdCheck, Object> {

    private String firstPwd;

    private String secondPwd;

    @Override
    public void initialize(RegisterPwdCheck constraintAnnotation) {
        this.firstPwd = constraintAnnotation.firstPwd();
        this.secondPwd = constraintAnnotation.secondPwd();
    }

    /**
     * 前后两次输入的密码需要相等
     *
     * @param value   校验对象
     * @param context validator上下文
     * @return 校验结果
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        } else {
            BeanWrapper beanWrapper = new BeanWrapperImpl(value);
            Object firstValue = beanWrapper.getPropertyValue(firstPwd);
            Object secondValue = beanWrapper.getPropertyValue(secondPwd);
            if (firstValue == null || secondValue == null) {
                return false;
            }
            return firstValue.equals(secondValue);
        }
    }
}
