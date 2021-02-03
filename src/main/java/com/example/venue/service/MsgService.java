package com.example.venue.service;

import com.alibaba.fastjson.JSONObject;

public interface MsgService {

    public JSONObject SendSms (String uPhone);

    public boolean PullSmsSendStatus(String uPhone);


}
