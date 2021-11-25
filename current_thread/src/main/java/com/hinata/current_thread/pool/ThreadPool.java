package com.hinata.current_thread.pool;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线程池
 */
public class ThreadPool {
    /**
     * 存放任务
     */
    private final BlockingQueue<Runnable> taskQueue;
    private WorkThread[] workThreads;
    private int coreSize;//线程数量
    private int taskCount; //任务数量

    public ThreadPool(int coreSize,int taskCount) {
        this.taskCount=taskCount;
        this.taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads =new WorkThread[coreSize];
        //初始化工作线程
        for(int i=0;i<coreSize;i++){
            workThreads[i]=new WorkThread();
            workThreads[i].start();
        }
    }

    public void execute(Runnable runnable) throws InterruptedException {
         taskQueue.put(runnable);
    }
    /* 工作线程 */
    private  class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable r=null;
            try {
                while (!isInterrupted()){
                    r=taskQueue.take();
                    if(r!=null){
                        System.out.println(getId()+" ready execute ");
                        r.run();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



        public void stopWork(){
            interrupt();// 中断线程，非强制类型
        }
    }

    @Override
    public String toString() {
        return "WorkThread number:"+taskCount
                +" wait task number:"+taskQueue.size();
    }
    public static void main(String[] args) throws InterruptedException {

        ThreadPool threadPool=new ThreadPool(3,5);
        threadPool.execute(new MyTask("11"));
        threadPool.execute(new MyTask("22"));
        threadPool.execute(new MyTask("33"));
        threadPool.execute(new MyTask("44"));
        threadPool.execute(new MyTask("55"));
        System.out.println(threadPool);
        Thread.sleep(30000);
    }
}
