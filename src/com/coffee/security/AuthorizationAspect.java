package com.coffee.security;

import com.coffee.constant.TokenCodeEnum;
import com.coffee.kit.Data;
import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationAspect {
    ResultData<Data> resultData;

    private static  String SECRET = "coffee";

    public AuthorizationAspect() {
        resultData = new ResultData<>();
    }

    public TokenCodeEnum verifyToken(String username, String token){
        if(token == null)
            return TokenCodeEnum.TOKEN_NOT_EXIST;
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            Claims result = claims.getBody();
            System.out.println(result);
            if(result.get("username").equals(username))
                return TokenCodeEnum.TOKEN_OK;
            else
                return TokenCodeEnum.TOKEN_ERROE;
        } catch (ExpiredJwtException e) {
            System.out.println("time out");
            return TokenCodeEnum.TOKEN_TIME_OUT;
        } catch (Exception e){
            System.out.println("error token");
            return TokenCodeEnum.TOKEN_ERROE;
        }
    }

    public static String createToken(String username){
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,2); //100f分钟后过期
        Date expireDate = nowTime.getTime();
        Map<String,Object> map = new HashMap<>();
        map.put("alg","HS256"); //SHA256加密
        map.put("typ","JWT");
        String token = null; //加密
        token = Jwts.builder()
                .setHeader(map)
                .claim("username",username)
                .setExpiration(expireDate) //到期时间
                .setIssuedAt(iatDate) //发布时间
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
        return token;
    }
}
