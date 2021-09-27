package com.hinata.currentThreadTool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 */
public class UserFutureTask {

   static class UserThread implements Callable {

       @Override
       public Object call() throws Exception {
           return 1;
       }
   }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserThread userThread=new UserThread();
        FutureTask futureTask=new FutureTask<>(userThread);
    }
}
