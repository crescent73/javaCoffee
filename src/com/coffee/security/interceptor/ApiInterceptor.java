package com.coffee.security.interceptor;

import com.coffee.constant.TokenCodeEnum;
import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.po.User;
import com.coffee.security.AuthorizationAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    AuthorizationAspect authorizationAspect;


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        //会打印请求
        HttpSession session = req.getSession();
        String url= req.getRequestURI();
        ResultData resultData = new ResultData();
        // 对API进行拦截
        if (url.indexOf("/page") <= 0 && url.indexOf(".html") <= 0 &&//不是页面请求
                (url.indexOf("/admin") > 0|| url.indexOf("/system") > 0 || url.indexOf("/teacher") >0)) {
            //用户未登录
            System.out.println(session.getAttribute("login"));
            if(session.getAttribute("login")==null){
                //只有申请登陆的请求会被执行
                if (url.indexOf("/login") > 0) {
                    return true;//登录请求
                } else {//其它请求直接拦截
                    return false;
                }
            } else if(session.getAttribute("login").equals(1)){
                //用户已经登陆 对API进行拦截
                //进行token验证
                User user = (User) session.getAttribute("user");
                System.out.println("user:"+user);
                //从请求信息中获取token
                String userToken = req.getHeader("token");
                System.out.println("userToken:"+userToken);
                TokenCodeEnum result =authorizationAspect.verifyToken((User) session.getAttribute("user"),userToken);
                switch (result.getCode()){
                    case "200":
                        System.out.println("token验证成功");
                        break;
                    case "201"://token不存在
                    case "202"://token失效
                    case "203"://token错误
                    case "204"://token格式错误
                        resultData.setCode(result.getCode()); //设置错误返回码
                        resultData.setMsg(result.getDesc());  //设置错误返回消息
                        req.setAttribute("resultData",resultData);
                        req.getRequestDispatcher("/response").forward(req,resp); //请求转发
                        return false;
                    default:
                        System.out.println("unknown error");
                        return false;
                }
            } else {
                resultData.setResult(ResultCodeEnum.UNKOWN_ERROE); //未知错误
                System.out.println(resultData);
                req.setAttribute("resultData",resultData);
                req.getRequestDispatcher("/response").forward(req,resp);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
