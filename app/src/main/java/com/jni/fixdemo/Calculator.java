package com.jni.fixdemo;

/**
 *模拟异常
 */
public class Calculator {
    public int calculator(){
        //直接抛了一个RuntimeException
        throw new RuntimeException();
    }
}
