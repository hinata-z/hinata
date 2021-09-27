package com.hinata.currentThreadTool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 信号灯，
 * 控制线程统一执行,
 *   1：定义线程CountDownLatch扣减数
 *   2:线程内调用扣减方法countDownLatch.countDown();
 *   3：主线程阻塞countDownLatch.await(); 等待扣减书=0，所有子线程开始执行

 */
@Slf4j
public class UseCountDownLatch {
    static  CountDownLatch countDownLatch=new CountDownLatch(4);

    static class  UserThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                log.info(Thread.currentThread().getName()+"开始");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            countDownLatch.countDown();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        log.info("主线程开始");
        UserThread userThread=new UserThread();
        UserThread userThread1=new UserThread();
        userThread.start();
        userThread1.start();
        countDownLatch.await();
        log.info("主线程结束");
    }
}
