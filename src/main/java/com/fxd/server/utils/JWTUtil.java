package com.fxd.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

// jwt 辅助类，用于生成和验证token
@Slf4j
public class JWTUtil {
    // fxd1999 md5加密大写
    // 用于 JWT 签名的密钥
    private static final String SIGN_KEY = "A39E8B39C5ABDABF";

    // 默认过期时间 30 min
    private final Integer DEFAULT_EXPIRES = 60 * 30;

    // token 默认长度
    private static final Integer DEFAULT_TOKEN_SIZE = 3;

    /**
     * 使用 jwt 获取 token
     * @param map payload of jwt
     * @param expires token time to live (second)
     * @return token
     */
    public static String getToken(Map<String, String> map, Integer expires) {
        Date expireDate = new Date(System.currentTimeMillis() + expires * 1000);
        log.info("[getToken]，过期时间：{}", expireDate);
        JWTCreator.Builder builder = JWT.create();

        // 指定 payload
        map.forEach(builder::withClaim);
        // 指定过期时间
        builder.withExpiresAt(expireDate);

        // 指定加密方式
        String token = builder.sign(Algorithm.HMAC256(SIGN_KEY));
        return confoundPayload(token);
    }

    /**
     * 解析 token
     * @param codedToken 客户端 token
     */
    public static DecodedJWT verify(String codedToken) {
        if (codedToken == null || "".equals(codedToken)) {
            throw new JWTDecodeException("无效的token");
        }
        String token = deConfoundPayload(codedToken);
        return JWT.require(Algorithm.HMAC256(SIGN_KEY)).build().verify(token);
    }

    private static String confoundPayload(String token) {
        return reversePayload(token);
    }

    private static String deConfoundPayload(String codedToken) {
        return reversePayload(codedToken);
    }

    /**
     * 将payload字段翻转处理，因为该字段未被加密
     */
    private static String reversePayload(String token) {
        String[] split = token.split("\\.");
        if (split.length != DEFAULT_TOKEN_SIZE) {
            throw new JWTDecodeException("签名格式错误");
        }
        String payload = split[1];
        return split[0] + "." + new StringBuilder(payload).reverse().toString() + "." + split[2];
    }
}
