package com.example.springbootdemo.classloader;

/**
 * 封装加载类的信息
 */
public class LoadInfo {
    //自定义的类的加载器
    private MyClassLoader myClassLoader;
    //记录要加载的类的加载时间戳
    private long loadTime;
    private BaseManager manager;

    public MyClassLoader getMyClassLoader() {
        return myClassLoader;
    }

    public void setMyClassLoader(MyClassLoader myClassLoader) {
        this.myClassLoader = myClassLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }

    public LoadInfo(MyClassLoader myClassLoader, long loadTime){
        super();
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }
}
