package com.yhc.srb.sms.utils;

import org.springframework.beans.factory.InitializingBean;

public class SmsProperties implements InitializingBean {

    private String Region;
    private String secretID;
    private String secretKey;
    private String smsSdkAppID ;
    private String signName ;
    private String templateID ;


    public static String REGION;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String SMS_SDK_APP_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_ID;
    @Override
    public void afterPropertiesSet() throws Exception {
        REGION=Region;
        SECRET_ID=secretID;
        SECRET_KEY=secretKey;
        SMS_SDK_APP_ID=smsSdkAppID;
        SIGN_NAME=signName;
        TEMPLATE_ID=templateID;
    }
}
