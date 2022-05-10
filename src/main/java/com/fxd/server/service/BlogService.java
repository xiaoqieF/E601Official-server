package com.fxd.server.service;

import com.fxd.server.pojo.Blog;
import com.github.pagehelper.PageInfo;


public interface BlogService {
    /**
     * 分页获取用户的所有文章
     * @param page
     * @param offset
     * @param userId 用户id
     * @param published 是否只获取已发表的文章
     * @return
     */
    PageInfo<Blog> getBlogsByUserId(Integer page, Integer offset, Long userId, boolean published);
    int addBlog(Blog blog);
    int deleteBlogById(Long id);
    Blog getRawBlogById(Long id);
    Blog getRenderedBlogById(Long id);
    int updateBlog(Blog blog);
    PageInfo<Blog> getAllBlogs(Integer page, Integer offset);
    int increaseBlogViews(Long blogId);
}
