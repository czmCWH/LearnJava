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

## IDEA 管理 Java 程序的总体结构：
* Project(项目、工程)，在 IDEA 中用文件夹表示。比如：小区。
* Module(模块)，一个 Project 中可以创建多个 Module， 比如：单元楼。
* Package(包)，一个 Module 中可以创建多个 Package，比如：每一层楼。
* class(类)，一个 Package 中可以创建多个 class，比如：每一层楼的房间。

## IDEA 创建工程

1、`Projects` > `New Project` > 选择 `Empty Project` > 指定工程目录存储目录 `myjavaseproject` 

2、选中工程目录右键选择 `Module`新建 > 输入 `day01-code` 模块名，并查看 `JDK` 是否关联成功 > 创建成功，后会有 `src` 文件夹

3、选择 `src` 文件夹右键 > 右键 `Package`，输入 `com.czm.hello` 命名包名(一般公司域名倒写+技术名)，如果有另一个包名为 `com.czm.speedkey`，则这2个包会折叠显示。

4、选择 `com.czm.hello` 包右键选择 `Java Class` > 输入类名 `hello`

## IDEA 快捷键

豆沙绿护眼颜色：`rgb(204, 238, 200) / CCEEC8`

* `main、psvm`：快速敲出 main 方法；`sout`：打印输出；

* `Ctrl + D`：复制当前行数据到下一行；

* `Ctrl + Y`：删除当前行；建议用 `ctrl + x`

* `Ctrl + Alt + L`：格式化当前代码；

* `Alt + Shift + 上下键`：上下移动当前行代码；

* `Ctrl + /、Ctrl + Shift + / `：对代码进行注释；


## IDEA 其它操作

* 打开工程，`File > Open > 选择工程名`。

* 关闭工程，直接点 X，或者 `File > Close Project` 。

* 删除类文件，选择类文件右键 `Delete`。

* 修改类名，选择类文件右键 `Refactor > Rename`。

* 修改模块，选择模块右键 `Rename module and directory`。

* **导入模块**
    方法1、复制需要导入的模块 > 先选择工程粘贴 > 再菜单栏 `File` > 选择 `Module from Existing Sources` > 选择工程中粘贴的模块，一直点下一步。
    方法2、新建模块，删除main文件 > 直接复制导入模块的代码粘贴即可。

* 删除模块，选择模块右键 `Remove Module` 然后 `Delete`。


# 三、Java API 文档

<https://www.oracle.com/java/technologies/javase-jdk17-doc-downloads.html>

* API 是应用程序编程接口：Java写好的程序，开发者可以直接调用。
* Java 提供的程序使用说明书。



