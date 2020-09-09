package com.offcn.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2020/8/14 11:09
 * @Description: 登录信息控制器
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    //欢迎登录人姓名
    @RequestMapping("/getLoginName")
    public Map getLoginName(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("username",username);
        return map;
    }
}
