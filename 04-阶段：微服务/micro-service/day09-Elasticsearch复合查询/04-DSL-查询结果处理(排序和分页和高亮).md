# 一、DSL查询 - 结果排序
`Elasticsearch` 支持对搜素结果排序，默认是根据相关度算分（`_score`）来排序，也可以指定字段排序。
分词字段 `type: text` 无法排序，能参与排序字段类型有：`keyword`类型、数值类型、地理坐标类型、日期类型等。

详细说明可以参考官方文档：<https://www.elastic.co/guide/en/elasticsearch/reference/7.12/sort-search-results.html>

对 DSL 查询结果排序：
```shell
GET /indexName/_search
{
  # 查询条件
  "query": {
    "match_all": {}
  },
  # sort 排序是一个数组，根据多个排序字段进行排序。先根据第一个字段排序，如果根据第一个字段进行排序时值相同，则根据第2个字段进行排序。以此类推。
  "sort": [
    {
      "排序字段": {
        "order 关键字": "排序方式 asc 或 desc"
      }
    }
  ]
}
```

实现案例：搜索商品，按照销量排序，销量一样则按照价格升序：
```shell
GET /items/_search
{
  "query": {
    "match_all": {}
  },
#  "sort": [
#    {
#      "sold": {
#        "order": "desc"
#      }
#    },
#    {
#      "price": {
#        "order": "asc"
#      }
#    }
#  ]
  # 简单写方式
  "sort": [
    {
      "sold": "desc",
    },
    {
      "price": "asc"
    }
  ]
}
```


# 二、DSL查询 - 结果分页
Elasticsearch 默认情况下只返回 top10 的数据，如果要查询更多数据就需要修改分页参数了。Elasticsearch 中通过修改`from`、`size`参数来控制要返回的分页结果：

- `from`：从第几个文档开始
- `size`：总共查询几个文档

类似于 `mysql` 中的`limit ?, ?`

官方文档如下：<https://www.elastic.co/guide/en/elasticsearch/reference/7.12/paginate-search-results.html>

实现案例：搜索商品，查询出销量排名前10的商品，销量一样时按照价格升序：
```shell
GET /items/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "sold": {
        "order": "desc"
      },
      "price": {
        "order": "asc"
      }
    }
  ],
  "from": 0,
  "size": 10
}
```

# 三、es 深度分页问题
`Elasticsearch` 存在深度分页问题，传统数据库中也存在这个问题，只不过 ES 表现更明显，因为 ES 存储数据的规模比传统数据库多得多。

`Elasticsearch` 的数据一般会采用`分片存储`，也就是把一个索引库中的数据分成N份，存储到不同节点上。查询数据时需要汇总各个分片的数据。
这种存储方式比较有利于数据扩展，但给分页带来了一些麻烦。

- 案例：比如一个索引库中有 100000 条数据，分别存储到 4 个分片，每个分片 25000 条数据。现在每页查询10条，查询第 100 页数据。那么分页查询的条件如下：
```shell
GET /items/_search
{
  "from": 990, // 从第990条开始查询
  "size": 10, // 每页查询10条
  "sort": [
    {
      "price": "asc"
    }
  ]
}
```
 
- 案例分析：查询第 100 页数据的范围为 第990~1000名 的数据，该如何实现呢？
从实现思路来分析，肯定是将所有数据排序，找出前 1000 名，截取其中的 990~1000 的部分。但问题来了，我们如何才能找到所有数据中的前 1000 名呢 ？
要知道每一片的数据都不一样，第1片上的第900~1000，在另1个节点上并不一定依然是900~1000名。

- 实现思路：在每一个分片上都找出排名前 1000 的数据，然后汇总到一起，重新排序，才能找出整个索引库中真正的前 1000 名，然后从中截取 990~1000 的数据即可。

> 深度分页问题
> 
> 试想一下，假如我们现在要查询的是第999页数据呢，是不是要找第9990~10000的数据，那岂不是需要把每个分片中的前10000名数据都查询出来，汇总在一起，在内存中排序？
> 如果查询的分页深度更深呢，需要一次检索的数据岂不是更多？
>
> 查询的页码越深，从每个分片查询的数据量就越大，汇总数据过多，对内存和CPU会产生非常大的压力，分页查询效率极差。
> 深度分页的问题在传统数据库中也存在，只不过在 es 中更明显。

## es 解决方案

深度分页问题解决方案都是类似的。针对深度分页，`Elasticsearch` 提供了两种解决方案：

- `search after`：分页时需要排序，原理是从上一次的`排序值`开始，查询下一页数据。（即记录上一次查询的最后一条记录的排序值，作为查询条件携带到下一次查询。）官方推荐使用的方式。
- `scroll`：原理将排序后的文档id形成快照，保存下来，基于快照做分页。官方已经不推荐使用。

详细可查看官方文档 <https://www.elastic.co/guide/en/elasticsearch/reference/7.12/paginate-search-results.html>

- `search after` 模式分页查询:
  1. 优点：没有查询上限，支持深度分页；
  2. 缺点：智能向后逐页查询，不能随机翻页；
  3. 场景：数据迁移，手机滚动查询；

