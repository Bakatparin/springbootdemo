package com.example.springbootdemo.thread;

/**
 * Create by Bakatparin
 * on 2019/2/28
 */
public class MyThread implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int a=1;a<=100;a++) {
            System.out.println("t1:"+a);
        }
    }
}