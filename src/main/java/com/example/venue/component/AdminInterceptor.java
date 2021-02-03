package com.example.venue.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Cookie[] cookies = request.getCookies();
        if(null == cookies){
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }

        String cookie_username = null;
        for(Cookie item : cookies){
            if("cookie_uPhone".equals(item.getName())){
                cookie_username = item.getValue();
                break;
            }
        }

        String servletPath = request.getServletPath();
        if(servletPath.equals("/admin") || servletPath.equals("/admin.html")
            || servletPath.equals("/orderVerifi") || servletPath.equals("/orderVerifi.html")
                || servletPath.equals("/getResultbyOCR")
                || servletPath.equals("/orderDateQuery") || servletPath.equals("/orderDateQuery.html")
                || servletPath.equals("/orderPeriodQuery") || servletPath.equals("/orderPeriodQuery.html")
        ){
            if(!cookie_username.equals("")){
                response.sendRedirect(request.getContextPath() + "/limit.html");
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }


        @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
