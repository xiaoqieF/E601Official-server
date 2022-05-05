package com.fxd.server.service;

import com.fxd.server.dao.BlogMapper;
import com.fxd.server.pojo.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;



@Service
public class BlogServiceImpl implements BlogService{
    private final BlogMapper mapper;

    public BlogServiceImpl(BlogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PageInfo<Blog> getBlogsByUserId(Integer page, Integer offset, Long userId) {
        PageHelper.orderBy("blog_create_time desc");
        if (page != null && offset != null)
            PageHelper.startPage(page, offset);
        return new PageInfo<>(mapper.getBlogsByUserId(userId));
    }
}
