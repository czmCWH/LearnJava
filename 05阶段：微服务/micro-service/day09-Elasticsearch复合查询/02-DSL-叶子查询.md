# 一、叶子查询
叶子查询的类型也可以做进一步细分，具体可看官网 <https://www.elastic.co/guide/en/elasticsearch/reference/7.12/query-dsl.html>。

- 1、全文检索查询（`Full Text Queries`) ：利用分词器对用户输入搜索条件先分词，得到词条，然后再去倒排索引搜索词条。例如：
    - `match`：匹配一个字段查询。
    - `multi_match`：与 match 查询类似，匹配多个字段查询，参与查询字段越多，查询性能越差。
    - 注意：match 和 multi_match 查询的字段都是可分词的字段。
  
- 2、精确查询（`Term-level queries`）：不对用户输入搜索条件分词，根据字段内容精确值匹配。⚠️ 但只能查找 type为：`keyword、数值、日期、boolean` 类型的字段。例如：
    - `ids`：根据 文档id集合 精确查询；
    - `term`：根据词条精确匹配查询；
    - `range`：根据数值范围 or 日期范围 查询；
  
- 3、地理坐标查询：用于搜索地理位置，搜索方式很多，例如：
    - `geo_bounding_box`：按矩形搜索
    - `geo_distance`：按点和半径搜索

## 1、全文检索查询
```shell
# 1、match 查询语法
GET /{索引库名}/_search
{
  "query": {
    "match": {
      "字段名": "搜索条件"
    }
  }
}

# 2、multi_match 查询语法
GET /{索引库名}/_search
{
  "query": {
    "multi_match": {
      "query": "搜索条件",
      "fields": ["字段1", "字段2"]
    }
  }
}
```

* 例如，在 `kibana 【Dev Tools】` 测试全文查询：
```shell
# match 全文查询
# 如下示例：在 items 索引中搜索 name字段 匹配 "脱脂牛奶" 的文档。
GET /items/_search
{
  "query": {
    "match": {
      "name": "脱脂牛奶"
    }
  }
}

# multi_match 全文查询
# 如下示例：在 items 索引中搜索 name 或 category 字段 匹配 "脱脂牛奶" 的文档。
GET /items/_search
{
  "query": {
    "multi_match": {
      "query": "脱脂牛奶",
      "fields": ["name", "category"]
    }
}
```

## 2、相关性算分
当我们利用 match 查询时，文档结果会根据与搜索词条的关联度打分（`_score`），返回结果时按照分值降序排列。

全文检索查询时，查询结果的会以文档与用户输入内容关联度、匹配度来打分进行排序，得分越高的排序越靠前。

## 3、精确查询
精确查询，英文是`Term-level query`，顾名思义，`词条级别`的查询。也就是说`不会对用户输入的搜索条件再分词`，而是作为一个`词条`，与搜索的字段内容精确值匹配。
因此推荐用来查找`keyword`、数值、日期、`boolean`类型的字段。例如：id、price、城市、地名、人名等作为一个整体才有含义的字段。

### 1、ids 查询
```shell
# 通过文档ID精确查询items索引中的特定文档
GET /items/_search
{
  "query": {
    "ids": {
      "values": ["1133837", "1305611"]
    }
  }
}
```

### 2、term 查询
`term`查询 用于精确匹配未经分词的字段值，其语法如下：

```shell
GET /{索引库名}/_search
{
  "query": {
    "term": {
      "字段名": {
        "value": "搜索条件"
      }
    }
  }
}

# 代码示例：精确查询 - term 查询
# 1、精确查询 items 索引库中 price 字段值为 96300 的文档
GET /items/_search
{
  "query": {
    "term": {
      "price": {
        "value": "96300"
      }
    }
  }
}
# 2、term 查询 查询字段类型是 keyword 类型时，需要加 keyword。这样使用 .keyword 子字段进行精确匹配，避免分词问题。
GET /items/_search
{
  "query": {
    "term": {
      "brand.keyword": {
        "value": "华为"
      }
    }
  }
}
```

### 3、range 查询
`range`是范围查询，对于范围筛选的关键字有：
- `gte`：大于等于
- `gt`：大于
- `lte`：小于等于
- `lt`：小于

`range`查询语法如下：
```shell
GET /{索引库名}/_search
{
  "query": {
    "range": {
      "字段名": {
        "gte": {最小值},
        "lte": {最大值}
      }
    }
  }
}

# 如下示例，检索 items 索引中 price 字段值在 5000 到 10000 之间的文档：
GET /items/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 5000,
        "lte": 10000
      }
    }
  }
}
```
