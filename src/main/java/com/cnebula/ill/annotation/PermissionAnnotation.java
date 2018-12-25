package com.cnebula.ill.annotation;

import com.cnebula.ill.pojo.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PermissionAnnotation {

    public Permission[] belong() default {Permission.ALL};
}
