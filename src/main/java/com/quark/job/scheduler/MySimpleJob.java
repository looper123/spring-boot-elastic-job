package com.quark.job.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by Administrator on 2017/11/30/030.
 * 简单分布式作业
 */
public class MySimpleJob implements SimpleJob {


    @Override
    public void execute(ShardingContext context) {
        //一个job中可以进行分片   （相当于一个个小job）
        //每一个分片根据策略来指定运行的作业服务器上 （具体如何分配根据策略来指定）
        switch (context.getShardingItem()) {
            //case的数量必须和sharding-item-parameters 对应
            case 0:
                System.out.println("this is slot 0");
                break;
            case 1:
                System.out.println("this is slot 1");
                break;
            case 2:
                System.out.println("this is slot 2");
                break;
        }

    }


}