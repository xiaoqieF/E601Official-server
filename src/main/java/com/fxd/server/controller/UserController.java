package com.fxd.server.controller;

import com.fxd.server.pojo.User;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import com.fxd.server.service.UserService;
import com.fxd.server.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/public/signup")
    public Result signUp(@RequestBody User user) {
        log.info("User:{}", user);
        if (userService.signUp(user) > 0) {
            return Result.success();
        } else {
            return Result.failed("用户名已存在！");
        }
    }

    // 上传用户头像
    @PostMapping("/public/signup/upload")
    public Result uploadAvatar(@RequestParam("file") MultipartFile uploadFile,
                               HttpServletRequest req) throws IOException {
        return FileUploadUtil.saveTo("/userAvatar", uploadFile, req);
    }

    @PostMapping("public/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        switch (userService.login(username, password)) {
            case 1:
                return Result.success();
            case 0:
                return Result.failed("密码错误！");
            default:
                return Result.failed("用户名不存在！");
        }
    }
}
