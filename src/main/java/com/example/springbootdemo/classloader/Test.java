package com.example.springbootdemo.classloader;

/**
 * 测试java类的热加载
 */
public class Test{
    public static void main(String[] args){
        new Thread(new MsgHandler()).start();
        /*System.out.println("扩展类加载器："+System.getProperty("java.ext.dirs"));
        System.out.println("系统类加载器："+ClassLoader.getSystemClassLoader());
        System.out.println("系统类加载器的父类："+ClassLoader.getSystemClassLoader().getParent());
        System.out.println("扩展类加载器的父类："+ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(System.getProperty("java.class.path"));*/
    }
}
