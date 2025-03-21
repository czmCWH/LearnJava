# Maven
**Maven 是一款用于管理和构建 Java 项目的工具，是 apache 旗下的一个开源项目。**
其它类似的工具还有：Gradle、Ant等

Maven 是基于 project object model(项目对象模型，POM)的概念，通过一小段描述信息来管理项目的构建。
Maven官网：http://maven.apache.org/

### Maven 的作用

1、方便的依赖管理：方便快捷的管理项目依赖的资源(jar包)。

2、标准的项目构建流程：标准化的跨平台(Linux、Windows、MacOS)的自动化项目构建方式。

3、统一项目结构：提供标准、统一的项目结构。

### Maven 的仓库类型

Maven 中的仓库，是指用于存储资源，管理各种jar包。
* 本地仓库：自己计算机上的一个目录。
* 中央仓库：由 Maven 团队维护的全球唯一，仓库地址：https://repo1.maven.org/maven2/
* 远程仓库(私服)：一般由公司团队搭建的私有仓库。

* 一般在 Maven 安装目录下创建本地仓库，即 repo 目录。

Maven 远程仓库：https://maven.aliyun.com/repository/public

# 一、Maven 的安装

```shell
$ mvn -v	# 获取 Maven 版本信息

$ mvn install	# 拉取依赖
```

# 二、创建 Maven 项目

## 1、配置IDEA 的 Maven环境(全局)
进入 IDEA 首页 >【自定义】Customize >【所有设置】All settings > 【构建、执行、部署】Build,Execution, Deployment
【构建工具】Build Tools > 
		Maven home path，设置为 Maven 的安装路径

## 2、创建 Maven 项目步骤：
1、New Project
	Empty Project > Name、Location

2、New Module > 
	Language: Java 
	Build system: Maven
	JDK: Project SDK1.8/11/21

	Advanced Settings > 
		group ID
		artifact ID

## 3、Maven 项目的 pom.xml 配置

1、Maven坐标
	Maven 中的坐标是资源(jar)的唯一标识，通过该坐标可以唯一定位资源位置。
	使用坐标来定义项目或引入项目中需要的依赖。

2、Maven 坐标主要组成
	groupId：定义当前 Maven 项目隶属组织名称(通常是域名反写，例如:com.baidu)
	artifactId：定义当前Maven项目名称(通常是模块名称，例如 order-service、goods-service)
	version：定义当前项目版本号
		SNAPSHOT: 功能不稳定、尚处于开发中的版本，即快照版本
		RELEASE: 功能趋于稳定、当前更新停止，可以用于发行的版本

### 依赖配置
依赖：指当前项目运行所需要的jar包，一个项目中可以引入多个依赖。
配置：
	在 pom.xml 中编写 `<dependencies>` 标签；
	在 `<dependencies>` 标签中 使用 `<dependency>` 引入坐标；
	定义坐标的 groupId，artifactId，version；
	点击刷新按钮，引入最新加入的坐标；

注意：
	如果引入的依赖，在本地仓库不存在，将会连接 远程仓库/中央仓库，然后下载依赖。(这个过程会比较耗时，耐心等待)
	如果不知道依赖的坐标信息，可以到 https://mvnrepository.com/ 中搜索。


### 依赖传递
* 依赖具有传递性。
	直接依赖：在当前项目中通过依赖配置建立的依赖关系。
	间接依赖：被依赖的资源如果依赖其他资源，当前项目间接依赖其他资源。

* 排除依赖：指主动断开依赖的资源，被排除的资源无需指定版本，

```shell
<dependency>
	<!--依赖坐标-->
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
    <!--排除依赖-->
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

安装 IDEA 插件 Maven Helper，用于管理依赖关系
	IDEA > Settings > Plugins > Marketplace > 搜索 Maven Helper 安装即可。

### 依赖范围
依赖的 jar 包，默认情况下，可以在任何地方使用。可以通过 `<scope>...</scope>` 设置其作用范围。
作用范围：
	主程序范围有效 (main 文件夹范围内)
	测试程序范围有效 (test 文件夹范围内)
	是否参与打包运行 (package 指令范围内)
scope 的取值类型：
	compile(默认值)：主程序、测试程序、打包(运行)；
	test：测试程序；
	provided：主程序、测试程序；
	runtime：测试程序、打包(运行)；


# 四、Maven 生命周期

Maven 的生命周期就是为了对所有的 maven 项目构建过程进行抽象和统一。

Maven 中有3套相互独立的生命周期:
1、clean：清理工作；
	clean，移除上一次构建生成的文件。

2、default：核心工作，如：编译、测试、打包、安装、部署等；
	compile，编译项目源代码。
	test，使用合适的单元测试框架运行测试(如：junit)。
	package，将编译后的文件打包，如：jar、war等。
	install，安装项目到本地仓库。

3、site：生成报告、发布站点等；

注意：在同一套生命周期中，当运行后面的阶段时，前面的阶段都会运行。















