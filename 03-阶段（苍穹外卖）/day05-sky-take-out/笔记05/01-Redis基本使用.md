# 一、Redis
Redis 是一个基于 内存 的 `key-value`结构 数据库。（MySql 是基于 磁盘 的）。
Redis 存储的 value 类型比较丰富，也被称为结构化的 非关系型数据库(NOSQL)。
特点：
1、由于它基于内存存储，所以读写性能高；
2、适合存储热点数据(热点商品、资讯、新闻)；
3、企业应用广泛；
官网：https://redis.io
	 https://www.redis.net.cn

> Redis 官方提供的数据是可以达到 100000+ 的QPS（每秒内查询次数)。

## Redis 应用场景
* 缓存
* 任务队列（基本不用 redis 做，而用其它中间件）
* 消息队列（基本不用 redis 做，而用其它中间件）
* 分布式锁

## MacOS 上安装 Redis

> Redis服务默认端口号为 6379

```shell
# 通过 Homebrew 安装 Redis
$ brew install redis

# 临时启动‌（关闭终端即停止）
$ redis-server

# 停止服务
$ 
```

通过修改 `redis.conf` 文件设置 Redis 服务密码：`requirepass 123456`， 修改密码后重启 `Redis`，再次连接Redis时，需加上密码，否则连接失败。

### Redis 客户端图形工具

`Another-Redis-Desktop-Manager` 是一款 Redis 可视化管理工具，兼容 Windows、Mac、Linux。


# 二、Redis 常用数据类型【重点】

* 字符串(`string`)：普通字符串，`Redis` 中最简单的数据类型；（重点！！！）

* 哈希(`hash`)：也叫散列，类似于 `Java` 中的 `HashMap` 结构；

* 列表(`list`)：按照插入顺序排序，可以有重复元素，类似于 Java 中的 `LinkedList`；

* 集合(`set`)：无序集合，没有重复元素，类似于 Java 中的 Hashset；

* 有序集合(`sorted set` 或 `zset`)：集合中每个元素关联一个分数(score)，根据分数升序排序，没有重复元素；


# 三、Redis常用命令【难点】
命令：存值、取值、删除值。

> Redis 中文网有详细的命令教程解释。

## 1、字符串(string) 类型常用命令

	SET key value 		设置指定key的值
	GET key 			获取指定key的值
	SETEX key seconds value  设置指定key的值，并将 key 的过期时间设为 seconds 秒
	SETNX key value 	只有在 key 不存在时设置 key 的值

## 2、哈希(hash) 类型常用命令
Redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象，常用命令：

	HSET key field value 	将哈希表 key 中的字段 field 的值设为 value
	HGET key field 			获取存储在哈希表中指定字段的值
	HDEL key field			删除存储在哈希表中的指定字段
	HKEYS key				获取哈希表中所有字段
	HVALS key  				获取哈希表中所有值

```
# 给 user 添加字段-值(field-value)
hset user name zhangsan
hset user age 18
hset user phone 12312311231

hget user name	# 获取 user 的 name 字段的值

hkeys user		# 获取 user 的所有 field字段
hvals user		# 获取 user 的所有 value值
hgetall user	# 获取 user 的所有 field-value
```

## 3、列表(list) 类型常用命令

Redis 列表(list) 是简单的字符串列表，按照插入顺序排序，常用命令：

	LPUSH key value1 [value2]	将一个或多个值插入到列表头部
	LRANGE key start stop		获取列表指定范围内的元素
	RPOP key 					删除并获取列表最后一个元素
	LLEN key 					获取列表长度


```
lpush list01 sing dance rap ball	# 向 list01 列表的头部添加元素
rpush list01 java go python			# 向 list01 列表的尾部添加元素

lrange list01 0 3  		# 获取 list01列表中 0～3 索引之间的元素
```

## 4、集合(set) 类型常用命令
Redis set 是 string 类型的无序集合。集合成员是唯一的，集合中不能出现重复的数据，常用命令:

	SADD key member1 [member2]  	向集合添加一个或多个成员
	SMEMBERS key   					返回集合中的所有成员
	SCARD key 						获取集合的成员数
	SINTER key1 [key2]				返回给定所有集合的交集
	SUNION key1 [key2]				返回所有给定集合的并集
	SREM key member1 [member2]		删除集合中一个或多个成员


```
sadd A go java 		# 向集合 A 中存入 go、java
sddd B java python	# 向集合 A 中存入 java python

smembers A 			# 查看集合A中的元素
scard A 			# 查看集合A中元素个数

sinter A B 			# 集合A和集合B的交集为 java

sunion A B  		# 集合 A、B 的并集为 go java python

sdiff A B 			# 集合A与集合B的差集为 go

```

## 5、有序集合(即 sorted set 或 zset) 类型常用命令
Redis 有序集合是 string 类型元素的集合，且不允许有重复成员。每个元素都会关联一个 double 类型的分数，集合内成员根据此分数进行排序。常用命令：

	ZADD key score1 member1 [score2 member2]		向有序集合添加一个或多个成员
	ZRANGE key start stop [WITHSCORES]		通过索引区间返回有序集合中指定区间内的成员
	ZINCRBY key increment member 			有序集合中对指定成员的分数加上增量 increment
	ZREM key member [member ...] 			移除有序集合中的一个或多个成员


```
zadd C 99 zhangsan 88 lisi 100 wangwu	# 向有序集合 C 中添加 3个成员

zrange C 0 -1		# 查询有序集合 C 中所有成员
zrange C 0 -1 withscores 	# 查询有序集合 C 中所有成员和分数

zincrby C 10 lisi   # 给有序集合 C 的 lisi 成员的分数增加 10

zrem C lisi 		# 删除有序集合 C 中的 lisi 成员
```


## 6、Redis 的通用命令
Redis 的通用命令是不分数据类型的，都可以使用的命令：

	KEYS pattern		查找所有符合给定模式( pattern)的 key
	EXISTs key  		检查给定 key 是否存在
	TYPE key 			返回 key 所储存的值的类型
	DEL key  			该命令用于在 key 存在时删除 key
	rename key 新key  	重命名
	ping				测试连接是否正常
	expire key 秒数		设置这个 key 在缓存中的存活时间
	ttl key  			返回给定 key 的剩余生存时间(TTL, time to live)，以秒为单位。若返回值为 -1：永不过期；若返回值为 -2：已过期 或 不存在
	


```
keys * 		# 查询所有的 key（注意：实际开发不能这样些，会导致性能暴增）
keys A*		# 查询以 A 开头的 key

exists A 	# 是否存在 A

type A 		# 查询 A 的存储值的类型

rename A Anew	# 重新命名

ttl A 		# 查看A的有效时间
```

 


