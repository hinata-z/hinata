package com.hinata.container;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArryLinkBlockQueue {
    static  ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<>(10);



    private Object[] items;
    final ReentrantLock lock;

    private final Condition notEmpty;

    /** Condition for waiting puts */
    private final Condition notFull;
    private Integer index;
    private Integer count;
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;


    public MyArryLinkBlockQueue(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock(true);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
    public void put(Object object) throws InterruptedException {
        items[index]=object;
        index++;

        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            //如果队列满了，则阻塞
            while (count == items.length)
                notFull.await();
            enqueue(object);
        } finally {
            lock.unlock();
        }

    }

    private void enqueue(Object x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[index] = x;
        if (++index == items.length)
            index = 0;
        count++;
        notEmpty.signal();
    }

    public Object take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    private Object dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        Object x =  items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
//        if (itrs != null)
//            itrs.elementDequeued();
        notFull.signal();
        return x;
    }

    static class Test extends Thread{

         @SneakyThrows
         @Override
         public void run() {
             for(;;){
                 System.out.println( queue.take());;
             }

         }

     }

    static class Test1 extends Thread{
         private String name;
        public Test1(String name){
            this.name=name;
        }
        @SneakyThrows
        @Override
        public void run() {
           for(;;){
               queue.put(name);
               Thread.sleep(300);
           }
        }

    }




    @SneakyThrows
    public static void main(String[] args) {
        new Test1("11").start();
        new Test().start();
    }
}
