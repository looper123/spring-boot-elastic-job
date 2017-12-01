package com.quark.job.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2017/11/30/030.
 * 加载xml配置文件
 */
@Configuration
@ImportResource(locations={"classpath:application-elastic-job.xml"})
public class MyConfiguration {
}
