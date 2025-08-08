# 一、Redis 分片集群

## 1、Redis 主从集群的问题
Redis 主从和哨兵模式可以解决高可用、高并发读的问题。但依然有两个问题没有解决： 
* 1、海量数据存储问题。
    
    主从之间是数据备份的关系，数据容量上限就是单节点的上限。但是单节点的内存容量不能设置太大，
    因为这样在做主从数据同步或持久化时，效率非常的低。通常单节点的存储大小不超过8GB，这是无法满足海量数据存储的。

* 2、高并发写的问题。
    
    主从读写分离结构是一主多从，虽然可以应对高并发的读，但是主节点只有一个，无法应对高并发写。

要解决这两个问题就需要用到分片集群了。分片的意思，就是把数据拆分存储到不同节点，这样整个集群的存储数据量就更大了。

## 2、分片集群特征：
-  集群中有多个 `master`，每个 `master` 保存不同分片数据，解决海量数据存储问题；
-  每个 `master` 都可以有多个 `slave` 节点 ，确保高可用；
-  `master` 之间通过 `ping` 监测彼此健康状态 ，类似哨兵作用；
-  客户端请求可以访问集群任意节点，最终都会被转发到数据所在节点；

# 二、搭建 Redis 分片集群
`Redis` 分片集群 最少也需要 3个 `master`节点，由于我们的机器性能有限，我们只给每个 `master`配置1个slave，形成最小的分片集群。
这样一共只需要6个Redis节点，如下所示计划部署的节点信息如下：

| 容器名 | 角色   | IP    | 映射端口 |
| ------ | ------ |-------| -------- |
| r1     | master | 宿主机IP | 7001     |
| r2     | master | 宿主机IP | 7002     |
| r3     | master | 宿主机IP | 7003     |
| r4     | slave  | 宿主机IP | 7004     |
| r5     | slave  | 宿主机IP | 7005     |
| r6     | slave  | 宿主机IP | 7006     |

## 1、集群配置
分片集群中的 `Redis` 节点必须开启集群模式，一般在配置文件中添加下面参数：

```Bash
port 7000
cluster-enabled yes   # 是否开启集群模式
cluster-config-file nodes.conf    # 集群模式的配置文件名称，无需手动创建，由集群自动维护
cluster-node-timeout 5000   # 集群中节点之间心跳超时时间
appendonly yes  # 数据持久化，默认是开启的
```
如下使用`docker-compose`部署，因此可以直接在启动命令中指定如上参数。在宿主机上新建 `/redis-cluster/docker-compose.yaml`，内容如下：
```yaml
version: "3.2"

services:
  r1:
    image: redis
    container_name: r1
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7001", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
  r2:
    image: redis
    container_name: r2
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7002", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
  r3:
    image: redis
    container_name: r3
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7003", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
  r4:
    image: redis
    container_name: r4
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7004", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
  r5:
    image: redis
    container_name: r5
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7005", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
  r6:
    image: redis
    container_name: r6
    network_mode: "host"
    entrypoint: ["redis-server", "--port", "7006", "--cluster-enabled", "yes", "--cluster-config-file", "node.conf"]
```
> ⚠️，使用Docker部署Redis集群，network模式必须采用 host。

## 2、启动集群
进入`/redis-cluster`目录，使用命令启动redis：
```Bash
# 1、先停止 Redis 的主从集群
$ cd /root/redis
$ docker compose down

# 2、启动集群
$ cd /root/redis-cluster
$ docker compose up -d
```

启动成功，查看节点信息：
```Bash
# 在  Linux 中可以通过命令查看启动进程：
$ ps -ef | grep redis

# 在 MacOS 上只能查看容器信息
$ docker inspect r1
"Args": [
    "--port",
    "7001",
    "--cluster-enabled",
    "yes",
    "--cluster-config-file",
    "node.conf"
]
```

> 可以发现每个 redis 节点都以 cluster(集群) 模式运行。不过节点与节点之间并未建立集群连接。

## 3、创建分片集群关系

```Bash
# 进入任意节点容器
$ docker exec -it r1 bash
# 然后，执行命令
root@docker-desktop:/data# redis-cli --cluster create --cluster-replicas 1 \
192.168.12.168:7001 192.168.12.168:7002 192.168.12.168:7003 \
192.168.12.168:7004 192.168.12.168:7005 192.168.12.168:7006
```

