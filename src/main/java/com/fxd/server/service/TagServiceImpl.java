package com.fxd.server.service;

import com.fxd.server.dao.TagMapper;
import com.fxd.server.pojo.Tag;
import com.fxd.server.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    private final TagMapper mapper;

    public TagServiceImpl(TagMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Type> getTagByUserId(Long userId) {
        return mapper.getTagByUserId(userId);
    }

    @Override
    public int addTag(Long userId, String tagName) {
        Tag tag = mapper.getTagByUserIdAndName(userId, tagName);
        if (tag == null) {
            return mapper.addTag(userId, tagName);
        }
        return 0;
    }

    @Override
    public int deleteTag(Long id) {
        return mapper.deleteTag(id);
    }
}
