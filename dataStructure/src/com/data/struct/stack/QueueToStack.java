package com.data.struct.stack;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 用队列实现栈
 */
public class QueueToStack {
    private static Queue<Integer> queue1=new LinkedList<>();
    private static Queue<Integer> queue2=new LinkedList<>();
//    public static void push(int x){
//        queue1.add(x);
//    }
    /**
     * 将队列反转
     */
    public static void push(int x){
        queue1.add(x);
        while (!queue2.isEmpty()){
            queue1.add(queue2.poll());
        }
        Queue<Integer> temp=queue1;
        queue1=queue2;
        queue2=temp;
    }


    public static Integer pop(){
        Queue<Integer> temp = new LinkedList<>();
        Integer data= null;
        while (!queue1.isEmpty()){
            if(queue1.size()==1){
                data=queue1.poll();
            }else{
                temp.add(queue1.poll());
            }

        }
        queue1=temp;
        return data;

    }

    public static void main(String[] args) {
        QueueToStack.push(1);
        QueueToStack.push(2);
        QueueToStack.push(3);
        System.out.println(QueueToStack.pop());
        System.out.println(QueueToStack.pop());
    }
}
