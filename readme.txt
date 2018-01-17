这个项目是由企业中真实的项目提取而成（因为只提取了和员工信息相关的模块）

配合上真实项目的系统架构和权限系统，从而形成这样一套一般大小的BS模式管理系统；

原创的，希望加高亮；

因为项目文件过大，所以单独将 所使用到的jar包单独上传到百度网盘中；

jar包下载地址1 http://pan.baidu.com/s/1qXKkewW

jar包下载地址2 https://pan.baidu.com/s/1kVfZb8r

 

下面简单说一下这套系统的特点：

       01. 采用后台及前台的 Spring + Spring mvc + Hibernate + Bootstrap

       02. 后台全注解式的开发（除了必要的spring和hibernate的xml配置以外）

       03. 后台通过自定义注解结合一个访问拦截器实现整个系统的权限控制

       04. 系统前台采用全采用的Html+jQuery开发

       05. 系统前台与后台的交互全部使用 Ajax 异步请求

       06. 自定义 SecureValid 注解实现权限的控制

       07. 通过自定义 ExcludeLog 注解来实现对日志的记录进行排除

       08. Spring mvc 返回数据格式采用统一的对象（JSONReturn）进行封装

       09. 通过自定义处理器 ExceptionIntercept 实现 Spring mvc的全局异常捕获

       10. 系统中包含了企业中采用的开发工具类的集合

       11. AbstractDao 父类实现了Dao中针对单个对象的常用操作

        12. data_patch / patch.1.0.0.0.init.sql 文件中包含了系统初始化的数据

 

如何在本地的myeclipse或Tomcat中运行这个系统：

        1. 将项目导入到myeclipse中

        2. 将 data_patch / patch.1.0.0.0.init.sql 文件中的脚本全部导入到数据库中

        3. 导入数据库请在命令行中使用source命令进行导入，因为脚本文件中包含了70多万条地址信息，在软件中执行脚本会导致软件卡死或执行脚本的时间过长

        3. 修改 src/main/resource / 目录下的 config.properties 配置文件连接本地数据库即可