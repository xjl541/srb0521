package com.atguigu.srb.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.common.utils.RandomUtils;
import com.atguigu.srb.sms.service.SmsService;
import com.atguigu.srb.sms.util.SmsProperties;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void send(String mobile) {
        // 1 默认配置,公共参数
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                SmsProperties.REGION_Id,
                SmsProperties.KEY_ID,
                SmsProperties.KEY_SECRET);


        // 2 api的客户端
        IAcsClient iAcsClient = new DefaultAcsClient(defaultProfile);
        // 3 请求参数封装
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysDomain("dysmsapi.aliyuncs.com");
        commonRequest.setSysVersion("2017-05-25");
        commonRequest.setSysAction("SendSms");
        commonRequest.putQueryParameter("RegionId", SmsProperties.REGION_Id);
        commonRequest.putQueryParameter("PhoneNumbers", mobile);
        commonRequest.putQueryParameter("SignName", SmsProperties.SIGN_NAME);
        commonRequest.putQueryParameter("TemplateCode", SmsProperties.TEMPLATE_CODE);

        String fourBitRandom = RandomUtils.getFourBitRandom();
        System.out.println(fourBitRandom);
        Map<String, String> map = new HashMap<>();
        map.put("code",fourBitRandom);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        commonRequest.putQueryParameter("TemplateParam",s);
        // 4 客户端发送请求
        CommonResponse commonResponse = iAcsClient.getCommonResponse(commonRequest);
        // 5 处理请求返回结果
        int httpStatus = commonResponse.getHttpStatus();
        HttpResponse httpResponse = commonResponse.getHttpResponse();

        System.out.println(httpStatus);
        System.out.println(httpResponse.isSuccess());
        // 根据短信发送的结果判断是否将验证码存入redis
        if(httpStatus==200){
            redisTemplate.opsForValue().set("srb:sms:code:" + mobile,fourBitRandom);
        }
    }
}
