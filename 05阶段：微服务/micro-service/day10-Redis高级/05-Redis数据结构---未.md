# Redis 常见数据结构
我们常用的 `Redis` 数据类型有5种，分别是：
- String
- List
- Set
- SortedSet
- Hash

还有一些高级数据类型，比如 `Bitmap`、`HyperLogLog`、`GEO` 等，其底层都是基于上述5种基本数据类型。因此在Redis的源码中，其实只有5种数据类型。

## 1、RedisObject
`Redis` 中的任意数据类型的键和值都会被封装为一个 `RedisObject`，也叫做 `Redis` 对象。
`RedisObject` 格式，它是一种结构体，C语言中的一种结构，可以理解为 `Java` 中的类。



太难了！学习地址：<https://www.bilibili.com/video/BV1S142197x7?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=149>

