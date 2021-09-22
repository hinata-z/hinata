package com.data.struct.stack;

import java.util.Stack;

/**
 * 入栈的值=当前值-min,
 *
 */
public class MinStack1 {
    int min;
    Stack<Integer> stack=new Stack<>();

    public void push(int val){
        if(stack.isEmpty()){
            stack.push(0);
            min=val;
        }
        stack.push(val-min);
        if(val<min){
            min=val;
        }
    }

    public void pop(){
        int pop=stack.pop();
        if(pop<0){
            min-=pop;
        }
    }

    public int peek(){
        int top=stack.peek();
        if(top>0){
            return min+top;
        }else{
            return min;
        }
    }

    public static void main(String[] args) {
        MinStack1 minStack1=new MinStack1();
        minStack1.push(2);
        minStack1.push(5);
        minStack1.push(12);
        minStack1.push(1);
        System.out.println(minStack1.peek());
        minStack1.pop();
        System.out.println(minStack1.min);
    }
}
