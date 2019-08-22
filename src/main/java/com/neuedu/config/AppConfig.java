package com.neuedu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@PropertySource("application-dev.yml")
public class AppConfig {

    @Value("url")
    private String url;
    @Value("driver")
    private String driver;
    @Value("username")
    private String username;
    @Value("password")
    private String password;

}
