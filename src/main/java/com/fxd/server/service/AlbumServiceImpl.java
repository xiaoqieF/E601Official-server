package com.fxd.server.service;

import com.fxd.server.dao.AlbumMapper;
import com.fxd.server.pojo.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{
    private final AlbumMapper mapper;

    public AlbumServiceImpl(AlbumMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int addAlbum(Album album) {
        album.setCreateTime(new Date());
        album.setUpdateTime(new Date());
        album.setLike(0);
        album.setViews(0);
        return mapper.addAlbum(album);
    }

    @Override
    public List<Album> getAlbumsByUserId(Long userId) {
        return mapper.getAlbumsByUserId(userId);
    }
}
