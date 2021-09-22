package com.hinata.current_thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class Test {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("myThread ");
        }
    }

    public static void main(String[] args) {

        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        Arrays.stream(threadMXBean.dumpAllThreads(false,false)).forEach(
                x -> System.out.println(x.getThreadName()));
        MyThread myThread= new MyThread();
        //设置优先级
        myThread.setPriority(10);
        myThread.setDaemon(true);
    }
}
