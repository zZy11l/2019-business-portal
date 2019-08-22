package com.neuedu.interceptor;


import com.neuedu.pojo.UserInfo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


@Component
public class AuthorityInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        HttpSession session=request.getSession();
        UserInfo userInfo=(UserInfo) session.getAttribute("login_user");
        if(userInfo==null){
            response.reset();

            response.setHeader("Content-Type","text/html;charset=UTF-8");
            PrintWriter printWriter=response.getWriter();

            String json="{\n" +
                    "    \"status\": 10,\n" +
                    "    \"msg\": \"用户未登录\"\n" +
                    "}";


            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
            return false;
        }

        return true;



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
