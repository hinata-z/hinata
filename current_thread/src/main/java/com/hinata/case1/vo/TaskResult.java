package com.hinata.case1.vo;

import lombok.Data;

/**
 * 任务调用返回结果
 *
 */
@Data
public class TaskResult<R> {

    private final  R returnValue;//返回数据
    private String reason;// 方法返回失败原因
    private EnumResult code; //返回状态 0 ： 成功 1：失败，2:异常

    public TaskResult(EnumResult code,R returnValue,String reason) {
        this.returnValue = returnValue;
        this.code=code;
        this.reason=reason;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "returnValue=" + returnValue +
                ", reason='" + reason + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
