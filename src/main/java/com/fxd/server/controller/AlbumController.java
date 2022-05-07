package com.fxd.server.controller;

import com.fxd.server.pojo.Album;
import com.fxd.server.response.Result;
import com.fxd.server.service.AlbumService;
import com.fxd.server.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // 新建相册
    @PostMapping("/private/album")
    public Result addAlbum(@RequestBody Album album) {
        if (albumService.addAlbum(album) > 0) {
            return Result.success();
        }
        return Result.failed("添加相册失败！");
    }

    // 获取用户的全部相册
    @GetMapping("/private/allAlbums/{userId}")
    public Result getAlbum(@PathVariable("userId") Long userId) {
        List<Album> albums = albumService.getAlbumsByUserId(userId);
        if (albums != null) {
            return Result.success(albums);
        }
        return Result.failed("获取相册失败！");
    }

    // 删除相册
    @DeleteMapping("/private/album/{albumId}")
    public Result deleteAlbum(@PathVariable("albumId") Long albumId) {
        if (albumService.deleteAlbumById(albumId) > 0) {
            return Result.success();
        }
        return Result.failed("删除失败！");
    }

    // 获取某一相册
    @GetMapping("private/album/{albumId}")
    public Result getAlbumById(@PathVariable("albumId") Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        if (album != null) {
            return Result.success(album);
        }
        return Result.failed("未查询到相关album");
    }

    @PutMapping("/private/album")
    public Result updateAlbum(@RequestBody Album album) {
        if (albumService.updateAlbum(album) > 0) {
            return Result.success();
        }
        return Result.failed("更新相册失败！");
    }

    // 上传图片
    @PostMapping("/private/album/picture/{userId}")
    public Result uploadPicture(@RequestParam("file") MultipartFile uploadFile,
                                     HttpServletRequest req, @PathVariable String userId) throws IOException {
        return FileUploadUtil.saveTo("/album/" + userId, uploadFile, req);
    }

    // 删除图片
    @DeleteMapping("/private/album/picture/{userId}/{fileName}")
    public Result deletePicture(@PathVariable("userId") String userId,
                                @PathVariable("fileName") String fileName) throws IOException {
        return FileUploadUtil.removeFile("/album/" + userId + "/" + fileName);
    }
}
