package com.example.springbootdemo.thread;

/**
 * Create by Bakatparin
 * on 2019/2/28
 */
public class TestThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        t1.setPriority(10);
        t1.start();

        for (int a=1;a<=100;a++) {
            System.out.println("main:"+a);
            if(a%10==0){
                Thread.yield();
               /* try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
