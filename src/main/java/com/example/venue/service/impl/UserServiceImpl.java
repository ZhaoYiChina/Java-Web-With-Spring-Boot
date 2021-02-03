package com.example.venue.service.impl;

import com.example.venue.domain.Order;
import com.example.venue.domain.User;
import com.example.venue.mapper.OrderMapper;
import com.example.venue.mapper.UserMapper;
import com.example.venue.service.OrderService;
import com.example.venue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUser(String uPhone) {
        return userMapper.getUser(uPhone);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
