package com.example.demo.service;

import com.example.demo.vo.DemoUser;

import java.util.List;

public interface UserService {

    //新增
    public void addUser(DemoUser user);

    //删除
    public void deleteUser(Integer userId);

    //修改
    public void updateUser(DemoUser user);

    //根据id查询
    public DemoUser getUserById(Integer userId);

    //查询所有
    public List<DemoUser> userList();

}
