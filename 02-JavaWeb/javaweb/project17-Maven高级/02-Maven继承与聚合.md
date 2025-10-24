# 二、Maven 的继承与聚合

## 2.1、继承
- 概念：继承描述的是两个工程间的关系，与 `java` 中的继承相似，子工程可以继承父工程中的配置信息，常见于依赖关系的继承（即子工程继承父工程的 `pom.xml`）。
- 作用：简化`依赖`配置、统一依赖管理。
- 实现：通过子工程的 pom.xml 的 `<parent></parent>` 标签来实现。

### 继承关系实现
* 步骤1，创建 maven 模块 `tlias-parent`，该工程为父工程，设置打包方式 `pom`（默认jar）。
  由于是 Spring 项目，父工程需要继承 `spring-boot-starter-parent`。

  Maven 打包方式：
  `jar`：普通模块打包，springboot 项目基本都是 jar 包，因为springboot项目内嵌了tomcat，可以直接运行jar包。
  `war`：普通web程序打包，需要部署在外部的 tomcat 服务器中运行。（很少使用）
  `pom`：父工程或聚合工程，该模块不写代码，仅进行依赖管理。

* 步骤2，在子工程的 pom.xml 文件中，配置继承关系，注意如下几点：
    1. 子工程的坐标 ` <groupId> </groupId>` 是可以省略的，会自动继承父工程的。
    2. 子工程继承的坐标信息需通过 `<relativePath> </relativePath> `指定父工程的pom文件的相对位置。如果不指定，将从本地仓库/远程仓库查找父工程。
    3. 若父子工程都配置了同一个依赖的不同版本，以子工程的为准。

* 步骤3，在父工程中配置各个工程共有的依赖 (子工程会自动继承父工程的依赖)

### 版本锁定 - 统一管理依赖版本
在父子工程中，如果不是所有模块公共的依赖，不建议直接在父工程中引入该依赖。如果部分子工程中引入了相同的依赖，那么如何同一管理各个依赖的版本？

- 版本锁定, 是指在 maven 中，可以在父工程的 pom.xml 文件中通过 `<dependencyManagement>` 来统一管理依赖版本。
  子工程使用依赖时，只需引入依赖坐标无需指定 `<version>` 版本号，会自动使用父工程统一管理的版本。当需要变更依赖版本时，只需在父工程中统一变更。
- 自定义属性：在 maven 中可以自定义属性并设置值，相当于变量，便于引用使用。

```xml
<project>
    <!--  自定义属性，设置版本号  -->
    <properties>
        <jwt.version>0.9.1</jwt.version>
    </properties>
    <!-- 1、统一管理依赖版本 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <!--  <version>0.9.1</version>-->
                <!-- 引用自定义属性的值 -->
                <version>${jwt.version}}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

> ⚠️：spring-boot 父工程中对 `spring-boot` 相关的依赖版本进行了集中管理。


## 2.2、聚合
项目进行分模块设计开发后，业务模块打将变得非常繁琐。因为，业务模块通常依赖其它子模块，当打包业务模块时，就需要将其子模块通过 maven 命令
install 到本地仓库，然后才能完成打包。可以通过 `maven聚合` 功能解决此问题。

- 聚合：将多个模块组织成一个整体，同时进行项目的构建。
- 聚合工程：一个不具有业务功能的“空”工程，该工程里有且仅有一个 `pom` 文件。通常 父工程 和 聚合工程 是同一个！
- 作用：快速构建项目(无需根据依赖关系手动构建，直接在聚合工程上构建即可)
- 实现：maven 中通过 `<modules>` 设置当前聚合工程所包含的子模块名称。

如下配置所示在 聚合工程的 pom.xml 中聚合子工程：
```xml
<!-- 聚合子工程 -->
<modules>
    <module>../tlias-pojo</module>
    <module>../tlias-utils</module>
    <module>../tlias-web-manager</module>
</modules>
```

> ⚠️：
> 1、聚合工程中所包含的模块，在构建时，会自动根据模块间的依赖关系设置构建顺序，与聚合工程中模块的配置书写位置无关，即与 `<modules>` 中书写顺序无关。
> 2、在聚合工程中执行 maven 生命周期操作（如：clean、package）时，所有子工程都会执行对应的操作。

### maven中 继承 与 聚合的联系与区别
联系：继承与聚合都属于设计型模块，打包方式都为pom，常将两种关系制作到同一个pom文件中。
区别:
1、继承用于简化依赖配置、统一管理依赖版本，是在子工程中配置继承关系。
2、聚合用于快速构建项目，是在父工程（聚合工程）中配置聚合的模块。


