package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dao.UserDao;
import com.ryan.ryanshoppingmall.dto.UserLoginRequest;
import com.ryan.ryanshoppingmall.dto.UserRegisterRequest;
import com.ryan.ryanshoppingmall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // Check the status of email registration
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("This email has already existed", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // create account
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getById(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail((userLoginRequest.getEmail()));

        if (user == null) {
            log.warn("This email does not exist", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLoginRequest.getPassword())) {
            return user;
        } else {
            log.warn("This password is incorrect", userLoginRequest.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
