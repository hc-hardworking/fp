package com.yhc.srb.oss.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import com.yhc.srb.oss.service.FileService;
import com.yhc.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(String module, InputStream inputStream, String fileName) {
        // 生成一个客户端
        COSCredentials cred = new BasicCOSCredentials(OssProperties.SECRET_ID, OssProperties.SECRET_KEY);
        Region region = new Region(OssProperties.REGION_NAME);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 判断oss实例是否存在，如果不存在就创建，如果存在就获取
        if(!cosClient.doesBucketExist(OssProperties.BUCKET_NAME)){
            cosClient.createBucket(OssProperties.BUCKET_NAME);
            // 设置oss实例的访问权限
            cosClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }

        // 构建日期数据 ：avatar/2022/7/15/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");

        // 文件名：uuid.扩展名
        fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        // 文件根路劲
        String key = module+"/"+folder+"/"+fileName;
        cosClient.putObject(OssProperties.BUCKET_NAME,key,inputStream,new ObjectMetadata());

        cosClient.shutdown();
        return "https://"+OssProperties.BUCKET_NAME+"."+"cos."+OssProperties.REGION_NAME+".myqcloud.com"+"/"+key;
    }

    @Override
    public void removeFile(String url) {
        // 生成一个客户端
        COSCredentials cred = new BasicCOSCredentials(OssProperties.SECRET_ID, OssProperties.SECRET_KEY);
        Region region = new Region(OssProperties.REGION_NAME);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 文件名（服务器上的文件路径）
        String host = "https://"+OssProperties.BUCKET_NAME+"."+"cos."+OssProperties.REGION_NAME+".myqcloud.com"+"/";
        String objectName = url.substring(host.length());

        // 删除文件
        cosClient.deleteObject(OssProperties.BUCKET_NAME,objectName);
        cosClient.shutdown();

    }
}
