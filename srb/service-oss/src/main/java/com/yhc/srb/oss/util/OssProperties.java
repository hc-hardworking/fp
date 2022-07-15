package com.yhc.srb.oss.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "txy.oss")
public class OssProperties implements InitializingBean {

    private String secretId;
    private String secretKey;
    private String regionName;
    private String bucketName;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String REGION_NAME;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        REGION_NAME = regionName;
        BUCKET_NAME = bucketName;

    }
}
