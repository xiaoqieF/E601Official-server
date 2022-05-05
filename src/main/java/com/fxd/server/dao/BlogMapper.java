package com.fxd.server.dao;

import com.fxd.server.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    // 获取某个用户的全部博客
    List<Blog> getBlogsByUserId(@Param("userId") Long userId);
    List<Blog> getBlogById(@Param("blogId") Long blogId);
    int addBlog(Blog blog);
    // 添加博客标签
    int addBlogTag(Blog blog);
}
