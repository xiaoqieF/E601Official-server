package com.fxd.server.service;

import com.fxd.server.pojo.User;
import com.fxd.server.response.Result;

public interface UserService {
    /**
     * 用户注册
     * @return 注册成功返回 1, 否则返回 0
     */
    int signUp(User user);

    Result login(String username, String password);

    User getUserById(Integer id);

    User getUserAllInfoById(Integer id);

    int updateUserInfo(User user);
}
