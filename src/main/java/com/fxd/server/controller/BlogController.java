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

    @PostMapping("/private/blog")
    public Result addBlog(@RequestBody Blog blog) {
        if (blogService.addBlog(blog) > 0) {
            return Result.success();
        }
        return Result.failed("插入文章失败！");
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
