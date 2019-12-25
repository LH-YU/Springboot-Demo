package com.example.demo.service.impl;

import com.example.demo.mapper.DemoUserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private DemoUserMapper userMapper;

    @Override
    public void addUser(DemoUser user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void updateUser(DemoUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DemoUser getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<DemoUser> userList() {
        return userMapper.selectAll();
    }
}
