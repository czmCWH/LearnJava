# macOS 上 Java JDK 版本配置

## 一、Java 常用终端命令

```shell
# 查看安装所有 java 的路径
$ /usr/libexec/java_home -V 

# 查看 java 的环境变量配置
$ echo $JAVA_HOME 
```

## 二、默认情况下无需配置环境变量

macOS 的 JDK 安装包（如 .pkg 格式）会将可执行文件路径自动注册到系统默认路径 `/usr/bin`，终端可直接识别 `java、javac` 命令‌。


## 三、‌多版本共存时，需配置环境变量

<https://blog.csdn.net/qq2019010390/article/details/125464810>

```shell
#jdk环境变量
export JAVA_11_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.15.1.jdk/Contents/Home
export JAVA_8_HOME=/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home
 
#重置JAVA_HOME 参数
alias jdk11='export JAVA_HOME=$JAVA_11_HOME'
alias jdk8='export JAVA_HOME=$JAVA_8_HOME'
 
#默认jdk
export JAVA_HOME=$JAVA_8_HOME
 
#classPath
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/jrt-fs.jar:.
export CLASSPATH
```

在终端中输入 jdk8 即可切换为 8，输入 jdk11 即可切换为 11

