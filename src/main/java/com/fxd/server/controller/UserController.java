package com.fxd.server.controller;

import com.fxd.server.pojo.User;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import com.fxd.server.service.UserService;
import com.fxd.server.utils.FileUploadUtil;
import com.fxd.server.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

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

    // 用户登录
    @PostMapping("public/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return userService.login(username, password);
    }

    @GetMapping("public/user/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Result.failed("用户ID无效");
        } else {
            return Result.success(user);
        }
    }

    @GetMapping("private/user/{id}")
    public Result getUserAllInfo(@PathVariable("id") Integer id) {
        User user = userService.getUserAllInfoById(id);
        if (user == null) {
            return Result.failed("用户ID无效");
        } else {
            return Result.success(user);
        }
    }

    @PutMapping("private/user/{id}")
    public Result updateUserInfo(@PathVariable("id") Integer id, @RequestBody User user) {
        int res = userService.updateUserInfo(user);
        if (res > 0) {
            return Result.success();
        } else {
            return Result.failed("修改用户信息失败！");
        }
    }
}
