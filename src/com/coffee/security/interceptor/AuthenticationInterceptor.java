package com.coffee.security.interceptor;

import com.coffee.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        HttpSession session = req.getSession();
        String url= req.getRequestURI();
        //拦截页面信息
        if (url.indexOf(".jsp") > 0 ||url.indexOf(".html") > 0 || url.equals("/blackboard/")) {
            //如果用户未登录
            if(session.getAttribute("login")==null){
                //只允许进入登陆页面
                if (url.indexOf("/index.html") > 0 || url.equals("/blackboard/")) {
                    return true;
                } else {//其它请求直接重定向到黑板
                    resp.sendRedirect("/blackboard/");
                    return false;
                }
            } else if(session.getAttribute("login").equals(1)){
                //进行用户权限验证
                User user = (User) session.getAttribute("user");
//                System.out.println("user:"+user);
                switch (user.getUserType()){
                    case "1"://管理员
                        if(url.indexOf("/index.html") > 0 || url.equals("/blackboard/")) {//如果再次进入登陆页面
//                            System.out.println("redirect");
                            resp.sendRedirect("/blackboard/page/admin/admin.html");//直接进入主页
                            return false;
                        }
                        if(url.indexOf("/page/admin")>0){ //只能访问管理员的界面
                            return true;
                        } else {
                            resp.sendRedirect("/blackboard/");//重新定向到主页
                            return false;
                        }
                    case "2"://教师
                    case "3"://学生
                        if(url.indexOf("/index.html") > 0 || url.equals("/blackboard/")) {//如果再次进入登陆页面
//                            System.out.println("redirect");
                            resp.sendRedirect("/blackboard/page/course/course.html");//直接进入主页
                            return false;
                        }
                        if(url.indexOf("/page/course")>0){ //只能访问教师的界面
                            return true;
                        } else {
                            resp.sendRedirect("/blackboard/");//重新定向到主页
                            return false;
                        }
                    default:
                        return false;
                }
            } else {
                //未知错误 返回登陆页面
                resp.sendRedirect("/blackboard/");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) {

    }
}
