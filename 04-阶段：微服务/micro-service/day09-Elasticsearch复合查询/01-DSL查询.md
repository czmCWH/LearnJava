前面学习了 ES 索引库文档的 增删改查 操作，这些操作都是基于文档ID进行的，这样并不能满足所有业务场景要求。

# 一、DSL查询
`Elasticsearch` 提供了 DSL(Domain Specific Lanquage) 查询来实现复杂查询，`就是以 JSON 格式来定义查询条件`。查询格式如下所示：
官网：<https://www.elastic.co/guide/en/elasticsearch/reference/7.13/query-dsl.html>
```shell
GET /_search
{
  "query": { 
    "bool": { 
      "must": [
        { "match": { "title":   "Search"        }},
        { "match": { "content": "Elasticsearch" }}
      ],
      "filter": [ 
        { "term":  { "status": "published" }},
        { "range": { "publish_date": { "gte": "2015-01-01" }}}
      ]
    }
  }
}
```

- `Elasticsearch` 的查询可以分为两大类：
  1. `叶子查询`（Leaf query clauses）：一般是在特定的字段里查询特定值，属于简单查询，很少单独使用。 
  2. `复合查询`（Compound query clauses）：以逻辑方式组合多个叶子查询 或者 更改叶子查询的行为方式。

在查询以后，还可以对查询的结果做处理，包括:
* 排序：按照1个或多个字段值做排序； 
* 分页：根据 from 和 size 做分页，类似MySQL；
* 高亮：对搜索结果中的关键字添加特殊样式，使其更加醒目；
* 聚合：对搜索结果做数据统计以形成报表；

## 1、DSL 查询快速入门
基于 DSL 的查询语法如下：
```shell
# indexName 索引库名称；_search 请求路径，固定值
GET /indexName/_search
{
  "query": {
    "查询类型": {
      "查询条件": "条件值"
    }
  }
}

# 例如：在 Kibana -> Dev Tools 实现查询
# 1、查询 indexName 索引库中所有数据，实际上只会查询出10条数据
GET /items/_search
{
  "query": {
    "match_all": {  #  match_all 查询类型，表示匹配所有，即查询所有数据
    }
  }
}

# 查询结果：
{
  "took" : 35,  # 请求耗时
  "timed_out" : false,  # 是否超时
  "_shards" : {   # _shards 分片，es 是支持集群存储数据的，可以把数据拆分到不同的节点保存，形成不同的分片。
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {  # 本次查询的数据
    "total" : {
      "value" : 10000,    # 当前查询的总条数，es 内部默认限制单次允许查询的最大数据不能大于1万条
      "relation" : "gte"  # gte 表示查询条数大于或等于1万条
    },
    "max_score" : 1.0,    # 得分，所有结果中得分最高的文档的相关性算分
    "hits" : [  # 本次查询命中的数据，默认最多只返回10条数据
      {
        "_index" : "items",   # 索引库名称
        "_type" : "_doc",   # 文档
        "_id" : "317578",
        "_score" : 1.0,
        "_source" : {   # 原始文档数据
          "id" : "317578",
          "name" : "RIMOWA 21寸托运箱拉杆箱 SALSA AIR系列果绿色 820.70.36.4",
          "price" : 28900,
          "stock" : 10000,
          "image" : "https://m.360buyimg.com/mobilecms/s720x720_jfs/t6934/364/1195375010/84676/e9f2c55f/597ece38N0ddcbc77.jpg!q70.jpg.webp",
          "category" : "拉杆箱",
          "brand" : "RIMOWA",
          "sold" : 0,
          "commentCount" : 0,
          "isAD" : false,
          "updateTime" : 1683342377000
        }
      },
      // ... 
    ]
  }
}


# 2、查询 items 索引库中所有文档总数
GET /items/_count

# 3、在 items 索引库中根据文档ID查询
Get /items/_doc/613358
```