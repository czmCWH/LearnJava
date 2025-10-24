# 一、Maven 私服
- Maven私服，私服是一种特殊的远程仓库，它是架设在局域网内的仓库服务，用来代理位于外部的中央仓库，用于解决团队内部的资源共享与资源同步问题。
    私服在企业项目开发中，一个项目/公司，只需要一台即可(无需我们自己搭建，会使用即可)。
    有了私服后项目中依赖查找顺序：本地仓库 --> 私服 --> 中央仓库。

- Maven 项目版本类型：
  - RELEASE(发行版本)：功能趋于稳定、当前更新停止，可以用于发行的版本，存储在私服中的RELEASE仓库中。
  - SNAPSHOT(快照版本)：功能不稳定、尚处于开发中的版本，即快照版本，存储在私服的SNAPSHOT仓库中。


```xml
<distributionManagement>
    <!-- 配置 maven 私服上传（发布）地址 -->
    <repository>
        <id>maven-release</id>
        <url>http://192.168.0.1:8081/repository/maven-releases/</url>
    </repository>
    <snapshotRepository>
        <id>maven-snapshots</id>
        <url>http://192.168.0.2:8081/repository/maven-snapshots/</url>
    </snapshotRepository>
</distributionManagement>
```





案例实现：`03-JavaWeb/project17-tlias-web`，对 `tlias-教学管理系统` 采用 分模块设计重构。
创建分模块设计项目的具体步骤：可查看视频 `06.分模块设计与开发-补充-0到1`。

