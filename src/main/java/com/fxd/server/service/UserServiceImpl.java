package com.fxd.server.service;

import com.fxd.server.dao.UserMapper;
import com.fxd.server.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
