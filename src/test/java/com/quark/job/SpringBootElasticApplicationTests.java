package com.quark.job;

import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.dangdang.ddframe.job.lite.lifecycle.internal.settings.JobSettingsAPIImpl;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticApplicationTests {

    @Resource(name = "regCenter")
    private CoordinatorRegistryCenter regCenter;

    @Test
    public void contextLoads() {
    }


    //    JobSettingsApi 测试
    @Test
    public void testElasticJobApi() {
        JobSettingsAPIImpl jobSettings = new JobSettingsAPIImpl(regCenter);
        JobSettings settings = jobSettings.getJobSettings("simpleJobExecutor");
        System.out.println(settings.getCron());//简单job
        System.out.println(settings.getDescription());//simpleJobExecutor
        System.out.println(settings.getJobName());//  SIMPLE
        System.out.println(settings.getJobType());// propertiesexecutor_service_handler=com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler
        for (Map.Entry properties : settings.getJobProperties().entrySet()) {
            System.out.println("properties" + properties);//propertiesjob_exception_handler=com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler
        }
    }

    //zookeeper connection test
    @Test
    public void zkConnectionTest() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.194.128", 5000000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event is processing....");
            }
        });
        String baseurl = "/";
        zkNodeDelete(zooKeeper, baseurl);
        zooKeeper.close();
    }

    private void zkNodeDelete(ZooKeeper zooKeeper, String baseurl) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(baseurl, true);
        if (children == null || children.size() == 0) {
            zooKeeper.delete(baseurl, zooKeeper.exists(baseurl, true).getVersion());
        } else {
            for (String child : children) {
                if (baseurl.endsWith("/")) {
                    if (!"zookeeper".equals(child)) {
                        zkNodeDelete(zooKeeper, baseurl + child);
                    }
                } else {
                    zkNodeDelete(zooKeeper, baseurl + "/" + child);
                }
            }
        }

    }


}
