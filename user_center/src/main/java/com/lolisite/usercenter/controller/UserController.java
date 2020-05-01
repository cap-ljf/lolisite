package com.lolisite.usercenter.controller;

import com.lolisite.usercenter.pojo.RegisterInput;
import com.lolisite.usercenter.util.ResponseUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author cap_ljf
 * @description 用户api
 * @create 2020/04/12 18:40
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping("/register")
    public Object register(@Valid RegisterInput input, BindingResult bindingResult) {
        //验证
        return ResponseUtil.success(input);
    }

}
