package com.fxd.server.controller;

import com.fxd.server.pojo.Type;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import com.fxd.server.service.TypeService;
import com.fxd.server.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
public class TypeController {
    private final TypeService typeService;
    private final HttpServletRequest request;

    public TypeController(TypeService typeService, HttpServletRequest request) {
        this.typeService = typeService;
        this.request = request;
    }

    // 查询所有分类
    @GetMapping("/public/categories/{userId}")
    public Result getCateByUserId(@PathVariable("userId") Long userId) {
        return Result.success(typeService.getTypeByUserId(userId));
    }

    // 添加分类
    @PostMapping("/private/categories/{userId}")
    public Result addCate(@PathVariable("userId") Long userId, @RequestBody Type type) {
        log.info("type:{}", type);
        // 验证用户token id
        String token = request.getHeader("Authorization");
        if (!JWTUtil.verify(token).getClaim("id").asString().equals(userId.toString())) {
            return Result.failed("token无效");
        }
        if (typeService.addType(userId, type.getName()) > 0) {
            return Result.success();
        }
        return Result.failed("不允许添加重复标签");
    }

    @DeleteMapping("/private/categories/{typeId}")
    public Result deleteCate(@PathVariable("typeId") Long typeId) {

        if (typeService.deleteType(typeId) > 0) {
            return Result.success();
        }
        return Result.failed(ResultMeta.DEFAULT_EXCEPTION);
    }
}
