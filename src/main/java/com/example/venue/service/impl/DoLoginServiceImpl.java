package com.example.venue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.User;
import com.example.venue.service.DoLoginService;
import com.example.venue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service("DoLoginService")
public class DoLoginServiceImpl implements DoLoginService {

    final String SYSTEM_USER_SESSION = "BEASE_VENUE";

    @Autowired
    UserService userService;

    @Override
    public JSONObject Login(String uPhone, String uMsg, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        JSONObject res = new JSONObject();
        res.put("code", 0);
        if(StringUtils.isEmpty(uPhone) || StringUtils.isEmpty(uMsg)){
            res.put("msg", "请输入手机号或验证码");
            return res;
        }

        if(uPhone.equals("") && uMsg.equals("")){
            res.put("code", 2);
            res.put("msg", "欢迎管理员登录！");
        }else {
            User user = userService.getUser(uPhone);
            if(null == user){
                res.put("msg", "手机号错误，请检查后重试");
                return res;
            }

            String uCode = user.getuCode();
            if(!uMsg.equals(uCode)){
                res.put("msg", "手机号或验证码错误，请检查后重试");
                return res;
            }

            res.put("code", 1);
            res.put("msg", "登录成功");
        }
//        session.setAttribute(SYSTEM_USER_SESSION, user);
        Cookie cookie_uPhone = new Cookie("cookie_uPhone", uPhone);
        cookie_uPhone.setMaxAge(60 * 60 * 24 * 7);
//        cookie_uPhone.setMaxAge(60 * 60 * 24 * 7);
//        cookie_uPhone.setPath(request.getContextPath());

        response.addCookie(cookie_uPhone);
        return res;
    }

    @Override
    public String Logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute(SYSTEM_USER_SESSION);
        Cookie cook_uPhone = new Cookie("cookie_uPhone", "");
        cook_uPhone.setMaxAge(0);
        cook_uPhone.setPath(request.getContextPath());
        response.addCookie(cook_uPhone);
        return "login";
    }
}
