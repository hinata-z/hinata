package com.data.struct.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 循环队列,基于数组，头节点和尾节点
 */
public class MyCircularQueue<T> {
    private int capacity;
    private T[] queue;
    private int head;
    private int tail;

    public MyCircularQueue(int count){
        //count+1 为什么object+1
        this.capacity=count+1;
        queue = (T[]) new Object[capacity];
        head=0;
        tail=0;
    }
    public boolean add(T value) {
        //判断是否满了
        int newtail = (tail + 1) % capacity;
        if (newtail == head)
            throw new IndexOutOfBoundsException("Queue full");
        queue[tail] = value;
        tail = newtail;
        return true; // we did add something
    }
    public boolean isFull() {
        System.out.println(queue.length);
        System.out.println(capacity);
        return (tail + 1) % capacity == head;
    }

    public T remove() {
        T data = queue[head];
        queue[head] = null;
        head = (head + 1) % capacity;
        System.out.println(head);
        return data;
    }

    public T pollFirst() {
        int h = head;
        T result = (T) queue[h];
        // Element is null if deque empty
        if (result == null)
            return null;
        queue[h] = null;     // Must null out slot
        head = (h + 1) & (queue.length - 1);
        System.out.println(head);
        return result;
    }


    public static void main(String[] args) {
        MyCircularQueue<Integer> q = new MyCircularQueue<Integer>(3);
        q.add(1);
        q.add(2);
        q.add(3);
        q.pollFirst();
        q.remove();
        q.add(4);
        q.remove();
    }
}
