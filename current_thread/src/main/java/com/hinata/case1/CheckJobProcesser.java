package com.hinata.case1;

import com.hinata.case1.vo.ItemVo;
import com.hinata.case1.vo.JobInfo;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 *类说明：任务完成后,在一定的时间供查询结果，
 * 之后为释放资源节约内存，需要定期处理过期的任务
 */
public class CheckJobProcesser {

    /*单例化*/
    private static class ProcesserHolder{
        public static CheckJobProcesser processer = new CheckJobProcesser();
    }

    public static CheckJobProcesser getInstance() {
        return ProcesserHolder.processer;
    }

    /*存放任务的队列*/
    private static DelayQueue<ItemVo<String>> queue
            = new DelayQueue<ItemVo<String>>();


    static class CheckJobTask implements Runnable{

        private static DelayQueue<ItemVo<String>> queue
                = CheckJobProcesser.queue;

        //缓存的工作信息
        private static Map<String, JobInfo<?>> jobInfoMap
                = PendJobPool.getMap();
        @Override
        public void run() {

        }
    }


}
