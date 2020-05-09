package com.jni.fixdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解,标注需要修复的类名,方法名等信息
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Replace {
    String clazz(); //修复哪个类
    String method();    //修复哪个方法
}
