package com.hinata.lock;

import lombok.SneakyThrows;

public class TestThread  extends Thread{


    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" get lock");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
