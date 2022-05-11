package com.fxd.server.dao;

import com.fxd.server.pojo.About;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiteInfoMapper {
    int updateAbout(About about);
    About getAbout();
}
