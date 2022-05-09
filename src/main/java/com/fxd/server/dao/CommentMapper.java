package com.fxd.server.dao;

import com.fxd.server.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int addComment(Comment comment);
    List<Comment> getCommentByBlogId(Long blogId);
}
