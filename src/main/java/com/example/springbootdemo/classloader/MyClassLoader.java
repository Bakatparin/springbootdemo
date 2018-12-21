package com.example.springbootdemo.classloader;

import java.io.*;

/**
 * 自定义热加载的类
 */
public class MyClassLoader extends ClassLoader {
    //要加载的java类的classpath路径
    private String classpath;

    public MyClassLoader(String classpath){
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name){
        byte[] data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }


    /**
     * 加载class文件中的内容
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        try {
            name = name.replace(".","//");
            FileInputStream fis = new FileInputStream(new File(classpath+name+".class"));
            if(fis == null){

            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != -1){
                baos.write(b);
            }
            fis.close();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {/**/
            e.printStackTrace();
        }
        return null;
    }
}
