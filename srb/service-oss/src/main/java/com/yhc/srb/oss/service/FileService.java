package com.yhc.srb.oss.service;

import java.io.InputStream;

public interface FileService {
    String upload(String moudle, InputStream input,String fileName);

    void removeFile(String url);
}
