package com.data.struct.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于链表结构队列
 *  * 基于数组队列
 *  * 入队操作put
 *  * 出队操作pop
 *  * 查看队头元素peek
 *  * 查看队列的大小
 *  * 查看队列是否为空
 *  */
public class MyLinkedQueue<E> {
    /**
     * 头节点 item is null ,transient 避免序列化
     */
    transient Node<E> head;


    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<E> last;


    public MyLinkedQueue(){
        last = head = new Node<E>(null);
    }
    public boolean enqueue(E data){
        Node<E> node=new Node<>(data);
        last=last.next=node;
        return true;
    }
//    public E dequeue(){
//        Node<E> h = head;
//        Node<E> first=head.next;
//        // head节点原来指向的节点的next指向自己，等待下次gc回收
//        h.next=h; //help gc 这里不明白
//        head=first;
//        E x = first.item;
//        // 新head节点的item值设置为null
//        first.item = null;
//        return x;
//    }
    private E dequeue() {
        // 获取到head节点
        Node<E> h = head;
        // 获取到head节点指向的下一个节点，也就是节点A
        Node<E> first = h.next;
        // 获取到下下个节点，也就是节点B
        Node<E> next = first.next;
        // head的next指向下下个节点，也就是图中的B节点
        h.next = next;
        // 得到节点A的值
        E x = first.item;
        first.item = null; // help GC
        // 这里为什么不指向null
        first.next = first; // help GC
        return x;

//        E e = null;
//        Node<E> temp = head.next;
//
//        head.next = temp.next;
//        e = temp.item;
//        temp.item = null;
//        temp.next = null;
//
//
//        return e;

    }

    /**
     * 获取队列元素方法

     */
    public E peek(){
        return head.next.item;
    }

    /**
     * 删除队列元素
     */
    public boolean remove(E e){
        //循环队列
        for (Node<E> trail = head, p = trail.next;
             p != null;
             trail = p, p = p.next){
            if(p.item.equals(e)){
                unlink(p, trail);
                return true;
            }

        }
        return false;
    }
    /**
     * 移除元素p, p的前置节点

     */
    public void unlink(Node<E> p,Node<E> trail){
        p.item = null;
        trail.next = p.next;
        // p是尾节点，尾节点指向前一个元素
        if (last == p)
            last = trail;
       // help gc 方法p.next = null或者p.next = p
    }

    static class Node<E>{
        E item;
        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head.next
         * - null, meaning there is no successor (this is the last node)
         */
        Node<E> next;

        Node(E x) { item = x; }
    }
    public String toString() {
           Node<E> p = head.next;
            if (p == null)
                return "[]";

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (;;) {
                E e = p.item;
                sb.append(e == this ? "(this Collection)" : e);
                p = p.next;
                if (p == null)
                    return sb.append(']').toString();
                sb.append(',').append(' ');
            }

    }
    public static void main(String[] args) {
        MyLinkedQueue<String> queue=new MyLinkedQueue<>();
        queue.enqueue("1");
        queue.enqueue("3");
        queue.enqueue("55");
        System.out.printf(queue.dequeue());
        queue.enqueue("4");
        System.out.println(queue.dequeue());
        queue.remove("4");
        System.out.println(queue.toString());
    }
}
