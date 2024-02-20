package com.moxi.mogublog.gateway.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token是服务器签发的一串加密字符串，是为了给客户端重复访问的一个令牌，作用是为了证明请求者（客户端）的身份，保持用户长期保持登录状态
 * JWT就是token的一种载体，或者说JWT是一种标准，而Token是一个概念，而JWT就是这个概念执行的一种规范，通俗点说token就是一串字符串，
 * jwt就是加上类型，信息等数据再加密包装一下成为一个新的token
 *
 * JwtProvider需要至少提供两个方法，一个用来创建我们的token，另一个根据token获取Authentication。
 * provider需要保证Key密钥是唯一的，使用init()构建，否则会抛出异常。
 * @author : Madlife
 * @date : Created in 2022/2/9 14:12
 */
@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息生成token
     *
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("CLAIM_KEY_USERNAME", userName);
        claims.put("CLAIM_KEY_CREATED", new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.get("CLAIM_KEY_USERNAME").toString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userName
     * @return
     */
    public boolean validateToken(String token,String userName){
        String username = getUserNameFromToken(token);
        return username.equals(userName) && !isTokenExpired(token);
    }


    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put("CLAIM_KEY_CREATED",new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    public Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 根据荷载生成JWT TOKEN
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(claims.get("CLAIM_KEY_USERNAME").toString())
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        // 向后推7天
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


}


