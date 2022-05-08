package com.fxd.server.service;

import com.fxd.server.pojo.Album;

import java.util.List;

public interface AlbumService {
    int addAlbum(Album album);
    List<Album> getAlbumsByUserId(Long userId);
    int deleteAlbumById(Long albumId);
    Album getAlbumById(Long id);
    int updateAlbum(Album album);
    List<Album> getAllAlbums();
}
