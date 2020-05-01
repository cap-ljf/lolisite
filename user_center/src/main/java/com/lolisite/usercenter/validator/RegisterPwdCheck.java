package com.lolisite.usercenter.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author cap_ljf
 * @description: 注册密码校验
 * @create 2020/04/12 23:33
 */
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterPwdCheckValidator.class)
@Documented
public @interface RegisterPwdCheck {

    String firstPwd();

    String secondPwd();

    String message() default "注册密码校验";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
