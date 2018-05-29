package com.example.demo.controller;

import com.example.demo.result.JsonResult;
import com.example.demo.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Title: UserController.java
 * @Description: springboot 封装实体进行页面展示测试
 *                 直接展示封装实体信息
 *                 封装到JsonResult对象中进行实体信息展示
 *
 *                 @RestController注解与@Controller + @ResponseBody注解的比较使用
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */

//@Controller
@RestController  // @RestController = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
   // @ResponseBody
    public User getUser(){

        User u = new User();
        u.setName("Demo");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPwd("test");
        u.setDes(null);

        return u;

    }

    @RequestMapping("/getUserJson")
    //@ResponseBody
    public JsonResult getUserJson(){

        User u = new User();
        u.setName("Demo");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPwd("test");
        u.setDes(null);

        return JsonResult.ok(u);

    }

}
