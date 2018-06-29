package com.vsu.configuration;

import com.vsu.interceptor.LoginRequiredInterceptor;
import com.vsu.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vsu on 2018/02/11.
 */
@Component
public class Configuration extends WebMvcConfigurerAdapter{

    @Autowired
    private PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){

        interceptorRegistry.addInterceptor(passportInterceptor);
        interceptorRegistry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
        super.addInterceptors(interceptorRegistry);
    }

}
