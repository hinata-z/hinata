package com.hinata.current_thread.pool;

import java.util.LinkedList;

public class Pool {
    private LinkedList<String> listPool=new LinkedList<>();

    /*限制了池的大小=20*/
    public Pool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                listPool.addLast("1");
            }
        }
    }

    /**
     * 等待超时获取线程
     * time milliseconds
     */
    public String getPool(long milliseconds) throws InterruptedException {
        synchronized (listPool){
            //永不超时
            if(milliseconds<0){
                while (listPool.isEmpty()){
                    listPool.wait();
                }
                return listPool.removeFirst();
            }else{
                /*等待时长*/
                long futureTime=System.currentTimeMillis()+milliseconds;
                //需要等待时间
                long remaining = milliseconds;
                while (listPool.isEmpty() && remaining>0){
                    listPool.wait(milliseconds);
                    /* 唤醒一次，重新计算等待时间 */
                    remaining=futureTime-System.currentTimeMillis();
                }
                if(!listPool.isEmpty()){
                    return listPool.removeFirst();
                }
                return null;
            }
        }

    }

    /**
     * 释放线程
     */
    public void releasePool(){
        synchronized (listPool){
            listPool.addLast("1");
            //通知其他等待连接的线程
            listPool.notifyAll();
        }
    }
}
