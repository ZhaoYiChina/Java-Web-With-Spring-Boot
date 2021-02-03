package com.example.venue.component;

import com.example.venue.domain.User;
import com.example.venue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    final String SYSTEM_USER_SESSION = "BEASE_VENUE";

    @Autowired
    private UserService userService;

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

        if(StringUtils.isEmpty(cookie_username)){
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }

        HttpSession session = request.getSession();
        Object object = session.getAttribute(SYSTEM_USER_SESSION);
        if (userService == null) {//解决service为null无法注入问题
//            System.out.println("userService is null!!!");
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            userService = (UserService) factory.getBean("UserService");
        }

        if(null == object){
            User user = userService.getUser(cookie_username);
            if(null == user){
//                logger.info("user is null");
            }else {
                session.setAttribute(SYSTEM_USER_SESSION, user);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