Elasticsearch 对传统 from-size 分页查询做了上限限制，即 from+size 的值 < 10000。

## 总结
大多数情况下，我们采用普通分页就可以了。查看百度、京东等网站，会发现其分页都有限制。例如百度最多支持77页，每页不足20条。京东最多100页，每页最多60条。
因此，一般我们采用限制分页深度的方式即可，无需实现深度分页。


# 四、结果高亮显示
* 高亮显示：就是在搜索结果中把搜索关键字突出显示

我们在百度，京东搜索时，关键字会变成红色，比较醒目，这叫高亮显示。观察页面源码，你会发现两件事情：
- 高亮词条都被加了`em`标签；
- `em`标签都添加了红色样式；

## 4.1、高亮显示原理
前端编写页面代码时是不知道要展示的数据，只是实现页面样式结构。
而使用 `Elasticsearch` 做分词搜索时，后端是知道哪些词条需要高亮的。因此词条的高亮标签是由服务端在返回数据中加上的。
实现高亮的思路就是：
- 用户输入搜索关键字搜索数据
- 服务端根据搜索关键字到 elasticsearch 搜索，并给搜索结果中的关键字词条添加`html`标签
- 前端提前给约定好的`html`标签添加`CSS`样式


- 为什么 es 可以在搜索结果中添加标签？
ES倒排索引进行数据存储时，会把文档分词形成词条列表，该列表中记录：词条：文档ID+词条在文档中的位置。从而实现在返回数据中添加`html`标签。


## 4.2、es 实现高亮显示
`Elasticsearch` 已经提供了给搜索关键字加标签的语法，基本语法如下：
```shell
GET /{索引库名}/_search
{
  "query": {
    "match": { 
      "搜索字段": "搜索关键字"
    }
  },
  "highlight": {
    "fields": {
      "高亮字段名称": {
        "pre_tags": "<em>",   # 高亮字段的前置标签
        "post_tags": "</em>"  # 高亮字段的后置标签
      }
    }
  }
}
```

**注意**：
- 搜索必须有查询条件，而且是全文检索类型的查询条件，例如`match`
- 参与高亮的字段必须是`text`类型的字段
- 默认情况下参与高亮的字段要与搜索字段一致，除非添加：`required_field_match=false`

如下案例：查询 items 索引库中，文档的 name 字段匹配 "脱脂牛奶" 的所有文档，并把搜索词条高亮：
```shell
GET /items/_search
{
  "query": {
    "match": {
      "name": "脱脂牛奶"
    }
  },
  "highlight": {
    "fields": {
      "name": {
        "pre_tags": "<em>",
        "post_tags": "</em>"
      }
    }
  }
}

# 查询结果
{
  "took" : 15,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 10000,
      "relation" : "gte"
    },
    "max_score" : 22.29004,
    "hits" : [
      {
        "_index" : "items",
        "_type" : "_doc",
        "_id" : "12179607155",
        "_score" : 22.29004,
        "_source" : {   # _source 是指原数据，不可改变
          "id" : "12179607155",
          "name" : """【沃尔玛】艾思达/ASDA纯牛奶 灭菌乳 早餐奶 牛奶 英国 进口 全脂\脱脂\部分脱脂 部分脱脂牛奶 1L*6""",
          "price" : 96300,
          "stock" : 10000,
          "image" : "https://m.360buyimg.com/mobilecms/s720x720_jfs/t5977/320/3555243042/134144/38730483/59546920Nbe3ddc70.jpg!q70.jpg.webp",
          "category" : "牛奶",
          "brand" : "ASDA",
          "sold" : 0,
          "commentCount" : 0,
          "isAD" : false,
          "updateTime" : 1556640000000
        },
        "highlight" : {   # 高亮数据
          "name" : [
            """【沃尔玛】艾思达/ASDA纯<em>牛</em><em>奶</em> 灭菌乳 早餐<em>奶</em> <em>牛</em><em>奶</em> 英国 进口 全<em>脂</em>\<em>脱</em><em>脂</em>\部分<em>脱</em><em>脂</em> 部分<em>脱</em><em>脂</em><em>牛</em><em>奶</em> 1L*6"""
          ]
        }
      }
    ]
  }
}
```


# 五、DSL 查询总结
查询的DSL是一个大的JSON对象，包含下列属性：

- `query`：查询条件
- `from`和`size`：分页条件
- `sort`：排序条件
- `highlight`：高亮条件

以上条件查询案例总结：
```shell
GET /items/_search
{
  "query": {
    "match": {
      "name": "华为"
    }
  },
  # 分页查询，查询第1页，每页20条数据
  "from": 0,  
  "size": 20,
  # 降序排序
  "sort": [ 
    {
      "price": {
        "order": "desc"
      }
    }
  ],
  #  把查询字段高亮显示
  "highlight": {  
    "fields": {
      "name": {
        "pre_tags": "<em>",
        "post_tags": "</em>"
      }
    }
  }
}
```