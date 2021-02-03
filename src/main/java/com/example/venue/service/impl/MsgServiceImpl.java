package com.example.venue.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.venue.domain.User;
import com.example.venue.service.MsgService;
import com.example.venue.service.UserService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.PullSmsSendStatusByPhoneNumberRequest;
import com.tencentcloudapi.sms.v20190711.models.PullSmsSendStatusByPhoneNumberResponse;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("MsgService")
public class MsgServiceImpl implements MsgService {

    public static String secretId = "";
    public static String secretKey = "";
    public static String appid = "";

    @Autowired
    private UserService userService;

    public JSONObject SendSms (String uPhone){

        String[] templateParams = {random()}; // 模板参数
        try {
            Credential cred = new Credential(secretId, secretKey); //实例化认证对象
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            SmsClient client = new SmsClient(cred, "ap-shanghai", clientProfile);
            SendSmsRequest req = new SendSmsRequest();

            req.setSmsSdkAppid(appid);

            String sign = "零卡文化";
            String session = "";
            String templateId = "793579"; // 模板ID
            String[] phoneNumbers  = {"+86"+uPhone};

            req.setSign(sign);
            req.setSenderId(""); // 国内填空
            req.setSessionContext(session);
            req.setTemplateID(templateId);
            req.setPhoneNumberSet(phoneNumbers);

            req.setTemplateParamSet(templateParams);

            SendSmsResponse res = client.SendSms(req); // 发送短信验证码

//            System.out.println(phoneNumbers[0]);
//            System.out.println(SendSmsResponse.toJsonString(res));
//            System.out.println(res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        JSONObject result = new JSONObject();
//        try {
//            TimeUnit.SECONDS.sleep(5);//秒
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        boolean sendStatus = PullSmsSendStatus(uPhone);
//        if(sendStatus){
//            result.put("code", 1);
//            result.put("msg", "发送验证码成功");
//            User user = userService.getUser(uPhone);
//            if(user == null){
//                user = new User();
//                user.setuPhone(uPhone);
//                user.setuCode(templateParams[0]);
//                userService.insertUser(user);
//            }else{
//                user.setuCode(templateParams[0]);
//                userService.updateUser(user);
//            }
//        }else {
//            result.put("code", 0);
//            result.put("msg", "发送验证码失败");
//        }
        User user = userService.getUser(uPhone);
        if(user == null){
            user = new User();
            user.setuPhone(uPhone);
            user.setuCode(templateParams[0]);
            userService.insertUser(user);
        }else{
            user.setuCode(templateParams[0]);
            userService.updateUser(user);
        }
        result.put("code", 1);
        result.put("msg", "发送验证码成功");

        return result;
    }

    public boolean PullSmsSendStatus(String uPhone){

        boolean result = false;

        try {
            Credential cred = new Credential(secretId, secretKey);
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            SmsClient client = new SmsClient(cred, "ap-shanghai", clientProfile);
            PullSmsSendStatusByPhoneNumberRequest req = new PullSmsSendStatusByPhoneNumberRequest();
            req.setSmsSdkAppid(appid);
            req.setPhoneNumber("+86"+uPhone);
            req.setSendDateTime(System.currentTimeMillis()/1000L - 60*60);
            req.setOffset(0L);

            Long limit = 10L;
            req.setLimit(limit);
            PullSmsSendStatusByPhoneNumberResponse res = client.PullSmsSendStatusByPhoneNumber(req);
            String MsgStatusJsonString =  PullSmsSendStatusByPhoneNumberResponse.toJsonString(res);
            JSONObject statusObjecct = JSONObject.parseObject(MsgStatusJsonString);
            JSONArray statusArray = statusObjecct.getJSONArray("PullSmsSendStatusSet");
            if(statusArray.size() == 0){
                result = false;
            }else {
                for(int i=0; i < statusArray.size(); i ++){
                    if(JSONObject.parseObject(statusArray.get(i).toString()).get("ReportStatus").equals("SUCCESS")){
                        result = true;
                        break;
                    }else {
                        result = false;
                        break;
                    }
                }
            }

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String random() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int[] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] = r.nextInt(9) + 1;
            sb.append(c[i]);
        }
        return sb.toString();

    }

}
