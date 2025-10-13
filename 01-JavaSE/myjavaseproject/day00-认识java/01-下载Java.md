# 一、认识 Java
Java 是美国 sun 公司(Stanford University Network)在1995年推出的一门计算机高级编程语言Java。
- Java 早期称为Oak(橡树)，后期改名为Java。
- Java 之父：詹姆斯·高斯林(James Gosling)。
- 2009年 sun 公司被 Oracle 公司收购。

## Java 技术平台

### 1、JavaSE(Java Platform，Standard Edition，标准版)
是 Java 的核心和基础
- 是其他两个平台的基础
- 可以开发桌面应用程序

### 2、JavaME(Java Platform，Micro Edtion，小型版)
主要应用在移动设备和嵌入式设备上(比如手机、PDA、电视机顶盒等)
- 以前智能手机上的一些应用、游戏就是基于Java ME开发的

### 3、JavaEE(Java Platform, Enterprise Edtion，企业版)
企业服务端开发的一套解决方案
- 用于服务端应用开发

## Java 版本
一般说 Java 的版本，就是指 Java SE 的版本、JDK的版本。如：JDK1.0、JDK1.1、JDK1.2、JDK1.3、JDK1.4、JDK5、JDK6 等等。

Java 的产品叫 JDK。JDK(`Java Development Kit`)，即java开发者工具包，必须安装 JDK 才能使用 Java。

Oracle 官网 <https://www.oracle.com/java/technologies/downloads/> 选择 `Products > 拉到最下 Hardware and Software` 下载 Java。

> Java 版本选型，JDK 8、11、17、21；很多企业还在使用JDK8/JDK11。

# 二、Java 安装
macOS 的 JDK 安装包（如 .pkg 格式）会将可执行文件路径自动注册到系统默认路径 `/usr/bin`，终端可直接识别 `java、javac` 命令‌。

在 macOS 上安装 Java 通常需要下载多个 JDK 版本，建议下载`.tar.gz`压缩包解压后放在同一个目录下，这样便于管理。

Java 常用终端命令
```shell
# java 是 执行工具
$ java --version

# javac 是 编译工具，c 是 compile(编译)
$ javac --version
javac 1.8.0_461

# 查看配置的 java 环境变量
$ echo $JAVA_HOME 
/Library/Java/jdk1.8.0_461.jdk/Contents/Home
```

## 多版本共存时，配置 Java 环境变量

macOS 上配置 Java 环境变量：

```shell
$ echo $SHELL
/bin/zsh

# 打开配置环境变量文件
$ open ~/.zshrc
```

`.zshrc` 文件配置信息如下：
```
# jdk环境变量
export JAVA_21_HOME=/Library/Java/jdk-21.0.7.jdk/Contents/Home
export JAVA_11_HOME=/Library/Java/jdk-11.0.28.jdk/Contents/Home
export JAVA_8_HOME=/Library/Java/jdk1.8.0_461.jdk/Contents/Home

# 默认jdk
export JAVA_HOME=$JAVA_8_HOME
export PATH="$JAVA_HOME/bin:$PATH"
```

<https://blog.csdn.net/qq2019010390/article/details/125464810>
在终端中输入 jdk8 即可切换为 8，输入 jdk11 即可切换为 11




