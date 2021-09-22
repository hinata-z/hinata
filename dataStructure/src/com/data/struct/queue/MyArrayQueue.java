package com.data.struct.queue;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于数组队列
 * 入队操作put
 * 出队操作pop
 * 查看队头元素peek
 * 查看队列的大小
 * 查看队列是否为空
 */
public class MyArrayQueue {
    private Object[] queue;
    private int size;
    private int maxSize;

    public MyArrayQueue(int maxSize){
        this.maxSize=maxSize;
        queue=new Object[maxSize];
    }

    public boolean put(Object data){
        queue[size]=data;
        size++;
        return true;
    }

    public boolean pop(Object data){
        Object temp=queue[0];
        for(int i=0;i<size-1;i++){
            queue[i]=queue[i+1];
        }
        queue[size]=null;
        size--;
        return true;
    }

    public boolean isEmpty(){
        if(queue==null || queue.length==0){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        return size == maxSize;

    }



}
