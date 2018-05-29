package com.example.demo.controller;

import com.example.demo.result.JsonResult;
import com.example.demo.vo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: TestResourceController.java
 * @Description: springboot 加载资源文件配置测试
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */

@RestController
@RequestMapping(value = "/testResource")
public class TestResourceController {

    @Autowired
    private Resource resource;

    @RequestMapping(value = "/getResource")
    public JsonResult getResource(){

        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);

        return JsonResult.ok(bean);

    }



}
