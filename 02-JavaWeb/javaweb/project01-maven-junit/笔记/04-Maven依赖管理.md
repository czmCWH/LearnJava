# Maven 依赖管理

## 一、依赖配置
依赖：指当前项目运行所需要的 `jar包`，一个项目中可以引入多个依赖。
配置：
  * 在 `pom.xml` 中编写 `<dependencies>` 标签；
  * 在 `<dependencies>` 标签中 使用 `<dependency>` 引入坐标；
  * 定义坐标的 `groupId，artifactId，version`，书写时默认会有提示；
  * 点击 `pom.xml` 文件中右侧浮动的 `刷新按钮`，引入最新加入的坐标；

> 注意⚠️：
> 如果引入的依赖，在本地仓库不存在，将会连接 远程仓库/中央仓库，然后下载依赖到 maven 配置的本地仓库中。(注意，引入依赖时请保持联网！)
> 如果不知道依赖的坐标信息，可以到 https://mvnrepository.com/ 中搜索。

### 1.1、依赖传递特性
* 依赖具有传递性
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
    
    <!--排除依赖传递，排除依赖不需要指定 version -->
    <exclusions>
        <exclusion>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

> 可以直接通过 Maven 管理依赖，或者 安装 IDEA 插件 `Maven Helper`，用于管理依赖关系
> IDEA > Settings > Plugins > Marketplace > 搜索 Maven Helper 安装即可。

### 1.2、依赖范围
依赖的 jar 包，默认情况下，可以在任何地方使用。可以通过 `<scope>...</scope>` 设置其作用范围。

依赖的 jar包 默认你作用范围如下：
  jar包在主程序范围有效 (jar包在 `main/` 文件夹范围内可用)
  测试程序范围有效 (jar包在 `test/` 文件夹范围内可用)
  是否参与打包运行 (jar包在 `package/` 指令范围内)

* scope 的取值类型：
  `compile`(默认值)：主程序、测试程序、打包(运行)；--- 常用
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

## 二、Maven 生命周期

Maven 的生命周期就是为了对所有的 maven 项目构建过程进行抽象和统一。
Maven 中有 3套 相互独立的生命周期。每套生命周期包含一些阶段(phase)，阶段是有顺序的，后面的阶段依赖于前面的阶段（即，后面阶段执行时，前面各阶段都会执行），如下所示：

### 1、clean：清理工作；
* pre-clean
* clean，移除上一次构建生成的文件。清理编译后的 target 目录。
* post-clean

### 2、default：核心工作，如：编译、测试、打包、安装、部署等；

* compile，编译项目源代码。即，把项目的 java 源代码文件编译为 .class 字节码文件。
* test，使用合适的单元测试框架运行测试 (如：junit)。一旦运行 test，项目中的所有单元测试都会执行。
* package，将编译后的文件打包，如：jar、war等。 可以直接通过 java 命令运行。
* install，安装项目到本地仓库。

### 3、site：生成报告、发布站点等；（基本不会用）
* pre-site
* site
* post-site
* site-deploy

> 注意⚠️：
> 在同一套生命周期中，当运行后面的阶段时，前面的阶段都会运行。
> 比如：执行 default 里面时，它本套内前面的都会执行。clean 里面的不会。


### 执行指定 Maven 生命周期的两种方式
1、在idea中，右侧的maven工具栏，选中对应的生命周期，双击执行。

2、通过 Maven 命令在命令行操作， 前提是 配置好了 Maven 环境变量。如下所示：
```
$ cd 项目的 pom.xml 目录下
# mvn 生命周期阶段命令
$ mvn compile
```

## 三、Maven 常见问题

### 1、Maven 面板中依赖显示报红
原因：由于网络原因，依赖没有下载完整导致的，在 maven 仓库中生成了 `xxx.lastUpdated` 文件，该文件不删除，不会再重新下载。
解决方案：
  方案1、根据 maven 依赖的坐标，找到仓库中对应的 `xxx.lastUpdated` 文件，删除，删除之后重新加载项目即可。
  方案2、终端切换到 本地仓库目录，通过命令(`del /s *.lastUpdated` )批量递归删除指定目录下的 `xxx.lastUpdated` 文件，删除之后重新加载项目即可。

