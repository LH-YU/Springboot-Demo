package com.example.demo.controller;

import com.example.demo.result.JsonResult;
import com.example.demo.service.UserService;
import com.example.demo.vo.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mybatisUser")
public class MybatisUserController {

    @Autowired
    private UserService userService;

    //新增
    @RequestMapping("/addUser")
    public JsonResult addUser(){

        DemoUser user = new DemoUser();
        user.setUserId(1);
        user.setUserName("test");
        user.setUserPwd("123");
        user.setUserAge(18);
        user.setUserSex("man");

        userService.addUser(user);

        return JsonResult.ok("新增用户成功");
        
    }

  /*  //删除
    @RequestMapping("/deleteUser")
    public void deleteUser(){

    }

    //修改
    @RequestMapping("/updateUser")
    public void updateUser(){

    }

    //根据id查询
    @RequestMapping("/getUserById")
    public void getUserById(){

    }

    //查询所有
    @RequestMapping("/getAllUser")
    public void getAllUser(){

    }*/


}
