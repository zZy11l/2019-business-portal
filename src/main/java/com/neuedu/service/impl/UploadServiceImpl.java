package com.neuedu.service.impl;

import com.google.gson.Gson;
import com.neuedu.commen.ServerResponse;
import com.neuedu.service.IUploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UploadServiceImpl implements IUploadService {


    @Value("${qiniu.bucket}")
    private String bucketName;
    @Autowired
    UploadManager uploadManager;
    @Autowired
    Auth auth;


    @Override
    public ServerResponse uploadFile(File uploadFile) {

        String uploadToken=auth.uploadToken(bucketName);
        try {
            Response response= uploadManager.put(uploadFile,null,uploadToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return ServerResponse.createServerResponseBySuccess(null,putRet.hash);
        } catch (QiniuException e) {
            e.printStackTrace();

        }


        return ServerResponse.createServerResponseByFail(1,"上传失败");


    }
}
