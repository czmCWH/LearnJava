# 一、数据聚合
Elasticsearch 不仅可以做数据的存储与搜索，还可以做海量数据的分析与运算，这种分析与运算的功能就称为数据聚合。

聚合(`aggregations`) 可以实现对文档数据的统计、分析、运算。聚合常见的有三类：

* 1、桶（`Bucket`）聚合：用来对文档做`分组`。类似于 mysql 的 group by。
  - `TermAggregation`：按照文档字段值分组，例如：按照品牌值分组、按照国家分组
  - `Date Histogram`：按照日期阶梯分组，例如：一周为一组，或者一月为一组

* 2、度量（`Metric`）聚合：用以`计算`一些值，比如：最大值、最小值、平均值等
  - `Avg`：求平均值
  - `Max`：求最大值
  - `Min`：求最小值
  - `Stats`：同时求`max`、`min`、`avg`、`sum`等

* 3、管道（`pipeline`）聚合：其它聚合的结果为基础做进一步运算

> 注意⚠️：参加聚合的字段必须是 keyword、日期、数值、布尔类型；即非分词字段。
> 聚合是搜索的一部分，它和查询、排序、分页是同级的。

# 二、DSL 聚合语法

聚合必须的三要素：
- 聚合名称
- 聚合类型
- 聚合字段

## 1、Bucket 聚合
案例1 - 统计所有商品中共有那些商品分类、商品品牌，即以 分类(category)、品牌(brand) 字段对数据分组。
分类(category) 值一样的文档放在同一组，属于 Bucket 聚合中的 Term 聚合。

在 kibana Dev Tools 中执行如下命令查询：
```shell
GET /items/_search
{
  "size": 0,    # 设置 size 为 0，表示查询结果中不包含文档，只包含聚合结果
  "aggs": {   # aggs 关键字，用于定义聚合对象
    "cate_agg": {   # cate_agg 表示聚合名字，解析结果时，需要用到此名字
      "terms": {    # 聚合类型，按照分类值聚合，所以选择 term 聚合
        "field": "category.keyword",    # 参与聚合的字段，这里使用 category(分类) 字段。
        "size": 20  # 表示希望获取的聚合结果的最大数量
      }
    },
    "brand_agg": {    # brand_agg 表示聚合名字
      "terms": {
        "field": "brand.keyword",
        "size": 20
      }
    }
  }
}

# 查询结果：
{
  "took" : 45,
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
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "brand_agg" : {     # ----- 品牌聚合结果
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 54283,
      "buckets" : [
        {
          "key" : "华为",
          "doc_count" : 7145    # 品牌名称为 华为 的文档数量有 7145 条
        },
        {
          "key" : "南极人",
          "doc_count" : 2432
        },
        // ... 其它
      ]
    },
    "cate_agg" : {     # ----- 分类聚合结果
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "休闲鞋",
          "doc_count" : 20612
        },
        {
          "key" : "牛仔裤",
          "doc_count" : 19611
        },
        // ... 其它
      ]
    }
  }
}
```

### 案例2 - 带条件的聚合。价格高于3000元的手机品牌有哪些：
默认情况下，Bucket 聚合是对索引库的所有文档做聚合，我们可以限定要聚合的文档范围，只要添加 `query` 条件即可。
```shell
GET /items/_search
{
  "query": {  # 聚合条件
    "bool": {
      "filter": [
        {
          "term": {
            "category.keyword": "手机"
          }
        },
        {
          "range": {
            "price": {
              "gte": 300000
            }
          }
        }
      ]
    }
  },
  "size": 0,
  "aggs": {
    "brand_agg": {
      "terms": {
        "field": "brand.keyword",
        "size": 20
      }
    }
  }
}
```

