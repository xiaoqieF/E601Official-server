package com.fxd.server.controller;

import com.fxd.server.pojo.About;
import com.fxd.server.response.Result;
import com.fxd.server.service.SiteInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SiteInfoController {
    private final SiteInfoService service;

    public SiteInfoController(SiteInfoService service) {
        this.service = service;
    }

    @GetMapping("public/about")
    public Result getAbout() {
        About about = service.getAbout();
        if (about != null) {
            return Result.success(about);
        }
        return Result.failed("获取关于失败！");
    }

    @GetMapping("private/about")
    public Result getRawAbout() {
        About about = service.getRawAbout();
        if (about != null) {
            return Result.success(about);
        }
        return Result.failed("获取关于失败！");
    }

    @PutMapping("private/about")
    public Result updateAbout(@RequestBody About about) {
        log.info("updateAbout:{}", about);
        if (service.updateAbout(about) > 0) {
            return Result.success();
        }
        return Result.failed("修改关于失败！");
    }
}
