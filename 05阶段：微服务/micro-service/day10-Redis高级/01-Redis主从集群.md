# 一、Redis 主从集群
单节点 Redis 可达到 1万 至 10万 QPS(每秒查询数)，基本上能满足大多数企业的需要。对一些用户量大、并发高的应用来讲，单节点 Redis 可能不够用了，这就需要使用 Redis 集群了。
通常，Redis 中的数据“读取多，写入少”，所以最常用的集群方式是 `主从集群`。通过 `主从集群` 实现 `读写分离`，从而提高读的并发能力。

`Redis` 主从集群中，有一个 `master` 节点、n个 `slave`节点（现在叫 `replica`）。当我们通过 `Redis` 的 `Java` 客户端访问主从集群时，应该做好路由：
- 如果是写操作，应该访问 `master` 节点，`master` 会自动将数据同步给n个 `slave` 节点；
- 如果是读操作，建议访问各个 `slave` 节点，从而分担并发压力；

slave：奴隶，replica ：副本

# 二、搭建 Redis 主从集群

### 需求：在同一个宿主机中利用3个Docker容器来搭建主从集群，容器要求如下：

  | **容器名** | **角色** | **IP**    | **映射端口** |
  | :--------- | :------- |:----------| :----------- |
  | r1         | master   | 宿主机IP     | 7001         |
  | r2         | slave    | 宿主机IP     | 7002         |
  | r3         | slave    | 宿主机IP     | 7003         |

### 实现：

#### 步骤1、使用 `file/docker-compose.yaml` 文件一键部署 Redis 容器：

```yaml
version: "3.2"

services:
  r1:
    image: redis
    container_name: r1
    network_mode: "host"   
    entrypoint: ["redis-server", "--port", "7001"]
  r2:
    image: redis
    container_name: r2
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7002"]
  r3:
    image: redis
    container_name: r3
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7003"]
```

说明：
1. `network_mode: "host"`，表示 `host` 网络模式下，容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口。
2. `entrypoint: ["redis-server", "--port", "7001"]`，在启动命令中修改 redis 的默认端口为7001。

```shell
# 1、安装 redis 镜像
$ docker pull redis:latest

# 2、使用 docker-compose.yaml 文件部署 Redis 集群
$ ls
docker-compose.yaml 
$ docker compose up -d

# 3、查看当前运行的 Redis 容器信息
# 由于 host 模式没有给 Redis 设置端口映射，所以 docker ps 命令无法查看到容器的ip 端口 信息
$ docker ps

# host 模式下容器直接使用宿主机的IP和端口，类似于宿主机中的进程。在 Linux 系统中可以通过如下命令查看IP/端口信息：
$ ps -ef | grep redis
# 在 mac 上需单独查看
$ docker inspect r1

# 4、停止 redis 集群
$ docker compose down
```

如上启动了3个Redis实例，但是它们并没有形成主从关系。我们需要通过命令来配置主从关系：

```Bash
# Redis5.0以前
slaveof <masterip> <masterport>
# Redis5.0以后
replicaof <masterip> <masterport>
```

#### 步骤2、建立 Redis 集群，通过如下命令配置容器主从关系：

配置容器主从关系有临时和永久两种模式：
- 永久生效：在redis.conf文件中利用`slaveof`命令指定`master`节点。
- 临时生效：直接利用redis-cli控制台输入`slaveof`命令，指定`master`节点。（推荐）

```shell
# 1、查看 r1 主节点 信息
$ docker exec -it r1 redis-cli -p 7001    # 进入正在运行的 r1 容器，并打开 Redis 的命令行客户端工具     
127.0.0.1:7001> info replication    # 查看集群状态
# Replication
role:master
connected_slaves:0
...
127.0.0.1:7002> exit

# 2、配置 r2 为 r1 的从节点
$ docker exec -it r2 redis-cli -p 7002
127.0.0.1:7002> slaveof 10.0.1.65 7001    # 设置 r2 人主 r1 为 master
OK
127.0.0.1:7002> info replication    # 查看集群状态
# Replication
role:slave
master_host:10.0.1.65
master_port:7001   
127.0.0.1:7002> exit 

# 3、配置 r3 为 r1 的从节点
$ docker exec -it r3 redis-cli -p 7003
127.0.0.1:7003> slaveof 10.0.1.65 7001    # 设置 r3 人主 r1 为 master
OK
127.0.0.1:7003> info replication    # 查看集群状态
# Replication
role:slave
master_host:10.0.1.65
master_port:7001
master_link_status:down
127.0.0.1:7003> exit

# 4、查看 r1 主节点 信息
$ docker exec -it r1 redis-cli -p 7001    
127.0.0.1:7001> info replication    # 查看集群状态
# Replication
role:master
connected_slaves:2
slave0:ip=127.0.0.1,port=7002,state=online,offset=182,lag=1
slave1:ip=127.0.0.1,port=7003,state=online,offset=182,lag=1
master_failover_state:no-failover
master_replid:8f5c06dd0ae3489feeafcf869837b1e5868c854c
master_replid2:0000000000000000000000000000000000000000
127.0.0.1:7001> exit
```

