package com.fxd.server.controller;

import com.fxd.server.pojo.Type;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import com.fxd.server.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class TypeController {
    @Autowired
    private TypeService typeService;

    // 查询所有分类
    @GetMapping("/public/categories/{userId}")
    public Result getCateByUserId(@PathVariable("userId") Long userId) {
        return Result.success(typeService.getTypeByUserId(userId));
    }

    // 添加分类
    @PostMapping("/private/categories/{userId}")
    public Result addCate(@PathVariable("userId") Long userId, @RequestBody Type type) {
        log.info("type:{}", type);
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
