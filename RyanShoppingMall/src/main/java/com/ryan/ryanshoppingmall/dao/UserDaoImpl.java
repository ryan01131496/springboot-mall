package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.dto.UserRegisterRequest;
import com.ryan.ryanshoppingmall.model.User;
import com.ryan.ryanshoppingmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user (email, password, created_date, last_modified_date) \n" +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date\n" +
                "FROM user\n" +
                "WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        UserRowMapper userRowMapper = new UserRowMapper();

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, userRowMapper);

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date\n" +
                "FROM user\n" +
                "WHERE email = :email;";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        UserRowMapper userRowMapper = new UserRowMapper();

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, userRowMapper);

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
