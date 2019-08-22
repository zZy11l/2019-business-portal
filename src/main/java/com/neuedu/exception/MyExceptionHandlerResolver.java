package com.neuedu.exception;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class MyExceptionHandlerResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {


        System.out.println("============="+ex.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        if(ex instanceof MyException){
            MyException myException=(MyException) ex;
            String url= myException.getDirector();

            modelAndView.setViewName("common/error");
            Map<String,Object> model= modelAndView.getModel();
            model.put("msg",ex.getMessage());
            model.put("url",url);

        }
        if(ex instanceof Exception){

            modelAndView.setViewName("common/error");
            Map<String,Object> model= modelAndView.getModel();
            model.put("msg",ex.getMessage());


        }


        return modelAndView;





    }
}
