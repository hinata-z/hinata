package com.data.struct.stack;

import java.util.Stack;
/**
 * 后入先出
 *
 */
public class MyStack {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());
        System.out.println();
    }
}
