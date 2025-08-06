# 一、DSL查询
`Elasticsearch` 提供了 DSL(Domain Specific Lanquage) 查询，`就是以 JSON 格式来定义查询条件`。查询格式如下所示：
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

`Elasticsearch` 的查询可以分为两大类：

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
GET /indexName/_search
{
  "query": {
    "查询条件": {
      "查询条件": "条件值"
    }
  }
}

# 例如：
# 1、查询 indexName 索引库中所有数据，实际上只会查询出10条数据
GET /indexName/_search
{
  "query": {
    "match_all": {
    }
  }
}
# 2、查询 items 索引库中所有文档总数
GET /items/_count

# 3、在 items 索引库中根据文档ID查询
Get /items/_doc/613358
```