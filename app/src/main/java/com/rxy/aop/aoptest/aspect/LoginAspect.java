package com.rxy.aop.aoptest.aspect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxy.aop.aoptest.LoginActivity;
import com.rxy.aop.aoptest.annotation.CheckLogin;
import com.rxy.aop.aoptest.config.Constant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/*
 * Author: rxy
 * Create: 2020/5/6 10:58 AM
 * Email:rxywxsy@gmail.com
 * Changes (from 2020/5/6)
 */
@Aspect
public class LoginAspect {

    @Pointcut("execution(@com.rxy.aop.aoptest.annotation.CheckLogin * *(..))")
    public void checkLogin() {
    }

    @Pointcut("execution(@com.rxy.aop.aoptest.annotation.TimeCount * *(..))")
    public void timeCount() {
    }

    @Around("checkLogin()")
    public Object dealCheckLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        AppCompatActivity context = (AppCompatActivity) joinPoint.getThis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.d("Peter", "className is " + className + "---methodName is " + methodName);
        SharedPreferences sp = context.getSharedPreferences("default", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean(Constant.KEY_IS_LOGIN, false);
        if (isLogin) {
            return joinPoint.proceed();
        } else {
            int loginCode = methodSignature.getMethod().getAnnotation(CheckLogin.class).loginCode();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivityForResult(intent, loginCode);
            return null;
        }
    }

    @Around("timeCount()")
    public Object dealTimeCount(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        Log.d("Peter", "start time is " + start + " end time is " + end + "---execute time is " + (end - start));
        Log.e("Peter", String.format("这是%s类里面的%s方法的耗时，总耗时是%s", className, methodName, end - start));
        return result;
    }
}
