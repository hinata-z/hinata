package com.hinata.current_thread.pool;

import lombok.SneakyThrows;

public  class MyTask implements Runnable{
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        System.out.println("任务 " + name + " 完成");
    }
}