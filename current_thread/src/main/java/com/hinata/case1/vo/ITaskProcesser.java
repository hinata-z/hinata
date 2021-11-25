package com.hinata.case1.vo;
/**
 *  任务调用执行器，

 */
public interface ITaskProcesser<T, R>  {
    TaskResult<R> taskExecute(T data);

}
