package com.cnebula.ill.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@Component
@Aspect
public class LogAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();
    @Pointcut(value = "execution(public * com.cnebula.ill.controller..*.*(..))")
    void log() {}

    //@Before(value = "log()")
    @Before(value = "execution(public * com.cnebula.ill.controller..*.*(..))")
    public void doLogBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String signature = joinPoint.getSignature().toString();
        LOGGER.info(signature);

        HashMap<String, String> logObject = new HashMap<>();

        String requestURL = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        String remoteHost = request.getRemoteHost();
        logObject.put("requestURL", requestURL);
        logObject.put("contextPath", contextPath);
        logObject.put("method", method);
        logObject.put("remoteHost", remoteHost);
        logObject.put("signature", signature);
        try {
            String logStr = MAPPER.writeValueAsString(logObject);
            LOGGER.info(logStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=================");
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfter(Object ret) throws Exception {
        LOGGER.info(MAPPER.writeValueAsString(ret));
    }

    @AfterThrowing(value="execution (public * com.cnebula.ill.controller..*.*(..))",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint,Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method name:"+methodName+ " ends and result="+e);
    }
}
