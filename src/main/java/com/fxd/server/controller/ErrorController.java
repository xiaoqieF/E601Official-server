package com.fxd.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// 全局异常转发，用于将过滤器或拦截器中的异常交由全局异常处理器处理
@RestController
public class ErrorController {

    // 异常转发
    @RequestMapping("/error/throw")
    public void rethrow(HttpServletRequest request) throws Exception {
        // 将拦截器中的异常重新抛出，便于全局异常捕获处理
        throw ((Exception) request.getAttribute("interceptorError"));
    }
}
