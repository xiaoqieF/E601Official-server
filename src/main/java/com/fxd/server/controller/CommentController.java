package com.fxd.server.controller;

import com.fxd.server.pojo.Comment;
import com.fxd.server.response.Result;
import com.fxd.server.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 添加评论
    @PostMapping("private/comment")
    public Result addComment(@RequestBody Comment comment) {
        if (commentService.addComment(comment) > 0) {
            return Result.success();
        }
        return Result.failed("添加评论失败！");
    }

    //获取一篇博客的评论
    @GetMapping("public/comment/{blogId}")
    public Result getCommentByBlogId(@PathVariable Long blogId) {
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        if (comments != null) {
            return Result.success(comments);
        }
        return Result.failed("获取评论失败！");
    }
}
