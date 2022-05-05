package com.fxd.server.service;

import com.fxd.server.dao.BlogMapper;
import com.fxd.server.pojo.Blog;
import com.fxd.server.pojo.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
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

    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        // 插入博客后，会自动给blog中的id赋值，这个id值用于下面的addBlogTag使用
        int num = mapper.addBlog(blog);
        log.info("插入博客：{}", blog);
        // 向中间表中插入关联数据
        mapper.addBlogTag(blog);
        return num;
    }

    @Override
    public int deleteBlogById(Long id) {
        mapper.deleteBlogTag(id);
        return mapper.deleteBlogById(id);
    }

    @Override
    public Blog getRawBlogById(Long id) {
        Blog blog = mapper.getBlogById(id);
        // 将未填充的字段填充
        blog.setTypeId(blog.getType().getId());
        List<Long> tagIds = blog.getTagId();
        for (Tag tag : blog.getTags()) {
            tagIds.add(tag.getId());
        }
        blog.setUserId(blog.getUser().getId());
        return blog;
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int num = mapper.updateBlog(blog);
        // 操作（博客--标签）关系表，先删除博客对应的原标签，后加入新标签
        mapper.deleteBlogTag(blog.getId());
        mapper.addBlogTag(blog);
        return num;
    }
}