#### 步骤3、测试 Redis 主从集群

```shell
# 1、在 r1 主节点写数据
$ docker exec -it r1 redis-cli -p 7001    
127.0.0.1:7001> set num 222
OK
127.0.0.1:7001> get num
"222"
127.0.0.1:7001> exit

# 2、在 r2、r3 从节点读数据
$ docker exec -it r2 redis-cli -p 7002
127.0.0.1:7002> get num
"222"
$ docker exec -it r3 redis-cli -p 7003 
127.0.0.1:7003> get num
"222"
127.0.0.1:7003> set num 123
(error) READONLY You can't write against a read only replica.   # 从节点不允许写数据
127.0.0.1:7003> exit
```

# 二、Redis 主从同步原理
在上面测试中，我们发现`r1`上写入 Redis 的数据，在`r2`和`r3`上也能看到，这说明主从之间确实完成了数据同步。
那么这个同步是如何完成的呢？

1. 建立主从关系前，每个 `redis` 节点都是 `master`(主节点) ，有自己的`replid`和`offset`。
2. 当主从节点之间 第一次同步连接 或 断开重连 时，`slave`(从节点) 都会向主节点发送携带 `replid` 和 `offset` 参数的 `psync` 请求，尝试数据同步。

- `replid`，它是 `Replication Id` 的简称，表示数据集的标记，节点的 `replid` 一致则是同一数据集。
  - 每一个 `master` 都有唯一的 `replid`，`slave` 继承 `master` 节点的 `replid`。
 
- `offset`，表示记录数据的偏移量，主从节点都有此偏移量。

## 1、全量同步
- 全量同步判断依据：
  - 当在从节点执行`slaveof`命令与`master`建立主从关系时，`master`判断发现`slave`发送来的`replid`与自己的不一致，说明这是一个全新的 `slave`，需要做全量同步。

- 全量同步：
  - 当首次给 `master` 添加 `slave` 时，`master` 会将自己重新生成一个`replid`标记为新的主节点`replid`；
  - `master` 把 `replid` 和`offset` 都发送给这个`slave`，`slave`保存这些信息到本地。自此以后 `slave`的`replid`就与`master`一致了。
  - 同时，`master` 会将完整内存数据生成`RDB`文件，通过网络传输给 `slave`。`slave` 清空本地数据，加载`master`的`RDB` 到内存。
  - 在后续 `master` 中执行的 redis 命令则记录在 `repl_baklog`文件，并且逐个发送给 `slave` 执行。

## 2、增量同步
- 增量同步判断依据：
  - 当 `slave`(从节点) 向 `master`(主节点) 发送 `psync` 请求时，`master` 发现它们 `replid` 一致，但是 `offset` 不同，就需要做 增量同步了。

- `master` 的 `repl_baklog` 文件：
  - `repl_baklog` 文件是一个固定大小的数组，只不过数组是环形，也就是说 “角标到达数组末尾后，会再次从0开始读写”，这样数组头部的数据就会被覆盖；
  - `repl_baklog` 文件中记录 `Redis` 处理过的命令及`offset`，包括 `master` 当前的`offset`，和 `slave` 已经拷贝到的`offset`：

- 增量同步：
  - `master` 根据 `slave` 的 `offset`，取出 `repl_baklog` 文件中从 `slave offset` 之后的命令发送给 `slave` 执行。
  - 这样只更新 `slave` 与 `master` 存在差异的部分数据，就是增量同步了。

### 增量同步的问题
`repl_baklog` 文件大小有上限（默认2MB），写满后会覆盖最早的数据。如果slave断开时间过久，导致尚未同步的数据被覆盖，在`repl_baklog`中不能找到offset时，
则无法基于`repl_baklog`做增量同步，只能再次全量同步。

## 3、Redis主从集群同步优化

> 主从同步可以保证主从数据的一致性，非常重要。

可以从以下几个方面来优化 `Redis` 主从集群：

* 1、在 `master` 中配置`repl-diskless-sync  yes`启用无磁盘复制，避免全量同步时的磁盘IO；

启用无磁盘复制 是指在进行 “全量同步” 时，`master` 内存中的数据不进行写入到 `RDB` 文件传递，而是直接直接通过网络传递给 `slave`，这样能减少大量的磁盘IO，提高性能。


* 2、`Redis` 单节点上的内存占用不要太大，一般不要超过 8GB，减少 `RDB` 导致的过多磁盘IO；

如果 `Redis` 单节点上的内存不够用，可以根据业务部署多个 主从集群；或者采用分片集群。

* 3、适当提高`repl_baklog`的大小，发现 `slave` 宕机时尽快实现故障恢复，尽可能避免全量同步；

* 4、限制一个`master`上的`slave`节点数量，如果实在是太多slave，则可以采用`主-从-从`链式结构，减少 `master` 压力，但是 `slave` 时效性会降低；

一般来说，项目中做 `一主一丛`，或者 三节点 主从集群就能满足大多数需求。


