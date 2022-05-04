package com.fxd.server.dao;

import com.fxd.server.pojo.Tag;
import com.fxd.server.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Type> getTagByUserId(Long userId);
    int addTag(@Param("userId") Long userId, @Param("tagName") String tagName);
    int deleteTag(@Param("id") Long id);
    Tag getTagByUserIdAndName(@Param("userId") Long userId , @Param("name") String name);
}
