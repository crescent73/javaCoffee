package com.coffee.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o){

        if (!req.getRequestURI().equals("/blackboard/response")) {
            //打印请求
            System.out.println("\n-------------------------------------------");
            System.out.println(new Date() + ",request : [" + req.getRequestURL() + "]     params  [" + req.getParameterMap() + "]");
            Enumeration enu = req.getParameterNames();
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                System.out.println(paraName + ": " + req.getParameter(paraName));
            }
            System.out.println("-------------------------------------------\n");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {
//    	System.out.println("Interceptor,postHandle");
//    	System.out.println(resp.getWriter());

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) throws Exception {
    }
}
