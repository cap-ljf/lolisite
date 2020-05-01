package com.lolisite.usercenter.pojo;

import com.lolisite.usercenter.validator.RegisterPwdCheck;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author cap_ljf
 * @description 注册信息
 * @create 2020/04/12 20:41
 */
@Data
@RegisterPwdCheck(firstPwd = "password", secondPwd = "secondPassword", message = "两次输入的密码需要一致")
public class RegisterInput implements Serializable {

    /**
     * 邮箱
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
//    @Pattern(regexp = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$")
    private String email;

    /**
     * 密码
     */
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    /**
     * 第二次输入的密码
     */

    private String secondPassword;

}
