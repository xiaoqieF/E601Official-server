package com.fxd.server.service;

import com.fxd.server.pojo.Blog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BlogService {
    PageInfo<Blog> getBlogsByUserId(Integer page, Integer offset, Long userId);
    int addBlog(Blog blog);
}
