package com.rxy.aop.aoptest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Author: rxy
 * Create: 2020/5/6 1:31 PM
 * Email:rxywxsy@gmail.com
 * Changes (from 2020/5/6)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeCount {
}
