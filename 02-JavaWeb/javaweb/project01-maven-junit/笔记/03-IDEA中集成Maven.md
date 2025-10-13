# IDEA 中集成 Maven

## 一、创建 Maven 项目

### 1、配置 IDEA 的 Maven环境(全局)
创建 Maven 项目之前，需要先配置 IDEA 的 Maven环境(全局)。如下步骤所示：
1. 进入 IDEA 首页
2. 【自定义】Customize ->【所有设置】All settings 
3. 【构建、执行、部署】Build,Execution,Deployment ->【构建工具】Build Tools

> ⚠️ 详细配置见 `/03-IDEA与maven配置(全局)` 目录示例图，这些是全局配置，而不是针对某个project的配置。

### 2、创建 Maven 项目
此时，新创建的项目会使用我们上面配置好的全局配置。
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

## 二、Maven 项目的 pom.xml 配置

## 1、Maven 坐标
Maven 中的坐标是资源(jar包) 的唯一标识，通过该坐标可以唯一定位资源位置。
开发中使用坐标来 定义项目 或 引入项目中需要的依赖。

## 2、Maven 坐标主要组成
* `groupId`：定义当前 Maven 项目隶属组织名称（通常是域名反写，例如：`com.baidu`）
* `artifactId`：定义当前 Maven 项目名称（通常是模块名称，例如：`order-service、goods-service`）
* `version`：定义当前项目版本号，常用的取值有：
    `SNAPSHOT`: 功能不稳定、尚处于开发中的版本，即快照版本 
    `RELEASE`: 功能趋于稳定、当前更新停止，可以用于发行的版本

## 2、Maven 项目的导入
在工程中导入 Maven 项目有2种方式，见图：`笔记/img/05-导入Maven项目`

导入Maven 项目注意：
1、建议将要导入的maven项目复制到你的目标项目目录下；
2、建议选择 maven 项目的 pom.xml 文件进行导入；

> 注意：如果 Maven 项目未被识别，可以在 pom.xml 上右键 add maven。 
