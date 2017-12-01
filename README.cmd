# elastic job console 部署
1 下载 elastic-job-lite-console 项目 地址：https://github.com/elasticjob/elastic-job/tree/master/elastic-job-lite/elastic-job-lite-console
2 打包
3 解压
4 启动 在 bin 目录下  linux启动 sh   windows启动 bat
5 conf 目录配置 登录用户
  默认    root.username=root
          root.password=root
          guest.username=guest
          guest.password=guest
6 登录 localhost:8899  输入用户密码
7 把已经启动的elastic job 关联的zookeeper ip配置上去  注意 namespace 必须和你项目中配置的zk的namespace相同 不然无法找到job的信息
