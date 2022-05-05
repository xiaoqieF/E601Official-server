package com.fxd.server.controller;

import com.fxd.server.pojo.Blog;
import com.fxd.server.response.Result;
import com.fxd.server.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 获取某个用户的全部博客信息
    @GetMapping("/private/blog/{userId}")
    public Result getAllBlogsByUserId(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                      @PathVariable("userId") Long userId) {
        PageInfo<Blog> pageInfo = blogService.getBlogsByUserId(pageNum, pageSize, userId);
        Map<String, Object> res = new HashMap<>();
        res.put("total", pageInfo.getTotal());
        res.put("blogList", pageInfo.getList());
        return Result.success(res);
    }
}
