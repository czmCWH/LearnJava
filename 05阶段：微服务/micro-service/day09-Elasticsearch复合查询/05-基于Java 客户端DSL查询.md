> 代码实现：`/item-service/src/test/.../ElasticSearchTest.java`

# 一、基于 Java 客户端构建查询条件
在 Java 中我们使用 `RestHighLevelClient` 进行文档搜索的基本步骤是：
1. 创建`request`对象，这次是搜索，所以是`SearchRequest`
2. 准备`request.source()` 构建请求体，也就是DSL，DSL中可以包含查询、分页、排序、高亮等。
    1. 使用 `QueryBuilders`来构建查询条件
    2. 传入`request.source()` 的` query() `方法
3. 发送请求，得到结果
4. 解析结果（参考JSON结果，从外到内，逐层解析）

由于 Elasticsearch 对外暴露的接口都是 Restful 风格的接口，因此 JavaAPI 调用就是在发送 Http 请求。而我们核心要做的就是利用Java代码组织请求参数，解析响应结果。

## 1、快速入门 - 查询所有数据并解析
```java
@Test
void testMatchAll() throws IOException {
    // 1、创建 request 对象
    SearchRequest request = new SearchRequest("items");
    // 2、配置 request 参数
    request.source()    // source() 构建完整的请求体，包含查询、分页、排序、高亮等 
            .query(QueryBuilders.matchAllQuery());  
    // query() 设置查询条件，在 JavaRestAPI 中，所有类型的Query查询条件都是由 QueryBuilders 来构建的。
    // QueryBuilders.matchAllQuery() 构建一个 match_all 查询的DSL，即查询所有数据。

    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    
    // 4、解析查询结果
    parseResponseResult(response);
}

private static void parseResponseResult(SearchResponse response) {
    // 4、解析查询结果
    SearchHits searchHits = response.getHits();
    // 4.1、查询总条数
    long total = searchHits.getTotalHits().value;
    System.out.println("--- 命中总条数, total = " + total);
    // 4.2、解析查询命中的数据
    SearchHit[] hits = searchHits.getHits();
    for (SearchHit hit : hits) {
        //4.2.1、获取 source 结果
        String json = hit.getSourceAsString();
        // 4.2.2、转为 ItemDoc
        ItemDoc item = JSON.parseObject(json, ItemDoc.class);
        System.out.println("item = " + item);
    }
}
```

## 2、叶子查询
```java
@Test
public void testDSLSearch() throws IOException {
    // 1、创建搜索请求
    SearchRequest request = new SearchRequest("items");
    // 2、设置查询参数

    // 2.1、全文检索(match、multi_match)
    // 2.1.1、全文检索 match 查询
    //        request.source().query(QueryBuilders.matchQuery("name", "脱脂牛奶"));
    // 2.1.2、全文检索 multi_match 查询
    //        request.source().query(QueryBuilders.multiMatchQuery("脱脂牛奶", "name", "category"));

    // 2.2、精确查询
    // 2.2.1、term 查询
    //        request.source().query(QueryBuilders.termQuery("brand.keyword", "华为"));
    // 2.2.2、Range 查询
    request.source().query(QueryBuilders.rangeQuery("price").gte(10000).lte(20000));

    // 3、发送请求获取响应结果
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 4、解析响应结果
    parseResponseResult(response);
}
```

## 3、复合查询
```java
/**
* DSL 复合查询 - 搜索关键字为脱脂牛奶，品牌为德亚，价格必须低于300 的文档数据
*/
@Test
void testSearch() throws IOException {
    // 1、创建 request 对象
    SearchRequest request = new SearchRequest("items");
    
    // 2、组织 DSL 查询
    request.source().query(
            QueryBuilders.boolQuery()   // 创建 bool查询对象
                    .must(QueryBuilders.matchQuery("name", "脱脂牛奶"))     // 关键字搜索
                    .filter(QueryBuilders.termQuery("brand.keyword", "德亚")) // 品牌过滤
                    .filter(QueryBuilders.rangeQuery("price").lt("30000"))  // 价格过滤
    );
    
    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    
    // 4、解析查询结果
    parseResponseResult(response);
}
```

# 二、查询结果处理（排序分页和高亮）

## 1、排序分页查询
```java
@Test
void testSortAndPage() throws IOException {
    // 0、模拟前端分页查询
    int pageNo = 2, pageSize = 10;

    // 1、创建 Request 对象
    SearchRequest request = new SearchRequest("items");
    // 2、组织 DSL 参数
    // 2.1、query 条件
    request.source().query(QueryBuilders.matchAllQuery());
    // 2.2、分页
    // from 从第几条文档开始，size 查询多少条文档
    request.source().from((pageNo - 1) * pageSize).size(pageSize);
    // 2.3、排序
    request.source()
            .sort("sold", SortOrder.DESC)   // 销量降序
            .sort("price", SortOrder.ASC);  // 价格升序

    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 4、解析结果
    parseResponseResult(response);
}
```

## 2、搜索关键字高亮显示
```java
/**
 * DSL 查询 - 搜索关键字高亮显示
 */
@Test
void testHighlight() throws IOException {
    // 1、创建 Request 对象
    SearchRequest request = new SearchRequest("items");
    // 2、组织 DSL 参数
    // 2.1、query 条件
    request.source().query(QueryBuilders.matchQuery("name", "脱脂牛奶"));
    // 2.2、高亮条件
    request.source().highlighter(SearchSourceBuilder.highlight()
            .field("name")
            .preTags("<em>")
            .postTags("</em>")
    );

    // 3、发送请求
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 4、解析结果
    parseResponseResult(response);

}
private static void parseResponseResult(SearchResponse response) {
    // 4、解析查询结果
    SearchHits searchHits = response.getHits();
    // 4.1、查询总条数
    long total = searchHits.getTotalHits().value;
    System.out.println("--- 命中总条数, total = " + total);
    // 4.2、解析查询命中的数据
    SearchHit[] hits = searchHits.getHits();
    for (SearchHit hit : hits) {
        //4.2.1、获取 source 结果
        String json = hit.getSourceAsString();
        // 4.2.2、转为 ItemDoc
        ItemDoc item = JSON.parseObject(json, ItemDoc.class);

        // 5、处理高亮结果
        // es 原始数据是放在 _source 返回；而处理高亮的数据是放在同级 _highlight 的中。因此要进行替换。
        Map<String, HighlightField> hfs = hit.getHighlightFields();
        if (hfs != null && !hfs.isEmpty()) {
            // 5.1、根据高亮字段名获取高亮结果
            HighlightField hf = hfs.get("name");
            // 5.2、获取高亮结果，覆盖非高亮结果
            // 高亮结果是数组返回，如果需要精确处理，还需把数组中每个元素都拼接
            String hfName = hf.getFragments()[0].string();
            item.setName(hfName);
        }

        System.out.println("item = " + item);
    }
}
```

