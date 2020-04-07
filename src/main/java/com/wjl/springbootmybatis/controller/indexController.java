package com.wjl.springbootmybatis.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/18
 * \* Time: 14:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller

public class indexController {

    @RequestMapping("/")
    public String regist(){
        return "index";
    }


    @RequestMapping("/listProducts")
    public String listProducts(){
        return "products";
    }


    @RequestMapping("/orderInfo")
    public String orderInfo(){
        return "orderInfo";
    }





}