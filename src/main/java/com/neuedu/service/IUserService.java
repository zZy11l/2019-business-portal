package com.neuedu.service;

import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.UserInfo;


public interface IUserService {

    ServerResponse login(UserInfo userInfo);

    ServerResponse register(UserInfo userInfo);

    ServerResponse update(UserInfo userInfo);


    ServerResponse checkValid(String str, String type);

    ServerResponse getQuestion(String username);

    ServerResponse checkAnswer(String username, String question, String answer);

    ServerResponse forgetPassword(String username, String passwordNew,String forgetToken);

    ServerResponse resetPassword(String username, String oldPassword, String newPassword);
}
