# 一、Maven 的安装 与 配置

> MacOS 上安装 Maven 时，可以在 Maven 官网上直接下载安装包，解压到 `/Library/` 目录下即可。（或者通过 Homebrew 工具进行安装）
> windows 上安装 Maven 时，解压即安装好了，然后进行如下配置。

## MacOS 上手动安装 Maven 后，配置环境变量

```shell
$ open ~/.zshrc

# Maven 配置
export MAVEN_HOME=/Library/apache-maven-3.9.9
export PATH=$PATH:$MAVEN_HOME/bin

$ source ~/.zshrc   

# 获取 Maven 版本信息
$ mvn -v
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /Library/apache-maven-3.9.9
Java version: 21.0.7, vendor: Oracle Corporation, runtime: /Library/Java/jdk-21.0.7.jdk/Contents/Home
Default locale: zh_CN_#Hans, platform encoding: UTF-8
OS name: "mac os x", version: "14.2.1", arch: "aarch64", family: "mac"


$ mvn install	# cd 到java 项目，然后拉取依赖(jar 包)
```

## 配置 Maven

在 Maven 的安装目录下找到 `/conf/settings.xml` 配置文件，并进行如下配置：

### 1、配置 Maven 本地仓库地址
步骤1、一般在 Maven 安装目录下创建本地仓库(repo 目录)，如：`/Maven/3.9.9/repo`。
步骤2、修改 `/conf/settings.xml` 中的 `<localRepository>` 来指定本地仓库地址：

```xml
<localRepository>***/Maven/3.9.9/repo</localRepository>
```

### 2、配置远程仓库

修改 `/conf/settings.xml` 中的 `<mirrors>` 标签，添加阿里云私服

```xml
  <mirrors>
   <mirror>
      <id>aliyunmaven</id>
      <mirrorOf>*</mirrorOf>
      <name>阿里云公共仓库</name>
      <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
  </mirrors>
```

### 3、配置JDK版本（项目可能会用到）

JDK 版本可以不配置，项目中一般用 JDK11。

```xml
<profile>
  <id>jdk-21</id>
  
  <activation>
    <activeByDefault>true</activeByDefault>
    <jdk>21</jdk>
  </activation>

  <properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <maven.compiler.compilerVersion>21</maven.compiler.compilerVersion>
  </properties>
</profile>
```


# 二、配置IDEA 的 Maven环境(全局)

1. 进入 IDEA 首页
2. 【自定义】Customize
3. 【所有设置】All settings
4. 【构建、执行、部署】Build,Execution, Deployment
5. 【构建工具】Build Tools
6. Maven home path，设置为 Maven 的安装路径

* 详细配置见 `/img` 目录示例图
