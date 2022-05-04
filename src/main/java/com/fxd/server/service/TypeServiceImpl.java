package com.fxd.server.service;

import com.fxd.server.dao.TypeMapper;
import com.fxd.server.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    private final TypeMapper mapper;

    @Autowired
    public TypeServiceImpl(TypeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Type> getTypeByUserId(Long userId) {
        return mapper.getTypeByUserId(userId);
    }

    @Override
    public int addType(Long userId, String name) {
        Type type = mapper.getTypeByUserIdAndName(userId, name);
        if (type == null) {
            return mapper.addType(userId, name);
        }
        return 0;
    }

    @Override
    public int deleteType(Long id) {
        return mapper.deleteType(id);
    }
}
