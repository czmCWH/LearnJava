# 一、Redis哨兵
主从结构中 `master`(主节点) 作用非常重要，一旦故障整个 redis 集群就无法处理增删改操作，整个集群不可用。那么有什么办法能保证 `主从集群` 的高可用性呢 ？

`Redis` 提供了 `哨兵`（`Sentinel`）机制来 监控 主从集群 监控状态，确保集群的高可用性。
官方文档：<https://redis.io/docs/latest/operate/oss_and_stack/management/sentinel/>

## 1、Redis 哨兵工作原理

Redis提供了哨兵(Sentinel)机制来实现主从集群的自动故障恢复，哨兵(Sentinel)通常有3个服务形成集群，避免自身宕机而无法监控。
哨兵的作用如下:
1. 状态监控：`Sentinel` 会不断检查您的`master`和`slave`是否按预期工作 ；
2. 自动故障切换(failover，或者故障转移)：如果`master`故障，`Sentinel`会将一个`slave`提升为`master`。当故障实例恢复后会成为新提升 master 的`slave` ；
3. 状态通知：`Sentinel`充当`Redis`客户端的服务发现来源，当集群发生`failover`时，Sentinel 会将最新集群信息推送给`Redis`的客户端；

> 哨兵原理见图：`/img/02-哨兵/01-Redis哨兵原理.jpg`。

* `Sentinel`怎么知道一个Redis节点是否宕机呢？

## 2、状态监控
`Sentinel` 基于心跳机制监测服务状态，每隔1秒向集群的每个节点发送 ping 命令，并通过实例的响应结果来做出判断：
1. 主观下线（`sdown`）：如果某 `sentinel` 节点发现某 `Redis` 节点未在规定时间响应，则认为该节点主观下线。 
2. 客观下线(`odown`)：若超过指定数量（通过`quorum`设置）的 `sentinel` 都认为该节点主观下线，则该节点客观下线。`quorum` 值最好超过 `Sentinel` 节点数量的一半，`Sentinel`节点数量至少3台。

> 哨兵状态监控：`/img/02-哨兵/02-哨兵状态监控.jpg`。

## 3、自动故障切换

### 3.1、选举新的 master
一旦发现 `master` 故障，sentinel 需要在 slave 中选择一个作为新的 master，选择依据是这样的：
1. 首先会判断 `slave` 与 `master` 断开时间长短，如果超过`down-after-milliseconds * 10`则会排除该 `slave`； 
2. 然后判断 `slave` 节点的 `slave-priority` 值，越小优先级越高，如果是 0 则永不参与选举（默认所有 `slave` 都是1）； 
3. 如果 `slave-prority` 一样，则判断 `slave` 节点的 `offset` 值，越大说明数据越新，优先级越高； 
4. 最后是判断 `slave` 节点的 `run_id` 大小，越小优先级越高（`通过info server可以查看run_id`）；
 
当选出一个新的 `master` 后，该如何实现身份切换呢？ 大概分为两步：
- 在多个`sentinel`中选举一个`leader`
- 由`leader`执行`failover`

### 3.2、选举leader
首先，Sentinel 集群要选出一个执行`failover`的 Sentinel 节点，可以成为`leader`。要成为`leader`要满足两个条件： 
1. 最先获得超过半数的投票； 
2. 获得的投票数不小于`quorum`值；

一般来说，第一个确认 master 客观下线的 `Sentinel`服务 会立刻发起投票，一定会成为 `leader`。

### 3.3、failover 故障恢复的步骤
1. `sentinel`给备选的`slave1`节点发送`slaveof no one`命令，让该节点成为`master`；
2. `sentinel`给所有其它`slave`发送`slaveof 192.168.12.168 7002` 命令，让这些节点成为新`master`的`slave`节点，开始从新的`master`上同步数据。
3. 最后，`sentinel`将故障节点标记为 `slave`，当故障节点恢复后会接收到哨兵信号，执行`slaveof 192.168.12.168 7002`命令，成为`slave`：


# 二、搭建 Redis 哨兵集群
`Sentinel` 一般不是单节点服务，而是多个服务形成一个集群，避免自身不可用。

