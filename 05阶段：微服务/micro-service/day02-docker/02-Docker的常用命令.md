# 一、安装 Docker
企业部署一般都是采用 Linux 操作系统，而其中又数 CentOS 发行版占比最多。

Docker 分为 CE 和 EE 两大版本。CE 即社区版（免费，支持周期7个月）；
EE 即企业版，强调安全，付费使用，支持周期24个月。

Docker CE 支持 64 位版本 CentOS 7，并且要求内核版本不低于 3.10，CentOS 7 满足最低内核的要求。

<https://docs.docker.com/desktop/setup/install/mac-install/#install-and-run-docker-desktop-on-mac>
<https://www.runoob.com/docker/macos-docker-install.html>

## 配置 Docker 加速器地址

`注册阿里云账号 > 登录账号 > 顶部tab【产品】> 容器 > 容器镜像服务 ACR > 管理控制台 > 镜像工具 > 镜像加速器`
然后 根据阿里云【操作文档】配置 Docker 加速器地址。

```shell
$ docker --version
# 查看 docker 详情中 Registry Mirrors 的地址是否是 阿里云镜像加速地址
$ docker info
```
> 亲测在mac上配置了阿里云镜像加速地址无效！ 

# 二、Docker 常见命令

Docker最常见的命令就是操作镜像、容器的命令，详见官方文档：<https://docs.docker.com/reference/cli/docker/>

常见命令如下：`day02-docker/img/02-Docker常用命令.jpg`

## 1、docker 创建 MySql 容器（即部署 MySql）详解

```shell
# 把下载本地镜像加载到本地镜像仓库
$ docker load -i mysql.tar  

# 查看本地镜像仓库
$ docker images

$ docker run -d \     # docker run 创建并运行一个容器，如果镜像不存在会先拉取；-d：让容器在后台运行
--name mysql \        # -name 给创建的容器起一个名字，并且必须唯一
-p 3306:3306 \        # -p 设置容器的端口映射，宿主机(即 Docker 运行的主机)端口号 : 容器内部端口（制作镜像时就已经确定了）
-e TZ=Asia/Shanghai \   # -e 设置环境变量，可以在 dockerHub 里面搜索镜像查询如何配置。设置时区
-e MYSQL_ROOT_PASSWORD=123 \  # 设置 mysql root 密码
mysql   # 从镜像仓库中拉取镜像的名字，如果不携带版本号，则默认使用最新镜像版本
```
> 注意：shell 指令使用 `\` 换行符连接时， `\`不能有任何空格，否则会执行报错！！！

* 1、`-p 宿主机端口:容器端口` 端口映射的作用？
因为容器在部署的服务器（宿主机）上运行时，外部是无法访问容器的，通过让 宿主机的端口 与 容器的端口 进行映射。
当外部访问 宿主机的某个端口时，docker 就能把这个请求转到 对应的容器，这样外部就可以对容器访问。

> `docker run -d --name mysql -p 3307:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123 mysql`
> 可以通过如上命令在同一台服务器上，根据同一个镜像创建多个不同端口的容器，如：`-p 3306:3306`、`-p 3307:3306`...等。

* 2、`-e` 环境变量的作用？
创建容器运行时用到的变量。由创建镜像时指定，需要查询镜像文档说明进行配置。

* 3、镜像名称结构
镜像名称一般分为两部分组成：[repository]:[tag]。
`repository：镜像名；tag：镜像版本号`

## 2、docker 命令基本使用

### 1、镜像的导入与导出
```shell
# 查看本地镜像列表
$ docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
nginx        latest    af5dda48e624   6 days ago     198MB
# 导出 nginx:latest 镜像到 nginx.tar 压缩包内 
$ docker save -o nginx.tar nginx:latest
# 查看保存的镜像压缩包
$ open .

# 删除镜像
$ docker rmi nginx
# 导入镜像到本地仓库
$ docker load -i mysql.tar  
$ docker images
```

### 2、容器基本使用
```shell
# 从 DockerHub <https://hub.docker.com/> 拉取 Nginx 镜像到本地
$ docker pull nginx

# 查看本地镜像列表
$ docker images

# 创建并运行 nginx 容器，如果镜像不存在会先拉取
$ docker run -d --name nginx -p 80:80 nginx

# 查看当前运行的容器
$ docker ps

# 停止容器
$ docker stop nginx

# 查看部署的所有容器
$ docker ps -a

# 查看容器详细信息
$ docker inspect nginx

# 启动容器
$ docker start nginx

# 查看容器的运行日志
$ docker logs nginx
# 持续查看容器的运行日志
$ docker logs -f nginx
```

### 3、操作容器
```shell 
# 容器是在隔离空间里，使用命令进入容器内部程序。-it：可交互终端。/bin/bash：以什么方式与容器程序交互。
$ docker exec -it nginx /bin/bash
root@aafb2f16415e:/# ls -l
# 进入 nginx 部署静态网页的目录
root@aafb2f16415e:/# cd usr/share/nginx/html/
root@aafb2f16415e:/usr/share/nginx/html# ls
50x.html  index.html
root@aafb2f16415e:/usr/share/nginx/html# cd ~
# 退出容器内的应用
root@aafb2f16415e:~# exit

# 删除容器-方式1
$ docker stop nginx
$ docker rm nginx
# 删除容器-方式2，停止并删除容器
$ docker rm -f nginx
```

进入mysql容器： `docker exec -it mysql mysql -u用户名 -p密码`

```shell
# 进入 mysql 容器
$ docker start mysql
$ docker exec -it mysql bash

# 登录 mysql
bash-5.1# mysql -uroot -p123
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.027 sec)

# 退出 mysql
mysql> exit;
Bye
# 退出 mysql 容器
bash-5.1# exit;
exit
```

# 三、在终端添加 docker 命令别名

```shell
$ vi ~/.bashrc
alias rm="rm -i"
alias cp="cp -i"
alias dps='docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"'
alias dis="docker images"

$ source  ~/.bashrc
```