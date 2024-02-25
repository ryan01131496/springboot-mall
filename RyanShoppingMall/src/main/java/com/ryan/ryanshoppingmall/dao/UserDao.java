package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.dto.UserRegisterRequest;
import com.ryan.ryanshoppingmall.model.User;

public interface UserDao {
    public Integer createUser(UserRegisterRequest userRegisterRequest);

    public User getById(Integer userId);
}
