package com.fxd.server.service;

import com.fxd.server.pojo.User;

public interface UserService {
    /**
     * 用户注册
     * @return 注册成功返回 1, 否则返回 0
     */
    int signUp(User user);
}
