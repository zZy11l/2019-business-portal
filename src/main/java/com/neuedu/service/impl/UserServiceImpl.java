package com.neuedu.service.impl;


import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.UserInfoMapper;


import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.utils.GuavaCacheUtil;
import com.neuedu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ServerResponse login(UserInfo userInfo) {
        //step1: 参数非空判断



        if(userInfo.getUsername()==null||userInfo.getUsername().equals("") ){//用户名为空

            return   ServerResponse.createServerResponseByFail(100,"用户名不能为空");

        }
        if(userInfo.getPassword()==null||userInfo.getPassword().equals("") ){//密码为空

            return   ServerResponse.createServerResponseByFail(100,"密码不能为空");


        }

        //setp2:判断用户名是否存在


        int result=userInfoMapper.existUsername(userInfo.getUsername());

        if(result==0){//用户名不存在

            return   ServerResponse.createServerResponseByFail(101,"用户名不存在");

        }

        //step3:根据用户名和密码查询


        //密码通过MD5算法加密
        String newPsd=MD5Utils.getMD5Code(userInfo.getPassword());
        userInfo.setPassword(newPsd) ;



        UserInfo userInfo_result=userInfoMapper.findUserByUsernameAndPassword(userInfo);


        if(userInfo_result==null){//密码错误
            return   ServerResponse.createServerResponseByFail(1,"密码错误");

        }




        return  ServerResponse.createServerResponseBySuccess(userInfo_result);

    }

    @Override
    public ServerResponse register(UserInfo userInfo) {

        if(userInfo.getUsername()==null||userInfo.getUsername().equals("")||
        userInfo.getPassword()==null||userInfo.getPassword().equals("")||
        userInfo.getEmail()==null||userInfo.getEmail().equals("")||
        userInfo.getQuestion()==null||userInfo.getQuestion().equals("")||
        userInfo.getAnswer()==null||userInfo.getAnswer().equals("")||
        userInfo.getPhone()==null||userInfo.getPhone().equals("")||
        userInfo.getIp()==null||userInfo.getIp().equals("")){
            return ServerResponse.createServerResponseByFail(100,"注册信息不能为空");        }


        if(userInfoMapper.existUsername(userInfo.getUsername())==1){
           return ServerResponse.createServerResponseByFail(1,"用户名已存在");
        }

        if(userInfoMapper.existEmail(userInfo.getEmail())==1){
            return ServerResponse.createServerResponseByFail(2,"邮箱已注册");

        }
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));

        userInfoMapper.insert(userInfo);

        return ServerResponse.createServerResponseBySuccess("用户注册成功");


    }

    @Override
    public ServerResponse update(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKey(userInfo);

        return ServerResponse.createServerResponseBySuccess("更新个人信息成功");
    }


    @Override
    public ServerResponse checkValid(String str, String type) {
        if("username".equals(type)){
            if(userInfoMapper.existUsername(str)==1){
                return ServerResponse.createServerResponseByFail(1,"用户名已存在");
            }
        }
        if("email".equals(type)){
            if(userInfoMapper.existEmail(str)==1){
                return ServerResponse.createServerResponseByFail(2,"邮箱已存在");
            }
        }
        return ServerResponse.createServerResponseBySuccess("校验成功");
    }

    @Override
    public ServerResponse getQuestion(String username) {

        if(username==null||"".equals(username)){
            return ServerResponse.createServerResponseByFail(100,"用户名不能为空");
        }
        if(userInfoMapper.existUsername(username)==0){
            return ServerResponse.createServerResponseByFail(101,"用户名不存在");
        }
        String question=userInfoMapper.selectQuestionByUsername(username);
        if(question==null||"".equals(question)){
            return ServerResponse.createServerResponseByFail(1,"该用户未设置密保问题");

        }

        return ServerResponse.createServerResponseBySuccess(null,question);

    }

    @Override
    public ServerResponse checkAnswer(String username, String question, String answer) {

        if(username==null||"".equals(username)){
            return ServerResponse.createServerResponseByFail(100,"用户名不能为空");
        }
        if(question==null||"".equals(question)){
            return ServerResponse.createServerResponseByFail(100,"密保问题不能为空");
        }
        if(answer==null||"".equals(answer)){
            return ServerResponse.createServerResponseByFail(100,"答案不能为空");
        }

        if(userInfoMapper.checkAnswerByUsername(username,question,answer)==0){
            return  ServerResponse.createServerResponseByFail(1,"问题答案错误");
        }

        String forgetToken= UUID.randomUUID().toString();
        GuavaCacheUtil.put(username,forgetToken);

        return ServerResponse.createServerResponseBySuccess(null,forgetToken);




    }

    @Override
    public ServerResponse forgetPassword(String username, String passwordNew,String forgetToken) {

        if(username==null||"".equals(username)){
            return ServerResponse.createServerResponseByFail(100,"用户名不能为空");
        }
        if(passwordNew==null||"".equals(passwordNew)){
            return ServerResponse.createServerResponseByFail(100,"密码不能为空");
        }

        String token=GuavaCacheUtil.get(username);
        if(token==null){
            return ServerResponse.createServerResponseByFail(103,"token已经失效");
        }

        if(!forgetToken.equals(token)){
            return ServerResponse.createServerResponseByFail(104,"非法的token");
        }


        passwordNew=MD5Utils.getMD5Code(passwordNew);

        if(userInfoMapper.updatePasswordByUsername(username,passwordNew)==0){
            return ServerResponse.createServerResponseByFail(1,"修改密码操作失败");
        }

        return ServerResponse.createServerResponseBySuccess("修改密码成功");


    }

    @Override
    public ServerResponse resetPassword(String username, String oldPassword, String newPassword) {



        if(oldPassword==null||"".equals(oldPassword)||
                newPassword==null||"".equals(newPassword)){
            return ServerResponse.createServerResponseByFail(100,"密码不能为空");
        }

        oldPassword=MD5Utils.getMD5Code(oldPassword);
        newPassword=MD5Utils.getMD5Code(newPassword);

       if(userInfoMapper.checkPasswordByUsername(username,oldPassword)==0){
           return ServerResponse.createServerResponseByFail(1,"旧密码输入不正确");
       }

       userInfoMapper.updatePasswordByUsername(username,newPassword);
       return ServerResponse.createServerResponseBySuccess("修改密码成功");

    }


}