命令说明：
- `redis-cli --cluster`：代表集群操作命令；
- `create`：代表是创建集群；
- `--cluster-replicas 1` ：指定集群中每个`master`的 副本个数(从节点个数) 为1；
  - 此时`节点总数 ÷ (replicas + 1)` 得到的就是`master`的数量`n`。因此节点列表中的前`n`个节点就是`master`，其它节点都是`slave`节点，随机分配到不同`master`
  - 例如：总节点为6，且`--cluster-replicas 2`时，则 1个`master` + 2个`slave` 3个节点形成一个主从集群。6÷3 = 2个主从集群，默认会把前面 7001、7002 节点设置为 `master`。
- `192.168.12.168`，指容器宿主机 IP地址，因为容器采用的 host 模式。

### MacOS 上容器使用 host 网络模式时，比较特殊，需要使用如下命令
```shell
# 1、先查看容器使用的 ip 地址，即 Docker VM 的 127.0.0.1（localhost）
$ docker exec -it r1 redis-cli -p 7001 
127.0.0.1:7001> exit

# 2、进入任意节点容器
$ docker exec -it r1 bash

# 3、执行命令创建集群
root@docker-desktop:/data# redis-cli --cluster create --cluster-replicas 1 \
127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 \
127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006
>>> Performing hash slots allocation on 6 nodes...     # 给 6个redis节点分配 hash 插槽 
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
# 随机分配主从关系，7001、7002、7003 为 master 节点
Adding replica 127.0.0.1:7005 to 127.0.0.1:7001
Adding replica 127.0.0.1:7006 to 127.0.0.1:7002
Adding replica 127.0.0.1:7004 to 127.0.0.1:7003
>>> Trying to optimize slaves allocation for anti-affinity
[WARNING] Some slaves are in the same host as their master
M: 79a51f5486cb0f63a2dd6034f33fac32d4ec6975 127.0.0.1:7001
   slots:[0-5460] (5461 slots) master
M: 72ee8d7fd9ff3463416cf76e4035764d9332bb01 127.0.0.1:7002
   slots:[5461-10922] (5462 slots) master
M: 600d0d8c94304869bcc54664368581a52b9071c6 127.0.0.1:7003
   slots:[10923-16383] (5461 slots) master
S: 4f31ad73439bc701b9df75785758f982fc0cef31 127.0.0.1:7004
   replicates 79a51f5486cb0f63a2dd6034f33fac32d4ec6975
S: c519384ab3f058adbc0067d0414b86e16c372a8e 127.0.0.1:7005
   replicates 72ee8d7fd9ff3463416cf76e4035764d9332bb01
S: 73093680fe96f074bd61f06a0995388492c175a2 127.0.0.1:7006
   replicates 600d0d8c94304869bcc54664368581a52b9071c6
# 是否运行按照如上关系配置，输入 yes
Can I set the above configuration? (type 'yes' to accept): yes
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.

# 至此，Redis 搭建分片集群完成
root@docker-desktop:/data# exit;
```

## 3、查看集群间的信息
```bash
# 1、先进入任意节点容器
 redis-cluster $ docker exec -it r1 bash

# 2、查看集群信息
$ root@docker-desktop:/data# redis-cli -p 7001 cluster nodes
c519384ab3f058adbc0067d0414b86e16c372a8e 127.0.0.1:7005@17005 slave 72ee8d7fd9ff3463416cf76e4035764d9332bb01 0 1754620533000 2 connected
600d0d8c94304869bcc54664368581a52b9071c6 127.0.0.1:7003@17003 master - 0 1754620534000 3 connected 10923-16383
72ee8d7fd9ff3463416cf76e4035764d9332bb01 127.0.0.1:7002@17002 master - 0 1754620533065 2 connected 5461-10922
4f31ad73439bc701b9df75785758f982fc0cef31 127.0.0.1:7004@17004 slave 79a51f5486cb0f63a2dd6034f33fac32d4ec6975 0 1754620535000 1 connected
79a51f5486cb0f63a2dd6034f33fac32d4ec6975 127.0.0.1:7001@17001 myself,master - 0 0 1 connected 0-5460
73093680fe96f074bd61f06a0995388492c175a2 127.0.0.1:7006@17006 slave 600d0d8c94304869bcc54664368581a52b9071c6 0 1754620536196 3 connected
```