# 一、创建 Maven 项目步骤：

具体步骤请查看 `/img/04-从空项目创建maven模块` 示例图：

```
1、New Project
Empty Project > Name、Location

2、New Module >
Language: Java
Build system: Maven
JDK: Project SDK1.8/11/21
	Advanced Settings > 
		group ID
		artifact ID
```

# 二、Maven 项目的 pom.xml 配置

## 1、Maven 坐标

Maven 中的坐标是资源(jar包) 的唯一标识，通过该坐标可以唯一定位资源位置。

开发中使用坐标来定义项目或引入项目中需要的依赖。

## 2、Maven 坐标主要组成

* `groupId`：定义当前 Maven 项目隶属组织名称(通常是域名反写，例如：`com.baidu`)

* `artifactId`：定义当前 Maven 项目名称(通常是模块名称，例如：`order-service、goods-service`)

* `version`：定义当前项目版本号，常用的取值有：
    `SNAPSHOT`: 功能不稳定、尚处于开发中的版本，即快照版本 
    `RELEASE`: 功能趋于稳定、当前更新停止，可以用于发行的版本


## 2、依赖配置

依赖：指当前项目运行所需要的 `jar包`，一个项目中可以引入多个依赖。

配置：

* 在 `pom.xml` 中编写 `<dependencies>` 标签； 
* 在 `<dependencies>` 标签中 使用 `<dependency>` 引入坐标； 
* 定义坐标的 `groupId，artifactId，version`，书写时默认会有提示； 
* 点击 `pom.xml` 文件中右侧浮动的 `刷新按钮`，引入最新加入的坐标；

> 注意： 
> 如果引入的依赖，在本地仓库不存在，将会连接 远程仓库/中央仓库，然后下载依赖到 maven 配置的本地仓库中。(这个过程会比较耗时，耐心等待)
> 如果不知道依赖的坐标信息，可以到 https://mvnrepository.com/ 中搜索。

## 3、依赖传递特性

* 依赖具有传递性。
  直接依赖 ：在当前项目中通过依赖配置建立的依赖关系。
  间接依赖：被依赖的资源如果依赖其他资源，当前项目间接依赖其他资源。

* 排除依赖：指主动断开依赖的资源，比如：A 依赖 B，B 依赖 C; 可以在 A 中可以排出 B、C 中不需要的依赖。
被排除的资源无需指定版本，因为一个依赖只能导入一个版本。

```shell
<dependency>
	<!--依赖坐标-->
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
    <!--排除依赖传递-->
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

> 可以直接通过 Maven 管理依赖，或者 安装 IDEA 插件 `Maven Helper`，用于管理依赖关系 
> IDEA > Settings > Plugins > Marketplace > 搜索 Maven Helper 安装即可。

## 4、依赖范围
依赖的 jar 包，默认情况下，可以在任何地方使用。可以通过 `<scope>...</scope>` 设置其作用范围。

* 作用范围：
    jar包在主程序范围有效 (jar包在 `main/` 文件夹范围内可用)
    测试程序范围有效 (jar包在 `test/` 文件夹范围内可用)
    是否参与打包运行 (jar包在 `package/` 指令范围内)

* scope 的取值类型：
    `compile`(默认值)：主程序、测试程序、打包(运行)；（常用）
    `test`：测试程序；
    `provided`：主程序、测试程序；
    `runtime`：测试程序、打包(运行)；

> 在 pom.xml 文件中书写依赖时如果自动生成 `<scop>`，则添加，否则不用写。

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.11.1</version>
    <!--  设置依赖范围为 test/ 目录内可用  -->
    <scope>test</scope>
</dependency>
```

# 三、Maven 生命周期

Maven 的生命周期就是为了对所有的 maven 项目构建过程进行抽象和统一。

Maven 中有3套相互独立的生命周期:

## 1、clean：清理工作；

* pre-clean
* clean，移除上一次构建生成的文件。清理编译后的 target 目录。
* post-clean

##  2、default：核心工作，如：编译、测试、打包、安装、部署等；

* compile，编译项目源代码。 
* test，使用合适的单元测试框架运行测试 (如：junit)。 
* package，将编译后的文件打包，如：jar、war等。 可以直接通过 java 命令运行。
* install，安装项目到 本地仓库。

## 3、site：生成报告、发布站点等；（基本不会用）
* pre-site 
* site 
* post-site 
* site-deploy

> 注意：
> 在同一套生命周期中，当运行后面的阶段时，前面的阶段都会运行。
> 比如：执行 default 里面时，它本套内前面的都会执行。clean 里面的不会。


### 构建 Maven 项目的方式

1、直接通过 Maven 面板操作

2、通过 Maven 命令在命令行操作， 前提是 配置好了 Maven 环境变量

```
$ cd 项目的 pom.xml 目录下
$ mvn compile
```


