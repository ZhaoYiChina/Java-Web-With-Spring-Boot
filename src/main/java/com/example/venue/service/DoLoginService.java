package com.example.venue.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface DoLoginService {

    public JSONObject Login(String uPhone, String uMsg, HttpSession session,
                            HttpServletRequest request, HttpServletResponse response);

    public String Logout(HttpSession session, HttpServletRequest request, HttpServletResponse response);
}
