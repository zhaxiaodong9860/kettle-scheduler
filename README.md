# kettle-scheduler
背景
Kettle作为用户规模最多的开源ETL工具，强大简洁的功能深受广大ETL从业者的欢迎。但kettle本身的调度监控功能却非常弱。Pentaho官方都建议采用crontab(Unix平台)和计划任务(Windows平台)来完成调度功能。所以大家在实施kettle作业调度功能的时候，通常采用以下几种方式：使用spoon程序来启动Job，使用crontab或计划任务，自主开发java程序来调用kettle的类库。

项目介绍
Kettle调度监控平台（以下简称KS）是一个自主开发的javaweb程序，专门用来调度和监控由kettle客户端创建的job和transformation。KS整体的框架是由spring+sprin gmvc +beetlsql整合而成，通过调用kettle的API来执行转换和作业，并且使用quartz框架完成调度工作。

此版本基于kettle-8.0.0.0-28版本的API开发的，目前可以基本可以支持所有的组件，包括大数据组件（hbase、hive、hdfs等）。

项目源码：https://github.com/zhaxiaodong9860/kettle-scheduler

部署
基础环境
操作系统：windows（linux类似）
预装软件：jdk1.8、mysql、tomcat、kettle8.0
将源码中kettle-scheduler.sql导入mysql数据库。


将源码编译打包后解压到tomcat下的webapps目录下。


配置km\WEB-INF\classes\resource\db.properties
jdbc.driver=com.mysql.jdbc.Driver   //mysql驱动
jdbc.url=jdbc:mysql://192.22.107.97:3306/kettle-master?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false   //mysql的jdbc url
jdbc.username=root  //mysql用户名
jdbc.password=123456   //mysql密码


配置km\WEB-INF\classes\resource\ kettle.properties
kettle.home=WEB-INF\\lib   //kettlehome路径, 此处不用修改，但是需要将kettle8.0的环境变量文件kettle.properties拷贝替换掉项目路径km\WEB-INF\lib\.kettle下的同名文件
kettle.plugin=E:\\zhaxiaodong\\plugins   //kettle插件目录，可以自定义一个目录，然后将data-integration\plugins目录下所需的插件拷贝到此目录下
kettle.script=Html\\js\\libs\\url   //kettle所用到的js文件，目前未使用到
kettle.loglevel=detail      //kettle日志级别
kettle.log.file.path=D:\\data-integration\\logs  //执行kettle转换和作业产生的日志存放的路径
kettle.file.repository=D:\\data-integration\\test   //存放上传的文件作业或文件转换的路径，此功能已屏蔽，无需修改



将data-integration目录下的simple-jndi和system文件夹拷贝到apache-tomcat-9.0.12\bin目录下
配置\apache-tomcat-9.0.12\conf\ server.xml
<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />    //配置端口号
<Host name="localhost"  appBase="webapps" unpackWARs="true" autoDeploy="true">
<Context path="/km" docBase="/km"  reloadable ="true" debug="0" privileged="true"> </Context> 
… 
</Host>

启动tomcat
Windows:apache-tomcat-9.0.12\bin\startup.bat;
Linux: apache-tomcat-9.0.12\bin\startup.sh;
访问http://localhost:8080/km进入系统。


使用说明
登陆
        访问http://localhost:8080/km进入登陆界面，用户名admin,密码admin




首页
首页主要是显示监控信息，当一个任务（作业或转换）启动后，这个任务就处于被系统的监控状态下，首页展示了总监控任务数、监控作业数、监控转换数、转换监控记录（仅显示5条）、作业监控记录（仅显示5条）以及7天内作业和转换的监控状况。

 

资源库管理
管理kettle数据库资源库的信息，可以新增、修改、删除数据库资源库。



任务管理 – 作业管理
管理作业定时任务，可以新增、修改、删除作业定时任务，启动后作业即开始运行。



任务管理 – 转换管理
管理转换定时任务，可以新增、修改、删除转换定时任务，启动后转换即开始运行。



任务管理 – 执行策略
管理执行策略，可以新增、修改、删除执行策略（定时执行策略）。



监控管理 – 作业监控
处于运行的作业会被系统监控，此处显示被监控的作业的监控信息，包括总作业任务数、总执行成功次数、总执行失败次数以及每个作业的成功次数和失败次数。查看详情页面还可以查看每次执行的日志及执行时间，日志还可下载。



监控管理 – 转换监控
处于运行的转换会被系统监控，此处显示被监控的转换的监控信息，包括总转换任务数、总执行成功次数、总执行失败次数以及每个转换的成功次数和失败次数。查看详情页面还可以查看每次执行的日志及执行时间，日志还可下载。



用户管理
此菜单只有admin用户登陆时显示，用户管理用户，admin用户可以新增用户、编辑用户、删除用户。



最后希望大家可以一起维护此项目，如有问题可加入qq群提问 点击链接加入群聊【QQ群：817362677】【kettle-scheduler交流群】：https://jq.qq.com/?_wv=1027&k=59nBFXl

详细说明见个人博客：https://blog.csdn.net/zhaxiaodong/article/details/84107102


