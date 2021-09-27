package com.hinata.currentThreadTool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
/**
 * 分而治之思想
 *  1: new ForkJoinPool 执行池
 *  2：定义任务：
 *      定义子task extend RecursiveTask
 *             子task如果还可以在拆分，也可以在主线程做
 *                  invokeAll(leftTask,rightTask);
 *                 return leftTask.join()+rightTask.join();
 *      定义actiion   RecursiveAction 没有返回
 *  3: 主线程中国 new ForkJoinPool();
 *       将子task加入   ForkJoinPool.invokeAll(leftTask,rightTask);
 *4:定义子task.join() 开始执行，阻塞方法
 *
 */
public class ForkJoin {

    static class  UserArray{

        static int length=40000;
        static int[] arr= new int[length];

        public static int[] markArray(){
            for(int i=0;i<length;i++){
                arr[i]=1;
            }
            return arr;
        }


    }

    /**
     * 有返回的 join

     */
    static class SumTask extends RecursiveTask<Integer> {
        private int start;
        private int end;
        private int[] arr;

        SumTask(int start,int end,int[] arr){
            this.arr=arr;
            this.end=end;
            this.start=start;
        }

        @Override
        protected Integer compute() {
            int count = 0;
            if(end-start<1000){
                for(int i=start;i<=end;i++){
                    count=count+arr[i];
                }
                return count;
                //接着拆分
            }else{
                SumTask leftTask=new SumTask(start,(start+end)/2,arr);
                SumTask rightTask=new SumTask((start+end)/2+1,end,arr);
                System.out.println("start="+start+"---end="+end);
                invokeAll(leftTask,rightTask);
                return leftTask.join()+rightTask.join();
            }
        }
    }

    public static void main(String[] args) {
        //1299936
        int count=0;
        for(int i=0;i<UserArray.length;i++){
            count+=UserArray.arr[i];
        }
        System.out.println("result="+count);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        SumTask sumTask=new SumTask(0,UserArray.length-1,UserArray.markArray());
        forkJoinPool.invoke(sumTask);
        /**
         * 异步
         */
        forkJoinPool.execute(sumTask);
        System.out.println(sumTask.join());
    }

}
