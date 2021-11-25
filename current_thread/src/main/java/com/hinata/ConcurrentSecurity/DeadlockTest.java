package com.hinata.ConcurrentSecurity;

public class DeadlockTest   {
    private static Object lock1=new Object();
    private static Object lock2=new Object();


    private static void test1() throws InterruptedException {
        synchronized (lock1){
            Thread.sleep(100);
            System.out.println(" --- lock1");
            synchronized (lock2){
                System.out.println(" --- lock2");
            }
        }
    }

    private static void test2() throws InterruptedException {
        synchronized (lock2){
            Thread.sleep(100);
            System.out.println(" --- lock2");
            synchronized (lock1){
                System.out.println(" --- lock1");
            }
        }
    }

     static class TestSafe extends Thread{
         @Override
         public void run() {
             try {
                 test2();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
    public static void main(String[] args) throws InterruptedException {
        TestSafe testSafe=new TestSafe();
        testSafe.start();
        test1();
    }
}
