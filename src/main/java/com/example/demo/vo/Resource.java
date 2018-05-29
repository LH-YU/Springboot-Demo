package com.example.demo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Title: Resource.java
 * @Description: springboot 加载资源文件配置测试实体
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */

@Configuration //表明该实体属性信息从资源文件中获取配置
@ConfigurationProperties(prefix = "com.example.demo.source") //所要加载的前缀
@PropertySource(value="classpath:resource.properties") //配置文件路径 （版本不同 配置方式不同）
public class Resource {

    private String name;
    private String website;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWebsite() {

        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
