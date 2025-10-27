# 一、Docker Storage
1、docker 容器的数据存储
在容器内创建的所有文件都存储在可写容器层中，该层位于只读、不可变的镜像层之上。
当容器被销毁时，即执行 `docker rm` 时，写入容器层的数据也会被删除。
每个容器的可写层都是唯一的。您无法轻松地将数据从可写层提取到主机或另一个容器。

<https://docs.docker.com/engine/storage/>

2、那么如何在容器的可写层之外存储数据？
- 挂载（Mount），是指将主机（宿主机）的目录或文件映射到容器中，实现资源文件双向同步。

- Docker 支持以下类型的存储挂载：
  1. Volume mounts：数据卷挂载、卷挂载
  2. Bind mounts：绑定挂载、本地目录挂载
  3. tmpfs mounts
  4. Named pipes 命名管道
