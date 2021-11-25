package com.hinata.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock  implements Lock, java.io.Serializable{
    private static ReentrantLock reentrantLock=new ReentrantLock();
    private static Sync sync=new Sync();


    public void test() throws InterruptedException {
        final MyReentrantLock myReentrantLock=new MyReentrantLock();
         class TestThread  extends Thread{

             public void test(int number){
                 System.out.println(Thread.currentThread().getName()+" start");
                 myReentrantLock.lock();
                 System.out.println(Thread.currentThread().getName()+number+" get lock");
                 try {
                     Thread.sleep(100);
                    int y=number-1;
                    if(y==0) return;
                    else {
                        test(y);
                    }
                 } catch (InterruptedException interruptedException) {
                 }finally {
                     myReentrantLock.unlock();
                 }
                 System.out.println(Thread.currentThread().getName()+" release lock");
             }
            @Override
            public void run() {
                test(3);

            }
        }
        for(int i=0;i<5;i++){
           TestThread t=new TestThread();
            t.start();
        }
        for(int i=0;i<10;i++){
            Thread.sleep(2000);
        }


    }

    public static void main(String[] args) throws InterruptedException {
        MyReentrantLock my=new MyReentrantLock();
        my.test();
    }
    private static class Sync extends AbstractQueuedSynchronizer{



        /**
         *
         * @param reduceCount  扣减个数
         * @return  返回小于0，表示当前线程获得同步状态失败
         * 大于0，表示当前线程获得同步状态成功
         */
        public int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        /**
         *
         * @param returnCount 归还个数
         * @return
         */
        public boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }


        @Override
        protected boolean tryAcquire(int arg) {
            //尝试获取锁
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else if(getExclusiveOwnerThread()==Thread.currentThread()){ //获取锁是当前线程，state+1
                setState(getState()+1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getExclusiveOwnerThread()!=Thread.currentThread()){
                throw new IllegalMonitorStateException();
            }
            //判断当前线程锁状态
            if(getState()==0){
                throw new IllegalArgumentException();
            }
            setState(getState()-1);
            if(getState()==0){
                setExclusiveOwnerThread(null);
            }
            return true;
        }


        @Override
        protected boolean isHeldExclusively() {
            //判断当前线程是否有锁
            return getState()==1;
        }


    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
