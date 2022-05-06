package com.fxd.server.dao;

import com.fxd.server.pojo.Album;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumMapper {
    int addAlbum(Album album);
}
