# JavaRestClient
Elasticsearch Java 客户端向前兼容，例如：8.12 版客户端可以与 Elasticsearch 8.13 版 进行通信而不会中断，但不支持其新功能。
当前，Elasticsearch 最新版本 9.0，通过如下链接查询到，Java 客户端最低版本为：Java Client: 7.16，支持 Java 8+。

https://www.elastic.co/docs/versions
https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/index.html

Elasticsearch Java 客户端更新很快，而企业中使用 Elasticsearch 的版本依然很低，接下来我们通过 Elasticsearch 8 以下的版本来使用：

## 1、添加 elasticsearch client 依赖
```xml
<!-- Elasticsearch 客户端 - 高性能分布式搜索引擎-->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
</dependency>
```

因为 `SpringBoot` 默认的 `ES` 版本是 `7.17.10`，和我们访问的 `Elasticsearch` 服务器的版本不一致，所以我们需要覆盖默认的ES版本:
```xml
<properties>
    <elasticsearch.version>7.13.0</elasticsearch.version>
</properties>
```

## 2、Elasticsearch 客户端初始化
```java
public class ElasticTest {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        // 初始化
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    @Test
    void testConnect() {
        System.out.println("--- 测试链接 Elasticsearch 是否成功，client = " + client);
    }
}
```

## 3、准备 hmall 商品表 Mapping 映射
我们要实现商品搜索，不能直接照搬数据中商品表的数据结构作为 Mapping 映射，因为商品表中的字段非常的多，并不是我们搜索都需要的。

索引库的字段是需要结合页面搜索业务需求的，如下所示我们根据 `Kibana Dev Tools` 控制台设计好 商品索引库 与 Mapping 映射:

实现搜索功能需要的字段包括三大部分：

- 搜索过滤字段
    - 分类
    - 品牌
    - 价格
- 排序字段
    - 默认：按照更新时间降序排序
    - 销量
    - 价格
- 展示字段
    - 商品id：用于点击后跳转
    - 图片地址
    - 是否是广告推广商品
    - 名称
    - 价格
    - 评价数量
    - 销量

```shell
# 创建商品索引库并设置 mapping 映射
PUT /items
{
  "mappings": {
    "properties": {
      "id": {     # 商品ID
        "type": "keyword"
      },
      "name": {   # SKU 名称
        "type": "text",
        "analyzer": "ik_smart"
      },
      "price": {  # 商品价格
        "type": "integer"
      },
      "image": {  # 商品图片
        "type": "keyword",
        "index": false
      },
      "category": {   # 类目名称
        "type": "keyword"
      },
      "brand": {    # 品牌名称
        "type": "keyword"
      },
      "sold": {     # 销量
        "type": "integer"
      },
      "commentCount": {   # 评论数
        "type": "integer",
        "index": false
      },
      "isAD": {   # 是否是推广广告
        "type": "boolean"
      },
      "updateTime": {   # 更新时间
        "type": "date"
      }
    }
  }
}
```

## 4、索引库操作
### 4.1、创建索引库
```java
/**
 * 创建商品索引库并设置 mapping 映射
 */
@Test
void testCreateIndex() throws IOException {
    // 1、准备 Request 对象，items 表示索引库名称
    CreateIndexRequest request = new CreateIndexRequest("items");
    // 2、准备请求参数
    // MAPPING_TEMPLATE 是一个静态常量，内容是 json 格式的 mapping 映射，其内容从 kibana 中复制出来。
    request.source(MAPPING_TEMPLATE, XContentType.JSON);
    //3、发送请求
    // indices() 方法执行返回的对象中包含操作索引的所有方法。
    client.indices().create(request, RequestOptions.DEFAULT);
}
```

### 4.2、查询索引库
```java
/**
 * 查询索引库
 */
@Test
void testGetIndex() throws IOException {
    // 1、准备 request 对象，items 表示索引库名称
    GetIndexRequest request = new GetIndexRequest("items");
    // 2、发送请求
    boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
    System.out.println("/items 索引库是否存在，isExists = " + isExists);
}
```

