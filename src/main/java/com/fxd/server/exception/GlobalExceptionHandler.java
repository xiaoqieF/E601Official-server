package com.fxd.server.exception;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fxd.server.response.Result;
import com.fxd.server.response.ResultMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 全局异常处理器
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理token异常
     */
    @ResponseBody
    @ExceptionHandler({SignatureVerificationException.class, AlgorithmMismatchException.class, JWTDecodeException.class})
    public Result tokenErrorException() {
        log.error("无效的token");
        return Result.failed(ResultMeta.TOKEN_ERROR_EXCEPTION);
    }

    /**
     * 处理token异常
     */
    @ResponseBody
    @ExceptionHandler({TokenExpiredException.class})
    public Result tokenExpiredException() {
        log.error("token超时");
        return Result.failed(ResultMeta.TOKEN_EXPIRED_EXCEPTION);
    }

    /**
     * 处理数据库操作异常，数据库中外键对应表格的删除和修改会导致异常
     */
    @ResponseBody
    @ExceptionHandler({DataIntegrityViolationException.class})
    public Result sqlConstraintException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return Result.failed(ResultMeta.DATA_ACCESS_EXCEPTION);
    }

    /**
     * 处理所有RuntimeException异常
     */
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public Result allException(RuntimeException e) {
        e.printStackTrace();
        return Result.failed(e.getMessage());
    }

    /**
     * 处理所有Exception异常
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result allException(Exception e) {
        e.printStackTrace();
        return Result.failed(e.getMessage());
    }
}
