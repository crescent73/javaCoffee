package com.coffee.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        //打印请求
        System.out.println("-------------------------------------------");
        System.out.println(new Date() + ",request : [" + req.getRequestURL() + "]     params  [" +  req.getParameterMap() + "]");
        Enumeration enu= req.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+ req.getParameter(paraName));
        }

        String url = req.getRequestURI();
        System.out.println(url);
        if(url.indexOf("/login") > 0){
            //登录请求
            System.out.println("login");
        } else {
            //非登录请求
            //验证session
            HttpSession session = req.getSession();
            System.out.println("session:"+session);
            //验证token

            //如果验证不通过，会重定向到login界面
            System.out.println("do not login");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {
//    	System.out.println("Interceptor,postHandle");
//    	System.out.println(resp.getWriter());

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    	System.out.println("Interceptor,afterCompletion");
    }
}
