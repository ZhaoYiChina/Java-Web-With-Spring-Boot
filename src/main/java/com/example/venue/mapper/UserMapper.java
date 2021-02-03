package com.example.venue.mapper;

import com.example.venue.domain.Order;
import com.example.venue.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public List<User> getAllUsers();

    public User getUser(String uPhone);

    public void insertUser(User user);

    public void updateUser(User user);

}
