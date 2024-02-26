package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dto.UserLoginRequest;
import com.ryan.ryanshoppingmall.dto.UserRegisterRequest;
import com.ryan.ryanshoppingmall.model.User;

public interface UserService {
    public Integer register(UserRegisterRequest userRegisterRequest);

    public User getById(Integer userId);

    public User login(UserLoginRequest userLoginRequest);
}