搭建哨兵集群使用文件见：`/file/02-redis哨兵集群`。
由于是在 MacOS 上演示，docker 存在部署 host 模式存在差异，所以容器的 ip 地址都是 172.0.0.n。如果是 Linux 环境，则需要使用 Linux 主机的 IP 地址。

## 步骤1，停止之前部署的 redis 主从集群：
```shell
$ ls  
docker-compose.yaml
$ docker ps
CONTAINER ID   IMAGE     COMMAND                   CREATED       STATUS       PORTS       NAMES
8dd9452b7cc5   redis     "redis-server --port…"   8 hours ago   Up 8 hours                r2
198606f86b32   redis     "redis-server --port…"   8 hours ago   Up 8 hours                r1
2c5d268cad32   redis     "redis-server --port…"   8 hours ago   Up 8 hours                r3
$ docker compose down
```

## 步骤2，准备 `redis` 哨兵 `sentinel.conf` 文件
```sentinel.conf
// 1、声明当前 sentinel 哨兵的 ip
sentinel announce-ip "宿主机IP地址"
// 2、sentinel monitor 表示监控配置，即配置哨兵监控的主从集群的信息。hmaster 主从集群的名称，自定义任意写；主节点 ip 和 端口；2 表示 quorum 值。
sentinel monitor hmaster 宿主机IP地址 7001 2
// 3、声明 master 节点超时多久后被标记下线，超时时间为5秒
sentinel down-after-milliseconds hmaster 5000
// 4、在第一次故障转移失败后多久再次重试。如果哨兵第一次进行故障恢复超过60秒，则会进行第2次故障恢复。
sentinel failover-timeout hmaster 60000
```

## 步骤3，准备 `DockerCompose` 一键部署 redis 集群 和 redis 哨兵集群
1、在宿主机中创建如下目录结构：
```shell
/root/redis 
  /s1/sentinel.conf
  /s2/sentinel.conf
  /s3/sentinel.conf
  docker-compose.yaml
```

2、直接运行命令，启动集群：
```shell
$ ls 
docker-compose.yaml	s1			s2			s3
$ docker compose up -d
WARN[0000] /Users/chen/Desktop/redis/docker-compose.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Running 6/6
 ✔ Container r1  Started                                                                                               0.4s 
 ✔ Container s2  Started                                                                                               0.4s 
 ✔ Container r3  Started                                                                                               0.4s 
 ✔ Container r2  Started                                                                                               0.4s 
 ✔ Container s1  Started                                                                                               0.4s 
 ✔ Container s3  Started                     
 
$ docker ps
CONTAINER ID   IMAGE     COMMAND                   CREATED              STATUS          PORTS     NAMES
bdd080840678   redis     "redis-sentinel /etc…"   About a minute ago   Up 59 seconds             s3
6875b822147f   redis     "redis-sentinel /etc…"   About a minute ago   Up 59 seconds             s2
7833e70dbd2f   redis     "redis-sentinel /etc…"   About a minute ago   Up 59 seconds             s1
848811940b38   redis     "redis-server --port…"   About a minute ago   Up 59 seconds             r3
f9e30f683281   redis     "redis-server --port…"   About a minute ago   Up 59 seconds             r1
5596ad6a776c   redis     "redis-server --port…"   About a minute ago   Up 59 seconds             r2
```

## 步骤4，测试
如下所示，目前 r1 容器作为 master：
```shell
$ docker exec -it r1 redis-cli -p 7001 
127.0.0.1:7001> info replication
# Replication
role:master
connected_slaves:2
slave0:ip=127.0.0.1,port=7003,state=online,offset=46378,lag=1
slave1:ip=127.0.0.1,port=7002,state=online,offset=46378,lag=0
master_failover_state:no-failover
master_replid:3a756d9e7bf48e62de1ef61ac09be9446e11faee
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:46378
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:46378
127.0.0.1:7001> exit
```

我们把 r1 暂停，演示一下当主节点故障时，哨兵是如何完成集群故障恢复（failover）的。

