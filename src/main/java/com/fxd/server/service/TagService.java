package com.fxd.server.service;

import com.fxd.server.pojo.Type;

import java.util.List;

public interface TagService {
    List<Type> getTagByUserId(Long userId);
    int addTag(Long userId, String tagName);
    int deleteTag(Long id);
}
