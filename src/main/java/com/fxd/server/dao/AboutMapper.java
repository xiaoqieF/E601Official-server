package com.fxd.server.dao;

import com.fxd.server.pojo.About;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AboutMapper {
    int updateAbout(About about);
    About getAbout();
}
