package com.example.venue.service;

import com.example.venue.domain.Order;
import com.example.venue.domain.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUser(String uPhone);

    public void insertUser(User user);

    public void updateUser(User user);
}
