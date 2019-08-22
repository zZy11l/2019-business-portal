package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.UserInfo;

import com.neuedu.service.IUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login.do")
    public ServerResponse login(UserInfo userInfo,
                                HttpSession session){
         ServerResponse serverResponse=userService.login(userInfo);
        if(serverResponse.getData()!=null){
            UserInfo loginUser=(UserInfo) serverResponse.getData();
            session.setAttribute("login_user",loginUser);
        }

        return serverResponse;

    }

    @RequestMapping("register.do")
    public ServerResponse register(UserInfo userInfo){
        return userService.register(userInfo);
    }

    @RequestMapping("check_valid.do")
    public ServerResponse register(@RequestParam("str") String str,@RequestParam("type") String type){
        return userService.checkValid(str,type);
    }


    @RequestMapping("get_user_info.do")
    public ServerResponse getLoginUser(HttpSession session){



        UserInfo userLogin=(UserInfo)session.getAttribute("login_user");

        return  ServerResponse.createServerResponseBySuccess(userLogin);
    }
    @RequestMapping("forget_get_question.do")
    public ServerResponse getQuestion(@RequestParam("username") String username){

        return userService.getQuestion(username);
    }
    @RequestMapping("forget_check_answer.do")
    public ServerResponse checkAnswer(String username,
                                      String question,
                                      String answer){

        ServerResponse serverResponse=userService.checkAnswer(username,question,answer);


        return serverResponse;
    }

    @RequestMapping("forget_reset_password.do")
    public ServerResponse forgetPassword(@RequestParam("username") String username,
                                        @RequestParam("passwordNew") String passwordNew,
                                        @RequestParam("forgetToken") String forgetToken){


        return userService.forgetPassword(username,passwordNew,forgetToken);
    }

    @RequestMapping("reset_password.do")
    public ServerResponse resetPassword(String oldPassword,
                                        String newPassword,
                                        HttpSession session){


       UserInfo loginUser=(UserInfo) session.getAttribute("login_user");

       return userService.resetPassword(loginUser.getUsername(),oldPassword,newPassword);
    }

    @RequestMapping("update_information.do")
    public ServerResponse updateUserInfo(UserInfo userInfo,
                                         HttpSession session){



        UserInfo loginUser=(UserInfo) session.getAttribute("login_user");

        userInfo.setId(loginUser.getId());

        return userService.update(userInfo);


    }


    @RequestMapping("get_information.do")
    public ServerResponse getInformation(HttpSession session){


        UserInfo userLogin=(UserInfo)session.getAttribute("login_user");


        return  ServerResponse.createServerResponseBySuccess(userLogin);
    }

    @RequestMapping("logout.do")
    public ServerResponse logout(HttpSession session){

        session.invalidate();
        return ServerResponse.createServerResponseBySuccess("退出成功");
    }








}
