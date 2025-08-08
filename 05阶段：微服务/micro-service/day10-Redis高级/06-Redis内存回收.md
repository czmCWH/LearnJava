# Redis 内存回收
`Redis` 之所以性能强，最主要的原因就是基于内存存储。然而单节点的 `Redis` 其内存大小不宜过大，会影响持久化 或 主从同步性能。
可以通过修改 `redis.conf` 文件，添加下面的配置来配置 `Redis` 的最大内存：

```Properties
maxmemory 1gb
```

当内存达到上限，就无法存储更多数据了。因此，`Redis` 内部会有两套内存回收的策略：
1. 过期key处理；
2. 内存淘汰策略；


# 一、Redis 内存过期处理
Redis 中通过`expire`命令，给KEY设置`TTL`（过期时间），代码实现如下所示：

```shell
# 1、连接 r1 容器启动 Redis 客户端，以集群模式连接本容器内的 7001 端口上的 Redis 服务
$ docker exec -it r1 redis-cli -c -p 7001 

127.0.0.1:7001> set name jack
-> Redirected to slot [5798] located at 127.0.0.1:7002
OK
# 2、设置 name 的过期时间为 5 秒
127.0.0.1:7002> expire name 5
(integer) 1
127.0.0.1:7002> get name
"jack"
127.0.0.1:7002> get name
(nil)

# 3、写入一条数据，并设置过期时间 5 秒
127.0.0.1:7002> set name java EX 5
OK
127.0.0.1:7002> get name
"java"
127.0.0.1:7002> get name
(nil)
```

当key的TTL到期以后，再次访问name返回的是nil，说明这个key已经不存在了，对应的内存也得到释放。 从而起到内存回收的目的。


## 1、过期时间记录方式
- Redis 如何判断一个KEY是否过期呢？
- Redis 又是何时删除过期KEY的呢？

`Redis` 的本身是键值型数据库，其所有数据都存在一个`redisDB`的结构体中，其中包含两个 `HashTable`(哈希表)：
1. `dict`：保存 `Redis` 中所有的键值对； 
2. `expires`：保存 `Redis` 中所有的设置了过期时间的 `KEY` 及其到期时间(写入时间+TTL)；

## 2、过期 key 删除策略
`Redis` 并不会实时监测 `key` 的过期时间，在 `key` 过期后立刻删除。而是采用两种延迟删除的策略：
* `惰性删除`：当有命令需要操作一个`key`的时候，检查该`key`的存活时间，如果已经过期才执行删除。
* `周期删除`：通过一个定时任务，周期性的抽样部分有`TTL`的 `key`，如果过期则执行删除。

`周期删除` 的定时任务执行周期有两种：
1. `SLOW模式`：默认执行频率为每秒10次，但每次执行时长不能超过25ms，受`server.hz`参数影响。
2. `FAST模式`：频率不固定，跟随`Redis`内部`I0`事件循环执行。两次任务之间间隔不低于2ms，执行时长不超过1ms。


# 二、内存淘汰策略
对于某些特别依赖于`Redis`的项目而言，仅仅依靠过期`KEY`清理是不够的，内存可能很快就达到上限。因此`Redis`允许设置内存告警阈值，
当内存使用达到阈值时就会主动挑选部分`KEY`删除以释放更多内存。这叫做 `内存淘汰` 机制。

1. `内存淘汰`：就是当Redis内存使用达到设置的阈值时，Redis主动挑选部分key删除以释放更多内存的流程。
2. `内存淘汰策略`：Redis会在每次处理客户端命令时都会对内存使用情况做判断，如果必要则执行内存淘汰。

`Redis` 支持8种不同的内存淘汰策略：
- `noeviction`： 不淘汰任何key，但是内存满时不允许写入新数据，默认就是这种策略。
- `volatile-ttl`： 对设置了TTL的key，比较key的剩余TTL值，TTL越小越先被淘汰。
- `allkeys-random`：对全体key ，随机进行淘汰。也就是直接从`db->dict`中随机挑选。
- `volatile-random`：对设置了TTL的key ，随机进行淘汰。也就是从`db->expires`中随机挑选。
- `allkeys-lru`： 对全体key，基于LRU算法进行淘汰。
- `volatile-lru`： 对设置了TTL的key，基于LRU算法进行淘汰。
- `allkeys-lfu`： 对全体key，基于LFU算法进行淘汰。
- `volatile-lfu`： 对设置了TTL的key，基于LFI算法进行淘汰。

比较容易混淆的有两个算法：
- `LRU`（Least Recently Used），最近最少未使用。用当前时间减去最后一次访问时间，这个值越大则淘汰优先级越高。
- `LFU`（Least Frequently Used），最少频率使用。会统计每个key的访问频率，值越小淘汰优先级越高。

