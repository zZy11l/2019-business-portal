package com.neuedu.config;

import com.neuedu.interceptor.AuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootConfig implements WebMvcConfigurer {
    @Autowired
    private  AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/order/**")
                .addPathPatterns("/cart/**")
                .addPathPatterns("/shipping/**")
                .addPathPatterns("/user/get_user_info.do")
                .addPathPatterns("/user/reset_password.do")
                .addPathPatterns("/user/update_information.do")
                .addPathPatterns("/user/get_information.do");


    }
}
