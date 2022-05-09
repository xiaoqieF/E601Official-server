package com.fxd.server.service;

import com.fxd.server.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);
    List<Comment> getCommentByBlogId(Long blogId);
}
