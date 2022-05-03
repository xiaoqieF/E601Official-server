package com.fxd.server.interceptor;

import com.fxd.server.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 拦截器中不处理异常，而是将其转发至全局异常处理器统一处理
        try {
            JWTUtil.verify(token);
        } catch (Exception e) {
            request.setAttribute("interceptorError", e);
            request.getRequestDispatcher("/error/throw").forward(request, response);
            return false;
        }
        return true;
    }
}