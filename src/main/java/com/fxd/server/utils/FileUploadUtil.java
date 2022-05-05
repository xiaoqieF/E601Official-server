package com.fxd.server.utils;

import com.fxd.server.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {

    static private final String winFileSavePath = "E:\\upload";

    static private final String unixFileSavePath = "/opt/upload/";

    /**
     * 接收文件，并保存至 path 处
     * @param path 保存的路径，此路径为相对与上传文件夹的路径
     * @param uploadFile 从客户端接收的上传文件
     * @return Result
     * @throws IOException
     */
    public static Result saveTo(String path, MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        String realPath = getRealPath(path);
        File saveDir = new File(realPath);
        if (!saveDir.isDirectory()) {
            saveDir.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID() + oldName.substring(oldName.indexOf("."));
        uploadFile.transferTo(new File(saveDir, newName));

        log.info("接收到上传文件：{}；保存至：{}", oldName, realPath + "/" + newName);

        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + "/images" + path + "/" + newName);
        data.put("relativePath", newName);
        log.info("access url:{}",
                req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                        + "/images" + path + "/" + newName );
        return Result.success(data);
    }

    // 根据操作系统获取上传路径
    private static String getRealPath(String path) {
        String os = System.getProperty("os.name");
        String realPath;
        // win系统
        if (os.toLowerCase().startsWith("win")) {
            realPath = winFileSavePath + path;
        } else {
            // unix系统
            realPath = unixFileSavePath + path;
        }
        log.info(realPath);
        return realPath;
    }

    public static Result removeFile(String path) throws IOException {
        String realPath = getRealPath(path);
        log.info("remove:{}", realPath);
        boolean deleted = Files.deleteIfExists(Paths.get(realPath));
        if (deleted) {
            return Result.success();
        }
        return Result.failed("删除文件失败:" + path);
    }
}
