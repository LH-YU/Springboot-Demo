package com.example.demo.controller;

import com.example.demo.vo.Resource;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: ThymeleafController.java
 * @Description: thymeleaf模板引擎整合demo
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private Resource resource;
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("test", "testForThymeleaf");
        return "thymeleaf/index";
    }

    @RequestMapping("/center")
    public String center() {
        return "freemarker/center/center";
    }

    @RequestMapping("/user")
    public String test(ModelMap map) {

        User u = new User();
        u.setName("thymeleaf");
        u.setAge(10);
        u.setPwd("123465");
        u.setBirthday(new Date());
        u.setDes("<font color='green'><b>hello thymeleaf</b></font>");

        map.addAttribute("user", u);

       User u1 = new User();
        u1.setAge(19);
        u1.setName("imooc");
        u1.setPwd("123456");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setAge(17);
        u2.setName("LH-Yu");
        u2.setPwd("123456");
        u2.setBirthday(new Date());

        List<User> userList = new ArrayList<User>();
        userList.add(u);
        userList.add(u1);
        userList.add(u2);

        map.addAttribute("userList", userList);

        return "thymeleaf/center/test";
    }

}
