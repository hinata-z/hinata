package com.hinata.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockCase {
    private ReentrantLock lock=new ReentrantLock();

    public void lockTest(){
        lock.lock();
        try {
            // TODO:  bussise
            //一定要加finally,不然会思索
        }finally {
            lock.unlock();
        }

    }
}
