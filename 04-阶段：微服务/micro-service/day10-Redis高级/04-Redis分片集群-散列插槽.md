# 一、散列插槽
数据要分片存储到不同的 `Redis` 节点，肯定需要有分片的依据，这样下次查询的时候才能知道去哪个节点查询。
很多数据分片都会采用一致性 hash 算法。而 `Redis` 则是利用散列插槽（`hash slot`）的方式实现数据分片。

<https://redis.io/docs/management/scaling/#redis-cluster-101>

在 `Redis` 集群中，共有 16384 个`hash slots`，集群中的每一个 `master` 节点都会分配一定数量的`hash slots`。具体的分配在集群创建时就已经指定了，如下所示：
```bash
>>> Trying to optimize slaves allocation for anti-affinity
[WARNING] Some slaves are in the same host as their master
M: 79a51f5486cb0f63a2dd6034f33fac32d4ec6975 127.0.0.1:7001
   slots:[0-5460] (5461 slots) master          # 给 7001 master 节点分配的插槽为： [0-5460]，一共有 5461 个。
M: 72ee8d7fd9ff3463416cf76e4035764d9332bb01 127.0.0.1:7002
   slots:[5461-10922] (5462 slots) master
M: 600d0d8c94304869bcc54664368581a52b9071c6 127.0.0.1:7003
   slots:[10923-16383] (5461 slots) master
```

## `hash slot`方式实现数据分片
Redis 数据不是与节点绑定，而是与插槽 slot 绑定。当我们读写数据时，`Redis` 基于`CRC16` 算法对`key`做`hash`运算，得到的结果与`16384`取余，
就计算出了这个`key`的`slot`值，这个值的范围在 0~16383 中。然后到`slot`所在的 `Redis` 节点执行读写操作。

`redis`在计算`key`的`hash`值是不一定是根据整个`key`计算，分两种情况：
1. 当`key`中包含`{}`时，根据`{}`之间的字符串计算`hash slot`；
2. 当`key`中不包含`{}`时，则根据整个key字符串计算`hash slot`；

例如：
- key是`user`，则根据`user`来计算hash slot；
- key是`user:{age}`，则根据`age`来计算hash slot；

> 可以通过设置 key 命名使`{}`内的内容一致，保证数据存储在同一片区域，如：`{user}:name、{user}:age、{user}:height` 使得它们存储在同一片节点。

```shell
# 1、先进入任意节点容器
$ cd redis-cluster
redis-cluster $ docker exec -it r1 bash

# 2、查看集群信息
$ root@docker-desktop:/data# redis-cli -p 7001 cluster nodes
ebe7549f2003dd8808a97e25ac76dc90cfc1d9ca 127.0.0.1:7005@17005 slave d284537b83e8e5f510796dd804a25a3beaa55480 0 1754623542546 3 connected
2f564a37c8dc8ea18e94b8d5e65cc5abf4106a15 127.0.0.1:7002@17002 master - 0 1754623541508 2 connected 5461-10922
49c1ec44320a057a94e06f1b9bc22962bfa675a5 127.0.0.1:7004@17004 slave 2f564a37c8dc8ea18e94b8d5e65cc5abf4106a15 0 1754623542000 2 connected
d284537b83e8e5f510796dd804a25a3beaa55480 127.0.0.1:7003@17003 master - 0 1754623539452 3 connected 10923-16383
f1b208f0cac25e6b3799833bb24f45eb043be8f3 127.0.0.1:7001@17001 myself,master - 0 0 1 connected 0-5460
e0910b472b106d59caa96a318a5774a7399e7069 127.0.0.1:7006@17006 slave f1b208f0cac25e6b3799833bb24f45eb043be8f3 0 1754623543602 1 connected

# 3、直接连接 7001 节点，存储数据会报错
root@docker-desktop:/data# redis-cli -p 7001
127.0.0.1:7001> 

# 直接存储 key 为 user，值为 jack 的数据，⚠️ 报错！！
# 得出`user`这个`key`的`hash slot` 是`5474`，而`5474`是在`7002`节点，不能在`7001`上写入！！
127.0.0.1:7001> set user jack
(error) MOVED 5474 127.0.0.1:7002
127.0.0.1:7001> exit

# 4、正确存储数据需以集群的方式连接节点，需添加 -c 参数，如下：redis-cli -c -p 7001，表示通过7001连接集群
root@docker-desktop:/data# redis-cli -c -p 7001
127.0.0.1:7001> set user jack
-> Redirected to slot [5474] located at 127.0.0.1:7002
OK
127.0.0.1:7002> get user
"jack"
```




