# 一、Java 入门程序

1、新建一个 test 文件夹，编写如下代码保存文件为 HelloWorld.java

```java
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("hello world!");
	}
}
```

2、保存后执行如下命令：
```shell
$ cd test

# 编译java，会生成 HelloWorld.class 文件
$ javac HelloWorld.java

# 执行 HelloWorld.class 文件，注意不需要带 .class
$ java HelloWorld

# 从 JDK 11 开发，可以直接用 java 执行 Java 源代码
$ java HelloWorld.java
```

# 二、Java 入门须知

## 1、Java 程序执行的原理

计算机底层都是硬件电路，可以通过不通电和通电，表示 0、1。
最早期的程序员通过机器语言编程的形式。
不管是什么样的高级编程语言，最终都是翻译成计算机底层可以识别的机器语言。

机器语言 > 汇编语言 > 高级语言

高级语言是 更简单、接近人类自己的语言，翻译器再将其翻译成计算机能理解的机器指令来驱动机器干活。

## 2、JDK 的组成

* JVM(java VirtualMachine): java 虚拟机，真正运行 java 程序 的地方。
* 核心类库: java 自己写好的程序，如：`System.out.println`，给程序员自己的程序调用的。
* JRE(Java Runtime Environment): Java 的运行环境，指 JVM + 核心类库。
* 开发工具：javac、java 命令，用于执行 Java 代码。

> JDK(Java Development Kit): Java 开发工具包(包括上面所有)

javac 命令编译 Java 源代码，java 把编译后的源代码运行在 JVM 上，JVM 执行代码调用核心类库，这样就完成了 Java 的执行。

## 3、Java 语言的跨平台特性

Java 跨平台：一次编译，处处可用。Java 为各种平台提供了对应的JVM虚拟机，程序在虚拟机里运行。

> Java程序 > javac 编译为 .class 文件 > 可运行在：Windows版JVM、Linux版本JVM、MacOS版本JVM。

## 4、Java 环境变量

## Path环境变量

* Path环境变量 可用于记住程序路径，以方便在命令行窗口的任意目录下直接启动该程序。

* 目前较新的JDK在安装时，会自动配置javac、java程序的路径到 Path环境变量 中。

### 手动配置 Path环境变量

Path: `/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin `

终端运行 java、javac 命令来验证 Path环境变量 是否配置成功。

```shell
$ java --version
$ javac --version
```

## JAVA_HOME环境变量

JAVA_HOME:是用于告诉操作系统JDK安装在了哪个位置(将来其他技术要通过这个环境变量找JDK)

JAVA_HOME: `/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home`

配置了 `JAVA_HOME` 后，Path环境变量可进行优化：`%JAVA_HOME%\bin`

# 三、Java 企业级开发工具

企业开发必用: IDE工具(Integrated Development Environment：集成开发环境)，如下：

eclipse、IntelliJ IDEA、NetBeans

## IntelliJ IDEA Ultimate(旗舰版)

<https://www.jetbrains.com.cn/idea/>

IDEA 管理 Java 程序的总体结构：

Project(项目、工程)
Module(模块)
Package(包)
class(类)












