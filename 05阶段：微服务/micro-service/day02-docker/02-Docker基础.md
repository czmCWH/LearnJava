# 一、安装 Docker
企业部署一般都是采用 Linux 操作系统，而其中又数 CentOS 发行版占比最多。

Docker 分为 CE 和 EE 两大版本。CE 即社区版（免费，支持周期7个月）；
EE 即企业版，强调安全，付费使用，支持周期24个月。

Docker CE 支持 64 位版本 CentOS 7，并且要求内核版本不低于 3.10，CentOS 7 满足最低内核的要求。

<https://docs.docker.com/desktop/setup/install/mac-install/#install-and-run-docker-desktop-on-mac>
<https://www.runoob.com/docker/macos-docker-install.html>

```shell
$ docker --version
$ docker info
```

# 二、Docker 常见命令

## 1、docker 创建容器详解

```shell
# 把下载本地镜像加载到本地镜像仓库
$ docker load -i mysql.tar  

# 查看本地镜像仓库
$ docker images

$ docker run -d \     # docker run 创建并运行一个容器，-d 让容器在后台运行
--name mysql \        # -name 给创建的容器起一个名字，并且必须唯一
-p 3306:3306 \        # -p 设置容器的端口映射，宿主机(即 Docker 运行的主机)端口号 : 容器内部端口（制作镜像时就已经确定了）
-e TZ=Asia/Shanghai \   # -e 设置环境，可以在 dockerHub 里面搜索镜像查询如何配置。设置时区
-e MYSQL_ROOT_PASSWORD=123 \  # 设置 mysql root 密码
mysql   # 从镜像仓库中拉取镜像的名字，如果不携带版本号，则默认使用最新镜像版本
```

> 镜像命名规范:
> 镜像名称一般分为两部分组成：[repository]:[tag]。
> repository：镜像名；tag：镜像版本号


`docker run -d --name mysql -p 3306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123 mysql`
* 注意：使用上面的命令创建容器时，多次执行此命令，只要指定的宿主机端口号不一致，就可以创建多个容器。如：`-p 3306:3306`、`-p 3307:3306`...等。


## 2、docker 命令使用案例

1、安装容器

```shell
# 从 DockerHub 拉取 Nginx 镜像
$ docker pull nginx

# 查看本地镜像列表
$ docker images

# 创建并运行 nginx 容器
$ docker run -d --name nginx -p 80:80 nginx

# 查看正在运行的容器
$ docker ps

# 停止容器
$ docker stop nginx

# 查看部署的所有容器
$ docker ps -a

# 查看容器详细信息
$ docker inspect nginx

# 启动容器
$ docker start nginx

```

2、操作容器
```shell 
# 进入容器内部程序。-it：选项。/bin/bash：以什么方式打开容器程序。
$ docker exec -it nginx /bin/bash
root@aafb2f16415e:/# ls
# 进入 nginx 部署静态网页的目录
root@aafb2f16415e:/# cd usr/share/nginx/html/
root@aafb2f16415e:/usr/share/nginx/html# ls
50x.html  index.html
root@aafb2f16415e:/usr/share/nginx/html# cd ~
# 退出容器应用
root@aafb2f16415e:~# exit

# 删除容器-方式1
$ docker stop nginx
$ docker rm nginx
# 删除容器-方式2，停止并删除容器
$ docker rm -f nginx
```

进入mysql容器： `docker exec -it nginx mysql -u用户名 -p密码`

# 二、数据卷 volume
创建了容器后，虽然可以进入容器进行一些操作，但是无法把宿主主机里的文件添加到容器里，该如何解决此问题？这就需要借助于 数据卷。
* 
* 数据卷 `volume` 是一个虚拟目录，是`容器内目录`与`宿主机目录`之间映射的桥梁。
* 数据卷是一个虚拟目录，它将宿主机目录映射到容器内目录，方便我们操作容器内文件，或者方便迁移容器产生的数据。

```shell
# 查看 volume 命令
$ docker volume --help
```

## 1、案例-利用 nginx 部署静态资源
需求：
创建 nginx 容器，修改 nginx 容器内的 html 目录（`/usr/share/nginx/html`）下的 index.html 文件；
将静态资源（如：图片）部署到 nginx 的 html 目录；

实现：
1、创建 nginx 容器时，使用 `-v 数据卷名:容器内目录` 完成数据卷挂载；

```shell
# 创建 nginx 容器；-v：并挂载数据卷- 如果数据卷不存在会创建
$ docker run -d --name nginx -p 80:80 -v html:/usr/share/nginx/html nginx

# 查看所有数据卷
$ docker volume ls
DRIVER    VOLUME NAME
local     html

# 查看 html 数据卷 的详情
$ docker volume inspect html
[
    {
        "CreatedAt": "2025-07-18T08:52:04Z",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/html/_data",   # 映射宿主主机的目录
        "Name": "html",
        "Options": null,
        "Scope": "local"
    }
]

# ⚠️⚠️⚠️，在 MacOS 上无法切换到此目录！！！这只是一个虚拟目录
$ cd /var/lib/docker/volumes/html/_data
```

## 2、查看 容器 详情
```shell
$ docker inspect mysql
[
    {
        #... 
        "Mounts": [
            {
              # ⚠️，创建 mysql 容器时，会自动添加数据卷：Name 数据卷名，Source 指宿主机目录，Destination 指容器目录 
                "Type": "volume",   # ⚠️ volume 表示挂载的是 数据卷
                "Name": "695bf26ed3c8c61a7c3b18fdf198a2e3da1250db25a497b1836c963737419286",
                "Source": "/var/lib/docker/volumes/695bf26ed3c8c61a7c3b18fdf198a2e3da1250db25a497b1836c963737419286/_data",
                "Destination": "/var/lib/mysql",
                "Driver": "local",
                "Mode": "",
                "RW": true,
                "Propagation": ""
            }
        ],
        "Config": {
          #...
        },
        #...
    }
]

# 查询宿主机上所有的数据卷
$ docker volume ls    
```

