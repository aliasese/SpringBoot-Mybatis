package com.cnebula.ill.config;

import com.cnebula.ill.controller.BusinessController;
import com.cnebula.ill.interceptor.MySecurityInterceptor;
import com.cnebula.ill.interceptor.SecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MySecurityInterceptor()).addPathPatterns("/**").excludePathPatterns("/query/get/{id}");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
