package com.hinata.case1;

import com.hinata.case1.vo.ITaskProcesser;
import com.hinata.case1.vo.TaskResult;
/**
 * 具体的工作任务类
 */
public class MyTask implements ITaskProcesser<Integer,String> {
    @Override
    public TaskResult<String> taskExecute(Integer data) {
        return null;
    }
}
