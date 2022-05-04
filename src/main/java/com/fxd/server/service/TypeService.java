package com.fxd.server.service;

import com.fxd.server.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeService {
    List<Type> getTypeByUserId(Long userId);
    int addType(Long userId, String name);
    int deleteType(Long id);
}
