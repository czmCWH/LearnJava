对于一个中大型的项目时，所涉及到的组件会比较多，最终在项目部署时对应的容器也比较多，达到十几个甚至几十个。
并且容器部署运时还存在先后顺序，比如服务的中间件（mysql、mq、redis）需要在服务前先部署，各个java服务也可能存在依赖关系部署也需要制定依赖顺序。

- 手动部署项目存在问题：
    - 部署繁琐；
    - 不便于统一管理；

# 一、DockerCompose 一键部署项目
`Docker Compose` 通过一个单独的 `docker-compose.yml` 模板文件(YAML 格式)来定义一组相关联的应用容器，
帮助我们实现多个相互关联的 `Docker` 容器的快速部署。

`docker-compose.yml` 文件中描述的信息 和 `docker run` 中描述的信息几乎完全一样，只是语法有些差别。

- 案例：基于 DockerCompose 一键部署 hmall 项目，涉及到的服务：MySql数据库、服务端、前端Nginx：
- 步骤：
  1. 准备资源（sql初始化文件、服务端jar包、Dockerfile文件、前端打包文件、nginx.conf）；
  2. 准备 `docker-compose.yml` 配置文件，如下所示；
  3. 运行命令基于 DockerCompose 一键部署项目；

```yml
version: "3.8"

services:
  mysql:
    image: mysql
    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      TZ: Asia/Shanghai
      MYSQL_ROOT_PASSWORD: 123
    volumes:
      - "./mysql/conf:/etc/mysql/conf.d"
      - "./mysql/data:/var/lib/mysql"
      - "./mysql/init:/docker-entrypoint-initdb.d"
    networks:
      - hm-net
  hmall:
    build: 
      context: .
      dockerfile: Dockerfile
    container_name: hmall
    ports:
      - "8080:8080"
    networks:
      - hm-net
    depends_on:
      - mysql
  nginx:
    image: nginx
    container_name: nginx
    ports:
      - "18080:18080"
      - "18081:18081"
    volumes:
      - "./nginx/nginx.conf:/etc/nginx/nginx.conf"
      - "./nginx/html:/usr/share/nginx/html"
    depends_on:
      - hmall
    networks:
      - hm-net
networks:
  hm-net:   # hm-net 是网络标识；在 docker-compose.yml 文件中容器配置信息使用。
    name: hmall   # 执行 docker network 命令时使用，或者 Dockerfile文件 中使用。
```

`dockerCompose` 命令见 `/img/05-dockerCompose命令.jpg`

```shell
# 创建并启动所有 service 容器；-d 在后台运行
$ docker compose up -d
# 停止并移除容器和网络（保留卷）
$ docker compose down
```


# 学习地址
<https://www.bilibili.com/video/BV1yGydYEE3H?spm_id_from=333.788.videopod.episodes&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=199>
<https://www.bilibili.com/video/BV1S142197x7?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=35>