## 3、基于宿主机目录挂载容器的目录

1. 在执行 `docker run` 命令时，使用 `-v 本地目录:容器内目录` 可以完成本地目录挂载;
2. 本地目录必须以 `“/” 或 "./"` 开头，如果直接以名称开头，会被识别为 数据卷 而非 本地目录;
```
    `-v mysql:/var/lib/mysql` 会被识别为一个数据卷叫 `mysql`;
    `-v ./mysql:/var/lib/mysql` 会被识别为当前宿主机的 `mysql` 目录;
```

### 案例：挂载 宿主机目录到 mysql 容器目录
基于 宿主机目录 实现 MySQL 数据目录、配置文件、初始化脚本的挂载。

1. 挂载宿主机的 `/root/mysql/data` 到容器内的 `/var/lib/mysql` 目录（存放数据的目录）；
2. 挂载宿主机的 `/root/mysql/init` 到容器内的 `/docker-entrypoint-initdb.d` 目录，宿主机目录需携带`day02-docker/file/01-mysql挂载目录/init`（创建数据库时会自动执行此sql脚本）；
3. 挂载宿主机的 `/root/mysql/conf` 到容器内的 `/etc/mysql/conf.d` 目录，宿主机目录需携带`day02-docker/file/01-mysql挂载目录/conf`（创建数据库容器会采用此配置）；

> ⚠️ 如何知道 mysql 容器中这些数据目录呢？
> 可以在 hub.docker 网站上搜索 mysql 镜像，找到 how to use this images 目录说明：https://hub.docker.com/_/mysql#how-to-use-this-image
> 然后通过通过通义千问总结查询

#### 步骤1：挂载 宿主机目录 到 mysql 容器：

```shell
# 删除已经存在的mysql容器
$ docker ps -a
$ docker rm mysql


# 重新添加 mysql 容器，并按要求指定挂载的 宿主机目录
$ docker run -d \
--name mysql \
-p 3306:3306 \
-e TZ=Asia/Shanghai \
-e MYSQL_ROOT_PASSWORD=123 \
-v /Users/chen/mysql/data:/var/lib/mysql \
-v /Users/chen/mysql/init:/docker-entrypoint-initdb.d \
-v /Users/chen/mysql/conf:/etc/mysql/conf.d \
mysql


# 查看容器详情，检查挂载目录
$ docker inspect mysql
[
    {
        #...
        "Mounts": [
            {
                "Type": "bind",   # ⚠️  bind 是指 绑定挂载，即将宿主机（Host）上的一个 目录或文件 直接挂载到容器（Container）中的指定路径。
                "Source": "/Users/chen/mysql/data",
                "Destination": "/var/lib/mysql",
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            },
            {
                "Type": "bind",
                "Source": "/Users/chen/mysql/init",
                "Destination": "/docker-entrypoint-initdb.d",
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            },
            {
                "Type": "bind",
                "Source": "/Users/chen/mysql/conf",
                "Destination": "/etc/mysql/conf.d",
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            }
        ],
        "Config": {
            #...
        },
        #...
    }
]
```

#### 步骤2：查询挂载后的 宿主机目录是否生效
```shell
# 进入数据库容器
$ docker exec -it mysql /bin/bash

# 登录 mysql
bash-5.1# mysql -uroot -p123
# 查看配置 `day02-docker/file/01-mysql挂载目录/conf` 的字符集
mysql> show variables like "%char%";
+--------------------------+--------------------------------+
| Variable_name            | Value                          |
+--------------------------+--------------------------------+
| character_set_client     | utf8mb4                        |
| character_set_connection | utf8mb4                        |
| character_set_database   | utf8mb4                        |
| character_set_filesystem | binary                         |
| character_set_results    | utf8mb4                        |
| character_set_server     | utf8mb4                        |
| character_set_system     | utf8mb3                        |
| character_sets_dir       | /usr/share/mysql-9.3/charsets/ |
+--------------------------+--------------------------------+
# 查看所有的数据库
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| hmall              |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
# 切换到由 `day02-docker/file/01-mysql挂载目录/init` 中sql脚本创建的数据库
mysql> use hmall;
mysql> show tables;
# 查看表信息
mysql> select * from address;
+----+---------+----------+--------+--------------+-------------+--------------------+-----------+------------+-------+
| id | user_id | province | city   | town         | mobile      | street             | contact   | is_default | notes |
+----+---------+----------+--------+--------------+-------------+--------------------+-----------+------------+-------+
| 59 |       1 | 北京     | 北京   | 朝阳区       | 13900112222 | 金燕龙办公楼       | 李嘉诚    | 0          | NULL  |
| 60 |       1 | 北京     | 北京   | 朝阳区       | 13700221122 | 修正大厦           | 李佳红    | 0          | NULL  |
| 61 |       1 | 上海     | 上海   | 浦东新区     | 13301212233 | 航头镇航头路       | 李佳星    | 1          | NULL  |
| 63 |       1 | 广东     | 佛山   | 永春         | 13301212233 | 永春武馆           | 李小龙    | 0          | NULL  |
+----+---------+----------+--------+--------------+-------------+--------------------+-----------+------------+-------+
4 rows in set (0.009 sec)

# 退出数据库
mysql> exit;
Bye
# 退出 mysql 容器
bash-5.1#  exit;
exit
```

# 三、自定义镜像 



# 四、容器网络


 