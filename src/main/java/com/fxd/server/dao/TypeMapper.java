package com.fxd.server.dao;

import com.fxd.server.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<Type> getTypeByUserId(Long userId);
    int addType(@Param("userId") Long userId, @Param("name") String name);
    int deleteType(@Param("id") Long id);
    Type getTypeByUserIdAndName(@Param("userId") Long userId ,@Param("name") String name);
}
