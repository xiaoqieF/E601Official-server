package com.fxd.server.service;

import com.fxd.server.pojo.Blog;
import com.github.pagehelper.PageInfo;


public interface BlogService {
    PageInfo<Blog> getBlogsByUserId(Integer page, Integer offset, Long userId);
    int addBlog(Blog blog);
    int deleteBlogById(Long id);
    Blog getRawBlogById(Long id);
    int updateBlog(Blog blog);
}
