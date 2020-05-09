package com.jni.fixdemo;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * 加载.dex文件
 */
public class DexManager {
    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }

    public void load(File file){
        try {
            DexFile dexFile = DexFile.loadDex(file.getAbsolutePath(), new File(context.getCacheDir(), "opt").getAbsolutePath(), Context.MODE_PRIVATE);
            //拿到当前dex文件下所有类名集合
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()){
                String clazzName=entries.nextElement();
                //这里需要注意,dex文件是从sd加载的,所以不能直接使用Class.forName反射的方式加载类信息
                //通过DexFile.loadClass加载类信息
                Class clazz = dexFile.loadClass(clazzName, context.getClassLoader());
                if (clazz!=null) {
                    fixClazz(clazz);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fixClazz(Class clazz) {
        //目的是替换方法,遍历类中的所有方法
        Method[] methods = clazz.getMethods();
        for (Method fixMethod : methods) {
            //通过注解,确定要修改的方法
            Replace replace = fixMethod.getAnnotation(Replace.class);
            if (replace==null) {
                continue;
            }
            String clazzName = replace.clazz();
            String methodName = replace.method();

            try {
                //通过反射的方式拿到原来的类(有bug的类)的信息
                Class errorClass = Class.forName(clazzName);
                //拿到需要替换的方法,第二个参数表示参数列表一致,保证找到正确的方法
                Method errorMethod = errorClass.getDeclaredMethod(methodName, fixMethod.getParameterTypes());
                replace(errorMethod,fixMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public native static void replace(Method errorMethod,Method fixMethod);
}
