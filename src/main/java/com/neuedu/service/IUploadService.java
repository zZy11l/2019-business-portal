package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

import java.io.File;

public interface IUploadService {

    ServerResponse uploadFile(File uploadFile);

}
