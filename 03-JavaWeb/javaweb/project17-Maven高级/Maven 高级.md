# Maven 高级

案例实现：`03-JavaWeb/project17-tlias-web`，对 `tlias-教学管理系统` 采用 分模块设计重构。
创建分模块设计项目的具体步骤：可查看视频 `06.分模块设计与开发-补充-0到1`。

# 一、分模块设计与开发

## 为什么需要分模块开发？
日常开发中项目会非常大，多个人开发，如果都在同一个java项目内开发（即单体项目），会造成不便维护、难以复用等问题。通常，项目开发采用 分模块设计，有2种方案：

方案一：按照功能分模块：通用组件、商品模块、搜索模块、购物车模块、订单模块...；
方案二：按照结构分模块；

## 分模块设计
分模块设计 是指将项目按照功能拆分成若干个子模块，方便项目的管理维护、扩展，也方便模块间的相互调用，资源共享。

> ⚠️：在日常开发中，分模块开发 需要先针对模块功能进行设计，再进行编码。不会先将工程开发完毕，然后进行拆分。

采用分模块设计后，各个模块可能无法独立运行，这就需要配置各模块之间的依赖关系。 


# 二、Maven 的继承与聚合

## 2.1、继承
继承描述的是两个工程间的关系，与 `java` 中的继承相似，子工程可以继承父工程中的配置信息，常见于依赖关系的继承（即子工程继承父工程的 `pom.xml`）。

注意：模块不允许多继承。
作用：简化依赖配置、统一依赖管理。

### 继承的实现
1、创建 `maven` 模块 `tlias-parent`，该工程为父工程，设置打包方式 `pom`，而不采用默认的 `jar`。由于是 Spring 项目，所有该父工程需要继承 `spring-boot-starter-parent`。

打包方式的区别：
`jar`：普通模块打包，`springboot` 项目基本都是 `jar`包，并通过其内嵌的 `tomcat`运行。
`war`：普通web程序打包，需要部署在外部的 `tomcat` 服务器中运行。（很少使用）
`pom`：父工程 或 聚合工程，该模块不写代码，仅进行依赖管理。


2、在子工程的 `pom.xml` 文件中，配置继承关系。配置子工程的 pom.xml 需要注意以下几点：

* 在子工程中，配置了继承关系之后，坐标中的 `groupId` 是可以省略的，因为会自动继承父工程的。

* `relativePath` 指定父工程的 pom 文件的相对位置(如果不指定，将从本地仓库/远程仓库查找该工程)

* 若父子工程都配置了同一个依赖的不同版本，以子工程的为准。

3、在父工程中配置各个工程 共有的依赖 (子工程会自动继承父工程的依赖)

### 版本锁定

在 `maven` 中，可以在父工程的 `pom` 文件中通过 `<dependencyManagement>` 来统一管理依赖版本。

子工程引入依赖时，无需指定 <version>版本号，父工程统一管理。变更依赖版本，只需在父工程中统一变更。

作用：解决版本冲突。


## 2.2、聚合
虽然分模块化设计与开发简化了工程中依赖管理，但是在进行 业务模块 打包时却非常繁琐，因为业务模块通常需要依赖许多子模块，这就需要对这些子模块安装到本地仓库，然后才能进行打包。打包、测试非常的繁琐。这就需要使用 “聚合” 即决此问题。

* 聚合：将多个模块组织成一个整体，同时进行项目的构建。
* 聚合工程（通常是一个父工程）：即一个不具有业务功能的“空”工程，该工程里有且仅有一个 `pom` 文件。
* 作用：快速构建项目(无需根据依赖关系手动构建，直接在聚合工程上构建即可)

`maven` 中可以通过 `<modules>` 设置当前聚合工程所包含的子模块名称。

> 聚合工程中所包含的模块，在构建时，会自动根据模块间的依赖关系设置构建顺序，与聚合工程中模块的配置书写位置无关。


# 2.3、maven中 继承 与 聚合的联系与区别

联系：继承与聚合都属于设计型模块，打包方式都为pom，常将两种关系制作到同一个pom文件中
区别:
1、继承用于简化依赖配置、统一管理依赖版本，是在子工程中配置继承关系。
2、聚合用于快速构建项目，是在父工程中配置聚合的模块。


# 三、私服配置