package com.coffee.security;

import com.coffee.constant.TokenCodeEnum;
import com.coffee.kit.Data;
import com.coffee.kit.ResultData;
import com.coffee.po.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class AuthorizationAspect {
    ResultData<Data> resultData;

    private static  String SECRET = "coffee";

    public AuthorizationAspect() {
        resultData = new ResultData<>();
    }

    public TokenCodeEnum verifyToken(User user, String token){
        if(token == null)
            return TokenCodeEnum.TOKEN_NOT_EXIST;
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            Claims result = claims.getBody();
            //获取token中存储的用户信息
            HashMap userMap = (LinkedHashMap) result.get("user");
            //将Map信息转化为user类
            User userToken = new User((String)(userMap.get("name")),(String)(userMap.get("password")),(String)(userMap.get("userType")),Integer.toUnsignedLong((Integer) userMap.get("id")));
            System.out.println("userToken"+userToken);
            System.out.println("user"+user);
            if(userToken.equals(user))//校验userId,userType,userName
                return TokenCodeEnum.TOKEN_OK; //校验成功返回ok信息
            else
                return TokenCodeEnum.TOKEN_ERROR; //校验失败返回失败信息
        } catch (ExpiredJwtException e) {
            System.out.println("time out");
            return TokenCodeEnum.TOKEN_TIME_OUT;  //token超时
        } catch (Exception e){
            System.out.println("error token");
            return TokenCodeEnum.TOKEN_FORMAT_ERROR; //token格式错误
        }
    }

    public static String createToken(User user){
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,24); //24小时以后过期
        Date expireDate = nowTime.getTime();
        Map<String,Object> map = new HashMap<>();
        map.put("alg","HS256"); //SHA256加密
        map.put("typ","JWT");
        String token = null; //加密
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("user",user);
        token = Jwts.builder()
                .setHeader(map)
                .claim("user",user)
                .setExpiration(expireDate) //到期时间
                .setIssuedAt(iatDate) //发布时间
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
        return token;
    }

    @RequestMapping("/response")
    @ResponseBody
    public ResultData responseJson(HttpServletRequest req) {
        return (ResultData) req.getAttribute("resultData");
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("index.html");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setUserType("1");
        user.setName("admin");
        User user1 = new User("admin","123","1",1L);
        String token = createToken(user);
        AuthorizationAspect authorizationAspect = new AuthorizationAspect();
        TokenCodeEnum result = authorizationAspect.verifyToken(user1,token);
        System.out.println(result);

    }
}
