package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: .java
 * @Description: 页面展示实体封装
 *
 *                 @JsonIgnore 注解使用
 *                 @JsonFormat 注解使用
 *                 @JsonInclude 注解使用
 *
 * @author LH-Yu
 * @Date 2018-5-28
 * @version V1.0
 */
public class User {

    private String name;
    @JsonIgnore   //用户密码不进行显示
    private String pwd;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8") //时间日期格式化显示
    private Date birthday;
    @JsonInclude(JsonInclude.Include.NON_NULL) //为空不进行显示
    private String des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getBirthday() {

        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
