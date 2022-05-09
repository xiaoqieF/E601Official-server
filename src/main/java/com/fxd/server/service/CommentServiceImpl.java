package com.fxd.server.service;

import com.fxd.server.dao.CommentMapper;
import com.fxd.server.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentMapper mapper;

    public CommentServiceImpl(CommentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int addComment(Comment comment) {
        comment.setCreateTime(new Date());
        return mapper.addComment(comment);
    }

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        return mapper.getCommentByBlogId(blogId);
    }
}
