package com.quark.job.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30/030.
 * 数据流分布作业
 */
public class MyDataflowJob implements DataflowJob {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List fetchData(ShardingContext shardingContext) {
        List  list = jdbcTemplate.queryForList("select * from EMPLOYEE");
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List data) {
        for (int i = 0; i < data.size(); i++) {
            Map employeeMap = (Map) data.get(i);
             System.out.println("dataFlow job is executing...."+employeeMap.get("FIRST_NAME"));
//             System.out.println(employeeMap.get("LAST_NAME"));
//             System.out.println(employeeMap.get("AGE"));
//             System.out.println(employeeMap.get("SEX"));
//             System.out.println(employeeMap.get("INCOME"));
        }
    }
}
