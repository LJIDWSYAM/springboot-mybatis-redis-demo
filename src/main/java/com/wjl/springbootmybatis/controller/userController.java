package com.wjl.springbootmybatis.controller;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.UserInfo;
import com.wjl.springbootmybatis.service.AddressService;
import com.wjl.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/18
 * \* Time: 15:32
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;

    @RequestMapping("/do_checkUserName")
    @ResponseBody
    public boolean checkUserName(String user_account){
        boolean result = userService.checkUserName(user_account);
        return result;
    }

    @PostMapping(value = "/do_regist")
    @ResponseBody
    public Boolean regist(UserInfo userInfo,ModelAndView modelAndView){
        userService.regist(userInfo);
        String user_account=userInfo.getUser_account();
        Address insertAddress=new Address();
        insertAddress.setUser_account(user_account);
        insertAddress.setPost_no(000000);
        insertAddress.setReciver_address("收货地址");
        insertAddress.setReciver_name("收货人姓名");
        insertAddress.setReciver_phone("电话号码");
        addressService.insertAddress(insertAddress);
        modelAndView.addObject("UserInfo",userInfo);
        if((userInfo.getUser_id()+"")!=null){
            return true;
        }else {
            return false;
        }
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public boolean login(UserInfo userInfo, HttpSession session){
        UserInfo result =userService.checkLogin(userInfo);
        if (result!=null){
            session.setAttribute("UserInfo",result);
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/login_check")
    @ResponseBody
    public boolean login_check(HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute("UserInfo");
        if (userInfo.getUser_account()!=null){
            return true;
        }else {
            return false;
        }
    }

}