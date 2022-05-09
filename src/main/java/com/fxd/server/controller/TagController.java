package com.fxd.server.controller;

import com.fxd.server.pojo.Type;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import com.fxd.server.service.TagService;
import com.fxd.server.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class TagController {
    private final TagService tagService;
    private final HttpServletRequest request;

    public TagController(TagService tagService, HttpServletRequest request) {
        this.tagService = tagService;
        this.request = request;
    }

    // 查询所有标签
    @GetMapping("/public/tags/{userId}")
    public Result getCateByUserId(@PathVariable("userId") Long userId) {
        return Result.success(tagService.getTagByUserId(userId));
    }

    // 添加标签
    @PostMapping("/private/tags/{userId}")
    public Result addCate(@PathVariable("userId") Long userId, @RequestBody Type tag) {
        // 验证用户token id
        String token = request.getHeader("Authorization");
        if (!JWTUtil.verify(token).getClaim("id").asString().equals(userId.toString())) {
            return Result.failed("token无效");
        }
        log.info("type:{}", tag);
        if (tagService.addTag(userId, tag.getName()) > 0) {
            return Result.success();
        }
        return Result.failed("不允许添加重复标签");
    }

    @DeleteMapping("/private/tags/{tagId}")
    public Result deleteCate(@PathVariable("tagId") Long tagId) {
        if (tagService.deleteTag(tagId) > 0) {
            return Result.success();
        }
        return Result.failed(ResultMeta.DEFAULT_EXCEPTION);
    }
}
