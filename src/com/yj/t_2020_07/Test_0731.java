package com.yj.t_2020_07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_0731 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        // 打印正在执行的缓存线程信息
                        System.out.println(Thread.currentThread().getName()
                                + "正在被执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
