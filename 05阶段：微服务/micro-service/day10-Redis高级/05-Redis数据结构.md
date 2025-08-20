# Redis 常见数据结构
我们常用的 `Redis` 数据类型有5种，分别是：
- String
- List
- Set 
- SortedSet
- Hash

还有一些高级数据类型，比如 `Bitmap`、`HyperLogLog`、`GEO` 等，其底层都是基于上述5种基本数据类型。因此在Redis的源码中，其实只有5种数据类型。

## 1、RedisObject - redis 对象
`Redis` 中的任意数据类型的键和值都会被封装为一个 `RedisObject`，也叫做 `Redis` 对象。
`RedisObject` 格式，它是一种结构体，C语言中的一种结构，可以理解为 `Java` 中的类。

```C
typedef struct redisobject {
    unsigned type:4;    // 表示 Redis 对象的数据类型（如：String、List、Set、zset、hash），占4个bit位。
    unsigned encoding:4;    // 数据类型的低层编码方式，共有12种，占4个bit位。
    unsigned lru:LRU_BITS;  // LRU_BITS为24，记录当前对象最后一次被访问的时间，占24个bit位，便于判断空闲时间太久的key
    int refcount;   // 占4byte，对象引用计数器，如果值为0则说明此对象无人引用，可以被回收
    void *ptr:      // 指针，存放内存地址，指向存放实际数据的空间
} robj;
```

Redis 中会根据存储的数据类型不同，选择不同的编码方式，共包含12种不同类型：`/img/04-Redis数据结构/02-Redis数据类型编码方式.jpg`

| 编号 | 编码方式                | 说明              |
| ---- | ----------------------- |-----------------|
| 0    | OBJ_ENCODING_RAW        | raw编码动态字符串      |
| 1    | OBJ_ENCODING_INT        | long类型的整数的字符串   |
| 2    | OBJ_ENCODING_HT         | hash表（字典也叫dict） |
| 3    | OBJ_ENCODING_ZIPMAP     | 已废弃             |
| 4    | OBJ_ENCODING_LINKEDLIST | 双端链表            |
| 5    | OBJ_ENCODING_ZIPLIST    | 压缩列表            |
| 6    | OBJ_ENCODING_INTSET     | 整数集合            |
| 7    | OBJ_ENCODING_SKIPLIST   | 跳表              |
| 8    | OBJ_ENCODING_EMBSTR     | embstr编码的动态字符串  |
| 9    | OBJ_ENCODING_QUICKLIST  | 快速列表            |
| 10   | OBJ_ENCODING_STREAM     | Stream流         |
| 11   | OBJ_ENCODING_LISTPACK   | 紧凑列表            |

Redis 中会根据存储的数据类型不同，选择不同的编码方式。Redis 中的常见5种 数据类型的 底层数据结构 和 编码方式 如下：

| 数据类型       | 编码方式                                              |
|------------| ----------------------------------------------------- |
| OBJ_STRING | int、embstr、raw                                      |
| OBJ_LIST       | LinkedList和ZipList(3.2以前)、QuickList（3.2以后）    |
| OBJ_SET        | intset、HT                                            |
| OBJ_ZSET       | ZipList（7.0以前）、Listpack（7.0以后）、HT、SkipList |
| OBJ_HASH       | ZipList（7.0以前）、Listpack（7.0以后）、HT           |

## 2、SkipList - 跳表编码方式

SkipList（跳表）首先是链表，但与传统链表相比有几点差异： 
- 元素按照升序排列存储
- 节点可能包含多个指针，指针跨度不同。

传统链表只有指向前后元素的指针，因此只能顺序依次访问。如果查找的元素在链表中间，查询的效率会比较低。
而 SkipList 则不同，它内部包含跨度不同的多级指针，可以让我们跳跃查找链表中间的元素，效率非常高。
跳表结构见图：`/img/04-Redis数据结构/03-跳表结构.jpg`。

SkipList（跳表）的特点：
- 跳跃表是一个有序的双向链表。
- 每个节点都可以包含多层指针，层数是1到32之间的随机数。
- 不同层指针到下一个节点的跨度不同，层级越高，跨度越大。
- 增删改查效率与红黑树基本一致，实现却更简单。但空间复杂度更高。


## 3、SortedSet 类型
SortedSet 数据结构的特点是：
- 每组数据都包含 score 和 member； 
- member 唯一；
- 可根据 score 排序；

### Redis的`SortedSet`底层的数据结构是怎样的？

SortedSet 是有序集合，底层的存储的每个数据都包含 member 和 score 两个值。score是得分，member 则是字符串值。SortedSet 会根据每个 member 的 score 值排序，形成有序集合。

SortedSet 支持的操作很多，比如：
- 根据 member 查询 score 值；
  - SortedSet 底层是基于 HashTable 来实现的，以 member 为键，score 为值。
- 按照 score 值升序或降序查询 member；
  - SortedSet是基于 跳表 实现的。


