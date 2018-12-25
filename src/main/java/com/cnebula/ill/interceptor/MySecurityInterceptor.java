package com.cnebula.ill.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class MySecurityInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MySecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MySecurityInterceptor........................");
        /*if (request.getSession() == null) {
            response.sendRedirect("http://www.baidu.com/");
            return false;
        } else {
            return false;
        }*/
        return true;
    }

}
