package com.fxd.server.controller;

import com.fxd.server.pojo.User;
import com.fxd.server.response.Result;
import com.fxd.server.service.UserService;
import com.fxd.server.utils.FileUploadUtil;
import com.fxd.server.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    static private String AUTH_CODE_NORM = "A39E8B39C5ABDABF";
    static private String AUTH_CODE_ADMIN = "XIAODONGFAN";
    private final UserService userService;
    private final HttpServletRequest request;

    public UserController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @PostMapping("/public/signup/{authCode}")
    public Result signUp(@RequestBody User user, @PathVariable("authCode") String authCode) {
        log.info("User:{}", user);
        if (authCode.equals(AUTH_CODE_NORM)) {
            user.setType(1);
        } else if (authCode.equals(AUTH_CODE_ADMIN)) {
            user.setType(0);
        } else {
            return Result.failed("授权码错误");
        }
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

    // 获取某个用户信息(不包含密码)
    @GetMapping("public/user/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Result.failed("用户ID无效");
        } else {
            return Result.success(user);
        }
    }

    // 获取某个用户信息(包含密码)
    @GetMapping("private/user/{id}")
    public Result getUserAllInfo(@PathVariable("id") Integer id) {
        // 验证用户token id
        String token = request.getHeader("Authorization");
        if (!JWTUtil.verify(token).getClaim("id").asString().equals(id.toString())) {
            return Result.failed("token无效");
        }
        User user = userService.getUserAllInfoById(id);
        if (user == null) {
            return Result.failed("用户ID无效");
        } else {
            return Result.success(user);
        }
    }

    @GetMapping("public/allUsers")
    public Result getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return Result.success(users);
        }
        return Result.failed("获取用户信息失败！");
    }

    // 修改用户信息
    @PutMapping("private/user/{id}")
    public Result updateUserInfo(@PathVariable("id") Integer id, @RequestBody User user) {
        // 验证用户token id
        String token = request.getHeader("Authorization");
        if (!JWTUtil.verify(token).getClaim("id").asString().equals(id.toString())) {
            return Result.failed("token无效");
        }
        int res = userService.updateUserInfo(user);
        if (res > 0) {
            return Result.success();
        } else {
            return Result.failed("修改用户信息失败！");
        }
    }
}
