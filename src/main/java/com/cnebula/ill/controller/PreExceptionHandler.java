package com.cnebula.ill.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//@RestController
public class PreExceptionHandler implements ErrorController {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    /*public PreExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }*/

    /*public PreExceptionHandler(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }*/

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("/error")
    public Object error(HttpServletResponse response, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();
        String method = request.getMethod();
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String authType = request.getAuthType();
        HashMap<String, Object> requestParamMap = new HashMap<>();
        requestParamMap.put("pathInfo", pathInfo);
        requestParamMap.put("contextPath", contextPath);
        requestParamMap.put("queryString", queryString);
        requestParamMap.put("method", method);
        requestParamMap.put("requestURL", requestURL);
        requestParamMap.put("requestURI", requestURI);
        requestParamMap.put("servletPath", servletPath);
        requestParamMap.put("authType", authType);
        String responseMessage = null;
        //while (request.getAttributeNames().hasMoreElements()) {
            System.out.println("&*****************&");
            requestParamMap.put(request.getAttributeNames().nextElement(), request.getAttribute(request.getAttributeNames().nextElement()));
        //}
        try {
            responseMessage = MAPPER.writeValueAsString(requestParamMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.reset();
        response.setHeader("Accept", "application/json;charset=utf-8");
        return responseMessage;
    }
}
