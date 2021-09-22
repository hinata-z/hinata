package com.hinata.current_thread;
/**
 * ThreadLocal
 *
 * 变量副本
 */
public class UserThreadLocal {
        private ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();

        public static void main(String[] args) {
                System.out.println(StaticClassTest.anInt);
        }
}
