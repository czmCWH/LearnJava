# 一、Maven 工具
Maven 是一款用于 管理 和 构建 Java 项目的工具，是 apache 旗下的一个开源项目。
其它类似的工具还有：Gradle、Ant等

* Maven 网址：
    http://maven.apache.org/ ，Maven 开发者文档;
	https://projects.apache.org/project.html?maven ，可查看 Maven 发布版本;

* Apache 软件基金会：
Apache 软件基金会，成立于1999年7月，是目前世界上最大的最受欢迎的开源软件基金会，也是一个专门为支持开源项目而生的非盈利性组织。
	https://apache.org/ ， Apache 官网；
	https://projects.apache.org/projects.html ，Apache 项目列表；
 
# 二、Maven 的作用

Maven 是基于 project object model（项目对象模型，POM）的概念，通过一小段描述信息（即，pom.xml）来管理项目的构建。

1、方便的依赖管理：方便快捷的管理项目依赖的资源(第三方jar包)。

2、标准的项目构建流程：标准化的跨平台(Linux、Windows、MacOS)的自动化项目构建方式。如进行如下操作：
	编译(compile)、测试(test)、打包(package)、发布(deploy)

3、统一项目结构：提供标准、统一的项目结构，如下所示：
```
- src/
	- main/		# 主程序
		- java/		# java 源代码
		- resources/	# 项目的配置文件
	- test/		# 测试程序
		- java		# java 文件，用于单元测试
		- resources		# 测试相关的配置文件
- pom.xml	# 核心配置文件
```


# 三、Maven 仓库
Maven 的仓库，是指用于存储资源，管理各种 jar 包。
* 本地仓库：自己计算机上的一个目录。
* 远程仓库(私服)：一般由公司团队搭建的私有仓库。例如：https://maven.aliyun.com/repository/public
* 中央仓库：由 Maven 团队维护的全球唯一，仓库地址：https://repo1.maven.org/maven2/

Maven 查找依赖（jar 包）的顺序：
	本地仓库 > 远程仓库 > 中央仓库

* 一般在 Maven 安装目录下创建本地仓库，即 repo 目录。












