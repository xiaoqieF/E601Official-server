package com.fxd.server.controller;

import com.fxd.server.pojo.Blog;
import com.fxd.server.response.Result;
import com.fxd.server.service.BlogService;
import com.fxd.server.utils.FileUploadUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 获取某个用户的全部博客信息
    @GetMapping("/private/allBlogs/{userId}")
    public Result getAllBlogsByUserId(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                      @PathVariable("userId") Long userId) {
        PageInfo<Blog> pageInfo = blogService.getBlogsByUserId(pageNum, pageSize, userId);
        Map<String, Object> res = new HashMap<>();
        res.put("total", pageInfo.getTotal());
        res.put("blogList", pageInfo.getList());
        return Result.success(res);
    }

    // 添加博客
    @PostMapping("/private/blog")
    public Result addBlog(@RequestBody Blog blog) {
        if (blogService.addBlog(blog) > 0) {
            return Result.success();
        }
        return Result.failed("插入文章失败！");
    }

    // 删除博客
    @DeleteMapping("/private/blog/{blogId}")
    public Result deleteBlog(@PathVariable("blogId") Long blogId) {
        if (blogService.deleteBlogById(blogId) > 0) {
            return Result.success();
        }
        return Result.failed("删除失败！");
    }

    @GetMapping("/private/blog/{blogId}")
    public Result getRawBlogById(@PathVariable("blogId") Long blogId) {
        Blog blog = blogService.getRawBlogById(blogId);
        if (blog != null) {
            return Result.success(blog);
        }
        return Result.failed("查找博客失败！");
    }

    // 更新博客信息
    @PutMapping("/private/blog")
    public Result updateBlog(@RequestBody Blog blog) {
        if(blogService.updateBlog(blog) > 0) {
            return Result.success();
        }
        return Result.failed("编辑博客失败！");
    }

    // 上传文章首图
    @PostMapping("/private/blog/firstPicture")
    public Result uploadFirstPicture(@RequestParam("file") MultipartFile uploadFile,
                               HttpServletRequest req) throws IOException {
        return FileUploadUtil.saveTo("/firstPicture", uploadFile, req);
    }

    // 删除文章首图
    @DeleteMapping("/private/blog/firstPicture/{fileName}")
    public Result deleteFirstPicture(@PathVariable("fileName") String fileName) throws IOException {
        return FileUploadUtil.removeFile("/firstPicture/" + fileName);
    }
}