### 4.3、删除索引库
```java
/**
 * 删除索引库
 */
@Test
void testDeleteIndex() throws IOException {
    // 1、准备 request 对象，items 表示索引库名称
    DeleteIndexRequest request = new DeleteIndexRequest("items");
    // 2、发送请求
    client.indices().delete(request, RequestOptions.DEFAULT);
}
```

## 5、文档操作

### 5.1、新增文档
```java
/**
 * 使用 Elasticsearch Java 客户端实现 新增文档
 */
@Test
void testIndexDoc() throws IOException {
    // 0、准备文档数据
    // 0.1、根据商品id查询数据库
    Item item = itemService.getById(613358L);
    // 0.2、把数据库数据转换为 文档数据
    ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);

    // 全量更新
//        itemDoc.setPrice(item.getPrice() + 100);

    // 1、准备 Request
    // items：索引库名；id：文档ID；
    IndexRequest request = new IndexRequest("items").id(item.getId().toString());
    // 2、准备 JSON 格式文档
    request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
    // 3、发送请求
    client.index(request, RequestOptions.DEFAULT);
}
```

### 5.2、删除文档
```java
/**
 * 使用 Elasticsearch Java 客户端实现 删除文档
 */
@Test
void testDeleteDocById() throws IOException {
    // 1、准备 Request
    DeleteRequest request = new DeleteRequest("items","613358");
    // 2、发送请求
    client.delete(request, RequestOptions.DEFAULT);
}
```

### 5.3、查询文档
```java
/**
 * 使用 Elasticsearch Java 客户端实现 查询文档
 */
@Test
void testGetDocById() throws IOException {
    // 1、准备 Request
    // items：索引库名；id：文档ID；
    GetRequest request = new GetRequest("items","613358");
    // 2、发送请求查询文档
    GetResponse response = client.get(request, RequestOptions.DEFAULT);
    // 3、解析响应结果
    String source = response.getSourceAsString();
    ItemDoc doc = JSONUtil.toBean(source, ItemDoc.class);
    System.out.println("--- 查询结果, doc = " + doc);
}
```

### 5.4、修改文档
```java
/**
 * 修改文档数据有2种方式：
 * 方式1：全量更新，再次写入id一样的文档，就会删除旧文档，添加新文档。与新增的javaAPI一致。
 * 方式2：局部更新。只需更新指定部分字段
 * 如下实现局部更新
 */
@Test
void testUpdateDocById() throws IOException {
  // 1、创建 request 对象
  // items，指索引库名称；id：需要更新的文档id；
  UpdateRequest request = new UpdateRequest("items","613358");
  // 2、准备更新参数，每2个参数为一对 key value
  request.doc(
          "price", 25600
  );
  // 3、发送请求更新文档
  client.update(request, RequestOptions.DEFAULT);

}
```

## 6、批处理
BulkRequest 封装普通的 CRUD请求
```java
void test() {
  // 1、创建 Bulk Request
  BulkRequest request = new BulkRequest();

// 2、准备请求参数,添加要批量提交的数据
// 批量添加
// items，表示索引库名称；id：文档ID
  request.add(new IndexRequest("items").id("1").source("json", XContentType.JSON));
  request.add(new IndexRequest("items").id("1").source("json", XContentType.JSON));
  request.add(new IndexRequest("items").id("1").source("json", XContentType.JSON));

// 批量删除
// items，表示索引库名称；id：文档ID
  request.add(new DeleteRequest("items").id("1"));
  request.add(new DeleteRequest("items").id("1"));
  request.add(new DeleteRequest("items").id("1"));

// 3、发送 Bulk 请求
  client.bulk(request, RequestOptions.DEFAULT);
}
```

# 代码实现：
`hmall - item-service服务 - test单元测试`