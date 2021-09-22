package com.data.struct.stack;

import java.util.Stack;
/**
 *入栈的值是最小的，
 * 新的值比原来的值小，则将新的min进行压栈
 */
public class MinStack {
    int min=Integer.MAX_VALUE;
    Stack<Integer> stack=new Stack<>();


    public  void push(int x){
        //min进行压栈
        if(min<x){
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }


    public  void pop(){
        if(stack.pop()==min){
            min=stack.pop();
        }
    }


}
