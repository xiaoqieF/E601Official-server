package com.fxd.server.dao;

import com.fxd.server.pojo.About;
import com.fxd.server.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    // 获取某个用户的全部博客
    List<Blog> getBlogsByUserId(@Param("userId") Long userId, boolean published);
    Blog getBlogById(@Param("blogId") Long blogId);
    int addBlog(Blog blog);
    // 添加博客标签
    int addBlogTag(Blog blog);
    int deleteBlogById(Long id);
    // 删除博客标签中间表中的相关记录
    int deleteBlogTag(Long blogId);
    int updateBlog(Blog blog);
    // 获取所有已发表的博客
    List<Blog> getAllBlogs();

    int updateBlogViews(Long blogId);
}
