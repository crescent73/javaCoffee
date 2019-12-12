package com.coffee.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse httpServletResponse, Object o) throws Exception {
//    	System.out.println("Interceptor,perHandler");
//        System.out.println(req.getRequestURL().toString());
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
