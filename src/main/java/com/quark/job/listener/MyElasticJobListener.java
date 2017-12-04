package com.quark.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * Created by Administrator on 2017/12/2/002.
 * 自定义监听器
 * 各节点都会执行
 */
public class MyElasticJobListener implements ElasticJobListener {

//    在job运行前执行
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("Listener is executing before job...");
    }

//    在job 执行后运行
    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("Listener is excuting after job...");
    }
}