## 2、Metric 聚合 
除了对数据分组(Bucket)以外，还可以对每个 Bucket 内的数据进一步做数据计算和统计。
案例：查询手机有哪些品牌，每个品牌的价格最小值、最大值、平均值，这就需要用到 `stat`聚合，就可以同时获取`min`、`max`、`avg`等结果。
```shell
GET /items/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "term": {
            "category.keyword": "手机"
          }
        }
      ]
    }
  }, 
  "size": 0,
  "aggs": {   # 先进行分组聚合
    "brand_agg": {  # 聚合名称
      "terms": {
        "field": "brand.keyword",
        "size": 20
      },
      "aggs": {   # 在 brand_agg 聚合操作的基础上，再进行度量聚合。这个聚合就是`brand_agg`的子聚合，会对`brand_agg`形成的每个桶中的文档分别统计。
        "price_stats": {  # price_stats 表示聚合的名字
          "stats": {  # 聚合类型，stats是`metric`聚合的一种
            "field": "price"  # 聚合字段，这里选择`price`，统计价格
          }
        }
      }
    }
  }
}

# 聚合结果：
{
  "took" : 52,
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
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "brand_agg" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 1728,
      "buckets" : [
        {
          "key" : "华为",
          "doc_count" : 7145,
          "price_stats" : {   # 对聚合的统计情况
            "count" : 7145,
            "min" : 0.0,
            "max" : 544000.0,
            "avg" : 50073.561931420576,
            "sum" : 3.577756E8
          }
        },
        {
          "key" : "小米",
          "doc_count" : 1227,
          "price_stats" : {
            "count" : 1227,
            "min" : 200.0,
            "max" : 889400.0,
            "avg" : 51005.86797066015,
            "sum" : 6.25842E7
          }
        },
        //... 
      ]
    }
  }
}
```

# 三、Java RestClient 实现聚合

> 代码实现：`/item-service/src/test/.../ElasticSearchTest.java`

## 1、Bucket 聚合
```java
/**
 * DSL 查询 - 数据聚合
 */
@Test
void testAgg() throws IOException {
    // 1、创建 Request 对象
    SearchRequest request = new SearchRequest("items");

    // 2、组织DSL参数
    // 2.1、分页
    request.source().size(0);   // 聚合查询不需要返回文档数据，所以设置size 为0
    // 2.2、聚合条件
    String brandAggName = "brandAgg";
    request.source().aggregation(       // aggregation 聚合方法
            // AggregationBuilders 构建聚合条件
            AggregationBuilders.terms(brandAggName)    // 根据聚合名称创建聚合类型
            .field("brand.keyword")     // 聚合字段
            .size(20)    // 获取聚合结果的最大数量
    );

    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    
    // 4、解析结果
    System.out.println("--- 聚合结果- response = "+ response);

    // 1、获取聚合结果
    Aggregations aggregations = response.getAggregations();
    // 2、根据聚合名称获取对应的 聚合，获取 Terms 类型聚合
    Terms brandTerms = aggregations.get(brandAggName);
    // 3、获取 buckets 聚合结果
    List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
    for (Terms.Bucket bucket : buckets) {
        System.out.println("--- 品牌名称, brand = " + bucket.getKeyAsString());
        System.out.println("--- 品牌的文档数量, count = " + bucket.getDocCount());
    }
}
```

## 2、Metric 聚合
```java
/**
 * DSL 查询 - 数据聚合，Metric 聚合
 */
@Test
void testAggMetric() throws IOException {
    // 1、创建 Request 对象
    SearchRequest request = new SearchRequest("items");

    // 2、组织DSL参数
    // 2.1、分页
    request.source().size(0);   // 聚合查询不需要返回文档数据，所以设置size 为0
    // 2.2、设置过滤条件
    request.source().query(QueryBuilders.boolQuery()
            .filter(QueryBuilders.termQuery("category.keyword", "手机"))
            .filter(QueryBuilders.rangeQuery("price").gte("300000"))
    );
    // 2.3、聚合条件
    String brandAggName = "brandAgg";
    request.source().aggregation(
            AggregationBuilders.terms(brandAggName)
                    .field("brand.keyword")
                    .size(20)
                    .subAggregation(
                            AggregationBuilders.stats("stats_metric").field("price")
                    )
    );


    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);


    // 4、解析结果

    // 1、获取聚合结果
    Aggregations aggregations = response.getAggregations();
    // 2、根据聚合名称获取对应的 聚合，获取 Terms 类型聚合
    Terms brandTerms = aggregations.get(brandAggName);
    // 3、获取 buckets 聚合结果
    List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
    for (Terms.Bucket bucket : buckets) {
        System.out.println("--- 品牌名称: 数量 = " + bucket.getKeyAsString() + ": " + bucket.getDocCount());
        Aggregations aggregations1 = bucket.getAggregations();
        if (aggregations1 != null) {
            Stats statsMetric = aggregations1.get("stats_metric");
            System.out.println("--- 平均价格 = " + statsMetric.getAvg());
            System.out.println("--- 最大价格 = " + statsMetric.getMax());
            System.out.println("--- 最小价格 = " + statsMetric.getMin());
        }
    }
}
```