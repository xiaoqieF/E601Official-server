package com.fxd.server.service;

import com.fxd.server.dao.UserMapper;
import com.fxd.server.pojo.User;
import com.fxd.server.response.Result;
import com.fxd.server.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int signUp(User user) {
        if (mapper.getUserCountByName(user.getUsername()) == 0) {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            return mapper.addUser(user);
        }
        return 0;
    }

    @Override
    public Result login(String username, String password) {
        User user = mapper.getUserByNameAndPassword(username, password);
        if (user != null) {
            // 登录成功，发放token
            Map<String, String> payload = new HashMap<>();
            payload.put("id", user.getId().toString());
            payload.put("username", user.getUsername());
            payload.put("password", user.getPassword());
            // 2 hours
            String token = JWTUtil.getToken(payload, 2*60*60);
            Map<String, Object> res = new HashMap<>();
            res.put("token", token);
            res.put("user", user);
            return Result.success(res);
        }
        // 密码错误
        if (mapper.getUserCountByName(username) > 0) {
            return Result.failed("密码错误！");
        // 用户不存在
        } else {
            return Result.failed("用户名不存在！");
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = mapper.getUserById(id);
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User getUserAllInfoById(Integer id) {
        return mapper.getUserById(id);
    }

    @Override
    public int updateUserInfo(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }


}