```shell
$ ls
docker-compose.yaml	s1			s2			s3

$ docker stop r1                
```
使用 `docker logs -f s1` 等查看各个 sentinel 哨兵的日志：
```shell
$ docker logs -f s1
1:X 07 Aug 2025 10:58:02.683 * oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
1:X 07 Aug 2025 10:58:02.683 * Redis version=8.2.0, bits=64, commit=00000000, modified=1, pid=1, just started
1:X 07 Aug 2025 10:58:02.683 * Configuration loaded
1:X 07 Aug 2025 10:58:02.683 * monotonic clock: POSIX clock_gettime
1:X 07 Aug 2025 10:58:02.684 * Running mode=sentinel, port=27001.
1:X 07 Aug 2025 10:58:02.688 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 10:58:02.688 * Sentinel ID is 254df4e89953d6fc36d3bcc0b7cdda31fe81581d
1:X 07 Aug 2025 10:58:02.688 # +monitor master hmaster 127.0.0.1 7001 quorum 2
1:X 07 Aug 2025 10:58:02.689 * +slave slave 127.0.0.1:7003 127.0.0.1 7003 @ hmaster 127.0.0.1 7001
1:X 07 Aug 2025 10:58:02.691 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 10:58:02.691 * +slave slave 127.0.0.1:7002 127.0.0.1 7002 @ hmaster 127.0.0.1 7001
1:X 07 Aug 2025 10:58:02.693 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 10:58:04.666 * +sentinel sentinel a108acc1064b9fe315e7cf7e39bb66e2d6ff1537 127.0.0.4 27002 @ hmaster 127.0.0.1 7001
1:X 07 Aug 2025 10:58:04.673 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 10:58:04.702 * +sentinel sentinel 73db2ae55d7e7b18491671dba3b943c6abfb3801 127.0.0.4 27003 @ hmaster 127.0.0.1 7001
1:X 07 Aug 2025 10:58:04.707 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 11:00:43.278 # +sdown master hmaster 127.0.0.1 7001
1:X 07 Aug 2025 11:00:43.304 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 11:00:43.304 # +new-epoch 1
1:X 07 Aug 2025 11:00:43.306 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 11:00:43.306 # +vote-for-leader a108acc1064b9fe315e7cf7e39bb66e2d6ff1537 1
1:X 07 Aug 2025 11:00:43.367 # +odown master hmaster 127.0.0.1 7001 #quorum 3/2
1:X 07 Aug 2025 11:00:43.367 * Next failover delay: I will not start a failover before Thu Aug  7 11:02:43 2025
1:X 07 Aug 2025 11:00:44.417 # +config-update-from sentinel a108acc1064b9fe315e7cf7e39bb66e2d6ff1537 127.0.0.4 27002 @ hmaster 127.0.0.1 7001
1:X 07 Aug 2025 11:00:44.417 # +switch-master hmaster 127.0.0.1 7001 127.0.0.1 7003
1:X 07 Aug 2025 11:00:44.417 * +slave slave 127.0.0.1:7002 127.0.0.1 7002 @ hmaster 127.0.0.1 7003
1:X 07 Aug 2025 11:00:44.417 * +slave slave 127.0.0.1:7001 127.0.0.1 7001 @ hmaster 127.0.0.1 7003
1:X 07 Aug 2025 11:00:44.423 * Sentinel new configuration saved on disk
1:X 07 Aug 2025 11:00:49.501 # +sdown slave 127.0.0.1:7001 127.0.0.1 7001 @ hmaster 127.0.0.1 7003
```

sentinel 故障恢复选举 r3 为新的 master
```shell
$ docker exec -it r3 redis-cli -p 7003
127.0.0.1:7003> info replication
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=7002,state=online,offset=47626,lag=0
master_failover_state:no-failover
master_replid:582d80561288fab9b1db922076d025b7bf7fa867
master_replid2:4e3ef1f50cabdced9dbea71b450988da97ce73c2
master_repl_offset:47626
second_repl_offset:30180
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:816
repl_backlog_histlen:46811
127.0.0.1:7003> exit

```