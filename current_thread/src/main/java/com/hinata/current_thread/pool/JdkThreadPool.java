package com.hinata.current_thread.pool;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * jdk线程池

 */
public class JdkThreadPool {
    public static void main(String[] args) {
//        ExecutorService executorService=new ThreadPoolExecutor(2,4,3,
//                 SECONDS ,new ArrayBlockingQueue<Runnable>(10));
//
//        //jdk中定义线程池
//        ExecutorService executorService1= Executors.newFixedThreadPool(3);
//        //保证任务的顺序执行
//        Executors.newSingleThreadExecutor();
//        //来一个任务创建一个线程
//        Executors.newCachedThreadPool();
//        for(int i=0;i<6;i++){
//            executorService.execute(new MyTask("name"+i) );
//        }
        ScheduledExecutorService executorService2= Executors.newSingleThreadScheduledExecutor();
        executorService2.schedule(new Runnable() {
            @Override
            public void run() {
                // Schedul 捕获异常
                System.out.println("1111");
            }
        },1000, MILLISECONDS);

    }
}
