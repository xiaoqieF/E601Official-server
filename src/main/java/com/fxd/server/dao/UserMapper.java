package com.fxd.server.dao;

import com.fxd.server.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int addUser(User user);
    User getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
    int getUserCountByName(@Param("username") String username);

}
