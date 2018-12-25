package com.cnebula.ill.aop;

import com.cnebula.ill.annotation.PermissionAnnotation;
import com.cnebula.ill.controller.BusinessController;
import com.cnebula.ill.pojo.Permission;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Component
public class PermissionAspect {
    private static final Logger log = LoggerFactory.getLogger(PermissionAspect.class);

    @Pointcut(value = "execution(public * com.cnebula.ill.controller..*.*(..))")
    void permission() {}

    @Before(value = "execution(public * com.cnebula.ill.controller..*.*(..))")
    public void doBefore(JoinPoint point) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        PermissionAnnotation pAnnotation = point.getTarget().getClass().getAnnotation(PermissionAnnotation.class);
        if (pAnnotation != null) {
            Permission[] permissions = pAnnotation.belong();
            Boolean f = false;
            for (Permission p:permissions) {
                if (p == Permission.ALL) {
                    f = true;
                    break;
                }
            }
            if (f) {
                log.info("You have permission");
            } else {
                log.error("You don't have permission");
                response.setStatus(500);
                response.getOutputStream().write("You don't have permission".getBytes("UTF-8"));
            }
        }
    }

    @Around("execution(public * com.cnebula.ill.controller..*.*(..))")
    public Object executeAroundType(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String tenantId = request.getHeader("X-Okapi-Tenant");

        PermissionAnnotation pAnnotation = point.getTarget().getClass().getAnnotation(PermissionAnnotation.class);
        Object obj = null;

        if (pAnnotation != null) {
            Permission[] permissions = pAnnotation.belong();
            Boolean f = false;
            for (Permission p:permissions) {
                if (p == Permission.QUERY) {
                    f = true;
                    break;
                }
            }
            if (f) {
                obj = point.proceed();
                log.info("You have permission");
                return obj;
            } else {
                log.error("You don't have permission");
                response.setStatus(500);
                response.getOutputStream().write("You don't have permission".getBytes("UTF-8"));
                return null;
            }
        } else {
            return point.proceed();
        }

    }

    //@Around("execution(public * com.cnebula.ill.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object executeAround(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String tenantId = request.getHeader("X-Okapi-Tenant");
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method signatureMethod = methodSignature.getMethod();
        Method method = null;
        try {
            method = point.getTarget().getClass().getDeclaredMethod(point.getSignature().getName(),signatureMethod.getParameterTypes());
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        Object obj = null;
        if (this.checkAnnotation(method, tenantId) && tenantId != null) {
            obj = point.proceed();
            log.info("You have permission");

        } else {
            log.error("You don't have permission");
            response.setStatus(500);
            response.getOutputStream().write("You don't have permission".getBytes("UTF-8"));
        }
        return obj;
    }

    @AfterReturning(returning = "ret", pointcut = "permission()")
    public void doAfter(Object ret) throws Exception {
        Gson gson = new Gson();
        log.info(gson.toJson(ret));
    }

    private Boolean checkAnnotation(Method method, String tenantId) {
        if (method.isAnnotationPresent(PermissionAnnotation.class)) {
            PermissionAnnotation pAnnotation = method.getAnnotation(PermissionAnnotation.class);
            Permission[] permissions = pAnnotation.belong();
            for (Permission p:permissions) {
                if (p == Permission.ALL) return true;
            }
        }
        return false;
    }

}
