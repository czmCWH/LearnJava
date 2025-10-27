# 一、项目部署的问题
在日常开发中部署一个单体项目时，不同操作系统上需要不同的安装包，配置不同的运行环境。安装和部署步骤复杂，容易出错。

而对于微服务项目动辄就是几十台、上百台服务需要部署，有些大型项目甚至达到数万台服务。
由于每台服务器的运行环境不同，你写好的安装流程、部署脚本并不一定在每个服务器都能正常运行，经常会出错。
这就给系统的部署运维带来了很多困难。

* 有没有一种技术能够避免部署对服务器环境的依赖，减少复杂的部署流程呢？
可以使用 容器化技术-`Docker` 解决此问题，即便对 Linux不熟悉，也能只要在部署的服务器上安装了 `Docker`，就能轻松部署各种常见软件、Java项目。

  

# 二、Docker 入门
`Docker` 是一个快速构建、运行、管理`应用`的工具。--- 运维工具！

* 为什么要使用 Docker？
  - 例如：我们在 Linux 中安装 Mysql 非常繁琐，比如：安装包哪里下载，找不到；安装命令太多，记不住；步骤太多，易出错。
  - 而通过 Docker 只需要如下一条命令，即可完成 Mysql 的安装：
    ```shell
    $ docker run -d --name mysql -p 3306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123 mysql:8
    ```
Docker可以帮助我们下载应用镜像，创建并运行镜像的容器，从而快速部署应用。  

Docker官方文档: <https://docs.docker.com/>
Docker 常见命令：<https://docs.docker.com/reference/cli/docker/>


## 1、Docker 的核心组件

### 1.1、镜像(Image)
* 概念：将运行应用所需的`运行环境、配置文件、系统函数库`等与`应用`一起打包得到的就是`镜像`。
* 特点：
  - 镜像用于创建 Docker 容器 的模版。使用 Docker 安装应用时，Docker会自动搜索并下载应用镜像。 
  - 同一个镜像可以在同一主机上构建出n多个容器。
  - 可以实现一次构建，到处运行。

### 1.2、容器(Container)
* 概念：容器是镜像的可运行实例（或应用程序），是实际运行应用的`隔离环境`。
* 特点：
  - ⚠️ 每个容器都有独立的文件系统、网络环境(如：ip等)、进程空间，因此每个容器类似于一个小系统；
  - 容器与其它容器及其宿主机相互隔离
  - 可以使用 docker 启动、停止、移动 或 删除容器。


### 1.3、镜像仓库 Docker Registry 
镜像仓库 是一个 Docker 镜像的托管平台，用于 存储 和 管理 Docker镜像，类似于 GitHub。

* Docker 官方提供了一个专门管理、存储镜像的网站 `DockerHub`： <https://hub.docker.com/>；
* 国内也有类似于 `DockerHub` 的公开服务，比如 阿里云镜像库、华为云镜像服务等。

官方仓库在国外，下载速度较慢，一般我们都会使用第三方仓库提供的镜像加速功能，提高下载速度。
而企业内部的机密项目，往往会采用私有镜像仓库。


## 2、Docker 的架构
`Docker` 是一个 CS架构的程序，由两部分组成：
1. 服务端（`server`）：Docker 守护进程，负责处理 Docker 指令，管理镜像、容器等。
2. 客户端（`client`）：通过 命令 或 RestAPI 向 Docker 服务端发送指令。即：可以在本地或远程向服务端发送指令。

Docker 架构官方文档：<https://docs.docker.com/get-started/docker-overview/>



# 三、Docker 与 虚拟机的区别
* 虚拟机(virtual machine) 是在操作系统中模拟硬件设备，然后运行另一个操作系统，比如在 windows 系统里面运行 Ubuntu 系统，这样就可以运行任意的 Ubuntu 应用了。
  - 虚拟机中应用调用链：启用应用 -> 虚拟机上安装的内置系统 -> Hypervisor -> 本机操作系统 -> 计算机硬件

* Docker 容器中的项目是‌直接运行在宿主机的操作系统内核之上‌。
  - Docker 中应用调用链 ：启动应用 -> 本机操作系统 -> 计算机硬件

|  | Docker | 虚拟机 |
|:-:|:------:|:-:|
| 性能  |  接近原生  | 性能较差 |
| 硬盘占用 | 一般为MB  | 一般为GB |
| 启动  |   秒级   | 分钟级 |



<https://www.bilibili.com/video/BV1vo4y1T73j?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=2>
好👍 <https://www.bilibili.com/video/BV1HP4118797/?spm_id_from=333.337.search-card.all.click&vd_source=f97692c2f656607aeb97ee92b4310d9e>
https://www.bilibili.com/video/BV1S142197x7?spm_id_from=333.788.videopod.episodes&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=31