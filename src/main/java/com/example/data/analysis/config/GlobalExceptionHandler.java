package com.example.data.analysis.config;

import com.example.data.analysis.utils.response.IResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/12 22:30
 */
@RestControllerAdvice  // 全局异常处理注解，用于捕获和处理控制器抛出的异常
public class GlobalExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)  // 指定处理过期的JWT异常
    public IResponse handleJwtExpired(ExpiredJwtException e) {
        // 返回 401 状态码和过期提示 // 返回给客户端的错误信息 // 设置HTTP状态码为401未授权
        return IResponse.fail("令牌已过期，请重新登录").setCode(HttpStatus.UNAUTHORIZED.toString()) ;

    }
}
