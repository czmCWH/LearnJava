# 一、docker 挂载
### 1、挂载
在 Docker 中，挂载（Mount）是指将主机（宿主机）的目录或文件映射到容器中，实现资源文件双向同步。

### 2、为什么要把宿主机本地目录挂载到容器上呢？
1. 我们使用 `docker exec -it 容器名 /bin/bash` 命令进入容器内部，查看容器内的目录文件等，但是无法直接修改容器内的资源文件，因为容器是一个隔离空间里运行。
2. 容器本身是临时的，停止或删除后，容器内的数据也会丢失。通过容器挂载，可以将重要数据保存到宿主机，即使容器重建也不会丢失。便于解耦。

### 3、docker 的挂载类型
1. 卷（Volumes）：数据卷挂载、卷挂载
2. Bind Mount：绑定挂载、本地目录挂载
3. tmpfs 挂载（Tmpfs Mounts） 
4. 命名管道（Named Pipes）

# 二、volume 数据卷挂载
* 数据卷 `volume` 是一个虚拟目录，是 `容器内目录` 与 `宿主机目录` 之间映射的桥梁。
* 数据卷由 docker 管理，数据卷映射到宿主机的目录由 Docker 指定（如 /var/lib/docker/volumes/），目录层级比较深。 

```shell
# 查看 volume 命令
$ docker volume --help
```

## 1、数据卷常用命令
docker volume create：创建数据卷；
docker volume ls：查看所有数据卷；
docker volume rm：删除指定数据卷；
docker volume inspect：查看某个数据详情；
docker volume prune：清除数据卷；

## 2、挂载 数据卷 volume

* 在执行 `docker run` 命令时，使用 `-v 数据卷名:容器内目录` 来完成数据卷挂载；
* 挂载 volume 数据卷，只能在 docker run 创建容器时进行，如果容器已经创建，则无法挂载数据卷；
* 当创建容器时，如果挂载的数据卷不存在，会自动创建该数据卷；

### 案例-利用 nginx 部署静态资源
创建 nginx 容器，修改 nginx 容器内的 html 目录（`/usr/share/nginx/html`）下的 index.html 文件；
将静态资源（如：图片）部署到 nginx 的 html 目录；

```shell
# 创建 nginx 容器；-v：并挂载数据卷- 如果数据卷不存在会创建
$ docker run -d --name nginx -p 80:80 -v html:/usr/share/nginx/html nginx

# 查看所有数据卷列表
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
        "Mountpoint": "/var/lib/docker/volumes/html/_data",   # 挂载点，即挂载到宿主机的目录。此目录和 html 卷映射。html卷 和 容器内目录 进行映射。
        "Name": "html",
        "Options": null,
        "Scope": "local"
    }
]

# ⚠️⚠️⚠️，在 MacOS 上无法切换到此目录！！！这只是一个虚拟目录。如果是 linux 系统则会存在此目录
$ cd /var/lib/docker/volumes/html/_data
```

## 3、查看 容器 详情中 的数据卷
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
                "Source": "/var/lib/docker/volumes/695bf26ed3c8c61a7c3b18fdf198a2e3da1250db25a497b1836c963737419286/_data",   # 宿主机目录
                "Destination": "/var/lib/mysql",  # 容器内的目录
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

# 三、Bind Mount：绑定挂载、本地目录挂载
通过 数据卷方式挂载容器的目录，对应到宿主机的目录层级很深，不方便管理。可以采用 宿主机目录 挂载到容器的目录，如下所示：

1. 在执行 `docker run` 命令时，使用 `-v 本地目录:容器内目录` 可以完成本地目录挂载;
2. 本地目录必须以 `“/” 或 "./"` 开头，如果直接以名称开头，会被识别为 `数据卷` 而非 `本地目录`;
```
    `-v mysql:/var/lib/mysql` 会被识别为一个数据卷叫 `mysql`;
    `-v ./mysql:/var/lib/mysql` 会被识别为当前宿主机的 `mysql` 目录;
```

## 案例演示 本地目录挂载
基于 宿主机目录 实现 MySQL 数据目录、配置文件、初始化脚本的挂载。

1. 挂载宿主机的 `/root/mysql/data` 到容器内的 `/var/lib/mysql` 目录（存放数据的目录）；
2. 挂载宿主机的 `/root/mysql/init` 到容器内的 `/docker-entrypoint-initdb.d` 目录，宿主机目录需携带`day02-docker/file/01-mysql挂载目录/init`（创建数据库时会自动执行此sql脚本）；
3. 挂载宿主机的 `/root/mysql/conf` 到容器内的 `/etc/mysql/conf.d` 目录，宿主机目录需携带`day02-docker/file/01-mysql挂载目录/conf`（创建数据库容器会采用此配置）；

### 注意事项
* 1、如何知道 mysql 容器中这些数据目录呢？
可以在 hub.docker 网站上搜索 mysql 镜像，找到 how to use this images 目录说明：https://hub.docker.com/_/mysql#how-to-use-this-image
然后通过通过通义千问总结查询

* 2、删除 mysql 容器后，重新运行部署 mysql 容器，`/docker-entrypoint-initdb.d` 目录中的sql语句不执行
`MySQL` 官方镜像仅在容器首次启动时才会执行 `/docker-entrypoint-initdb.d` 目录下的SQL文件‌。
解决办法：
方法1：删除宿主机的 `/root/mysql/data` 中的内容，重新部署mysql容器。
方法2：删除 mysql 容器、mysql 镜像，从头部署。

### 步骤1：挂载 宿主机目录 到 mysql 容器：

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
-v /Users/chen/mysql/data:/var/lib/mysql \   # 挂载 宿主机目录(/Users/chen/mysql/data) : mysql容器目录(/var/lib/mysql)
-v /Users/chen/mysql/init:/docker-entrypoint-initdb.d \   # 创建容器时执行的 sql 初始脚本
-v /Users/chen/mysql/conf:/etc/mysql/conf.d \   # 指定数据库的 字符编码+时区
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

### 步骤2：查询挂载后的 宿主机目录是否生效
```shell
# 进入数据库容器
$ docker exec -it mysql /bin/bash

# 登录 mysql
bash-5.1# mysql -uroot -p123
# 查看配置 `day02-docker/file/01-mysql挂载目录/conf` 的字符编码
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
