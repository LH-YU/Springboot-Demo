package com.example.demo.controller;

import com.example.demo.vo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: FreemarkerController.java
 * @Description: freemarker模板引擎整合demo
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */

@Controller
@RequestMapping("freemarker")
public class FreemarkerController {

    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("resource", resource);
        return "freemarker/index";
    }

    @RequestMapping("center")
    public String center() {
        return "freemarker/center/center";
    }

}
