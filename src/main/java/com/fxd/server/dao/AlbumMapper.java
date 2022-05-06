package com.fxd.server.dao;

import com.fxd.server.pojo.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumMapper {
    int addAlbum(Album album);
    List<Album> getAlbumsByUserId(@Param("userId") Long userId);
    int deleteAlbumById(@Param("albumId") Long albumId);
}
