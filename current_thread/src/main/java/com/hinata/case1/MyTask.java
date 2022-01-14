package com.hinata.case1;

import com.hinata.case1.vo.EnumResult;
import com.hinata.case1.vo.ITaskProcesser;
import com.hinata.case1.vo.TaskResult;
/**
 * 具体的工作任务类
 */
public class MyTask implements ITaskProcesser<Integer,String> {
    @Override
    public TaskResult<String> taskExecute(Integer data) {
        if(data>100){
            return new TaskResult<>(EnumResult.Success,"return ", "reason");
        }
        if(data<100){
            return new TaskResult<>(EnumResult.Failure,"return ", "reason");
        }
        return null;
    }
}
