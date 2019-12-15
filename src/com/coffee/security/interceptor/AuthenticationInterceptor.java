package com.coffee.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        //会打印请求
        System.out.println("-------------------------------------------");
        System.out.println(new Date() + ",request : [" + req.getRequestURL() + "]     params  [" + req.getParameterMap() + "]");
        Enumeration enu = req.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + req.getParameter(paraName));
        }
        System.out.println("-------------------------------------------\n\n");
        //用户未登录，只能进入登陆页面，只能发登陆请求
//        if (Authentication.ROLE == null) {
//
////            if (url.indexOf("/index.html") > 0 || url.equals("/blackboard/") || url.indexOf("/login") > 0) {
////                //登录请求
////                System.out.println("login");
////                return true;
////            } else {
////                resp.sendRedirect("/blackboard/");
////                return false;
////            }
//        } else {
//            //说明用户登陆了
//            //验证token
//        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
