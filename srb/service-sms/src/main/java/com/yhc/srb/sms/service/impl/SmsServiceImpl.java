package com.yhc.srb.sms.service.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.yhc.srb.sms.service.SmsService;
import com.yhc.srb.sms.utils.SmsProperties;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String mobile,String[] param) {
        try {
            Credential cred = new Credential(SmsProperties.SECRET_ID, SmsProperties.SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, SmsProperties.REGION, clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId(SmsProperties.SMS_SDK_APP_ID);
            req.setSignName(SmsProperties.SIGN_NAME);
            req.setTemplateId(SmsProperties.TEMPLATE_ID);
            String[] phoneNumberSet1 = {mobile};
            req.setPhoneNumberSet(phoneNumberSet1);
//            //随机生成6位验证码
//            String code= RandomUtil.getSixBitRandom();
//            String[] templateParamSet1 = {code};
            req.setTemplateParamSet(param);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

    }
}
