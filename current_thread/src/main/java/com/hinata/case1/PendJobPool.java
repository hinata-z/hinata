package com.hinata.case1;

import com.hinata.case1.vo.EnumResult;
import com.hinata.case1.vo.ITaskProcesser;
import com.hinata.case1.vo.JobInfo;
import com.hinata.case1.vo.TaskResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author hinataz
 * 定义线程池， 阻塞队列，线程池，任务容器，线程数量
 *    1：注册任务，放入过期
 *    2：

 */
public class PendJobPool {
    private PendJobPool(){

    }
   static  class PendJobHolder{
        private static PendJobPool pendJobPool=new PendJobPool();
   }
    public static PendJobPool getInstance(){
        return PendJobHolder.pendJobPool;
    }
    // 线程数量
    private static  final int THREAD_NUMBER=10;

    //存放任务的阻塞队列
    private static ArrayBlockingQueue<Runnable> taskQueue=new ArrayBlockingQueue<>(500);

    private static ExecutorService taskEx=new ThreadPoolExecutor(THREAD_NUMBER,THREAD_NUMBER,60, TimeUnit.SECONDS,taskQueue);

    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap=new ConcurrentHashMap<>();

    //将任务包装  提交给线程池使用，
    // 同时将任务结果写入缓存

    class  ExecTask<T,R> implements Runnable{

        private JobInfo<R> jobInfo;
        private T processData;

        public ExecTask(JobInfo<R> jobInfo, T processData) {
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run() {
            R r=null;
            ITaskProcesser<T,R> taskProcesser= (ITaskProcesser<T, R>) jobInfo.getTaskProcesser();
            TaskResult<R> result = null;
            JobInfo<R> jobInfo=null;
            try{
                result=taskProcesser.taskExecute(processData);
                if(result==null){
                    result = new TaskResult<R>(EnumResult.Exception,r
                            ,"result is null");
                }
            }catch (Exception e){
                e.printStackTrace();
                result = new TaskResult<R>(EnumResult.Exception,r
                        ,e.getMessage());
            }finally {
                jobInfo.addTaskResult(result);
            }

        }
    }

    // 提交工作中任务
    public  <T,R> void putTask(String jobName,T t){
        JobInfo<R> jobInfo=getJob(jobName);
        ExecTask<T,R>  execTask=new ExecTask(jobInfo,t);
        taskEx.execute(execTask);
    }


    //调用者注册工作，如工作名，任务的处理器等等
    public <R> void registerJob(String jobName, int jobLength,
                                ITaskProcesser<?, ?> taskProcesser, long expireTime) {
        JobInfo<R> jobInfo = new JobInfo<R>(jobName,jobLength,taskProcesser,expireTime);
        if(jobInfoMap.putIfAbsent(jobName, jobInfo)!=null) {
            throw new RuntimeException(jobName+"已经注册！");
        }
    }


    /*根据工作名称检索工作*/
    @SuppressWarnings("unchecked")
    private <R> JobInfo<R> getJob(String jobName){
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (null==jobInfo)
            throw new RuntimeException(jobName+"是非法任务！");
        return jobInfo;
    }

    /*获得工作的整体处理进度*/
    public <R> String getTaskProgress(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

    /*获得每个任务的处理详情*/
    public <R> List<TaskResult<R>> getTaskDetail(String jobName){
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskResults();
    }

    public static Map<String, JobInfo<?>> getMap(){
        return jobInfoMap;
    }


}
