package com.jni.fixdemo.service;

import com.jni.fixdemo.Replace;

/**
 *模拟修复后的类
 * 正常情况下应该是从后端获取修复后的Calculator.java编译之后的.dex文件,写在这里省略了手动编译打包的过程
 * Calculator.java通过JavaC命令编译成Calculator.class, 然后可以通过dex命令打包成.dex文件
 */
public class Calculator {
    @Replace(clazz = "com.jni.fixdemo.Calculator" ,method = "calculator")
    public int calculator(){
        return 1024;
    }
}
