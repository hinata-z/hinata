package com.hinata.case1.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hinataz\
 *  提交给框架执行的工作任务
 *
 *
 */
@Data
public class JobInfo<R> {

    private String jobName;
    private int jobLength;
    private ITaskProcesser<?,?> taskProcesser;
    private TaskResult taskResult;

    private AtomicInteger successCount; //成功数量
    private AtomicInteger taskProcessCount;// 已处理数量

    /*存放每个任务的处理结果，供查询用*/
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueues;
    private final long expireTime;/*保留的工作的结果信息供查询的时长*/

    public JobInfo(String jobName, int jobLength, ITaskProcesser<?, ?> taskProcessor,
               long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.taskProcesser = taskProcessor;
        this.successCount = new AtomicInteger(0);;
        this.taskProcessCount = new AtomicInteger(0);
        this.taskDetailQueues = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
        this.expireTime = expireTime;
    }




    /*提供工作的整体进度信息*/
    public String getTotalProcess() {
        return "Success["+successCount.get()+"]/Current["+taskProcessCount.get()
                +"] Total["+jobLength+"]";
    }

    // 提供工作中每个任务的处理结果
    public List<TaskResult<R>> getTaskResults(){
        List<TaskResult<R>> taskResultList=new ArrayList<>() ;
        TaskResult<R> taskResult;
        if((taskResult=taskDetailQueues.pollFirst())!=null){
            taskResultList.add(taskResult);
        };
        return taskResultList;
    }
    /*
       1:任务处理完成后， 修改task中数量统计
     */
    public void addTaskResult(TaskResult<R> taskResult){
        if("0".equals(taskResult.getCode())){
            successCount.incrementAndGet();
        }
        taskProcessCount.incrementAndGet();
        taskDetailQueues.addLast(taskResult);
        //why? 任务处理数量 todo
//        if(taskProcessCount.get()==jobLength){
//            checkJob.putJob(jobName,expireTime);
//        }
    }
}
