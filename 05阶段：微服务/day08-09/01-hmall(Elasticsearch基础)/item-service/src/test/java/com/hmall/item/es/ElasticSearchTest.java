package com.hmall.item.es;

import cn.hutool.core.builder.EqualsBuilder;
import com.alibaba.fastjson.JSON;
import com.hmall.item.domain.po.ItemDoc;
import com.mysql.cj.QueryBindings;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.stats.SearchStats;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 使用 Elasticsearch Java 客户端 搜索文档
 */


public class ElasticSearchTest {
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

    /**
     * Elasticsearch 数据搜索 - JavaRestClient 快速入门，测试match_all搜索
     */
    @Test
    void testMatchAll() throws IOException {
        // 1、创建 request 对象
        SearchRequest request = new SearchRequest("items");
        // 2、配置 request 参数
        request.source()     // source()，构建DSL，DSL中可以包含查询、分页、排序、高亮等
                .query(QueryBuilders.matchAllQuery());
        // query() 设置查询条件，在 JavaRestAPI 中，所有类型的Query查询条件都是由 QueryBuilders 来构建的。
        // QueryBuilders.matchAllQuery() 构建一个 match_all 查询的DSL，即查询所有数据。

        // 3、发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        System.out.println("--- 快速入门，查询items 索引库中所有数据 = "+ response);

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


    /**
     * Java 客户端实现 DSL 叶子查询
     */
    @Test
    void testDSLSearch() throws IOException {
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
                        .filter(QueryBuilders.rangeQuery("price").lte("30000"))  // 价格过滤
        );

        // 3、发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 4、解析查询结果
        parseResponseResult(response);
    }

    /**
     * DSL 查询 - 排序分页
     */
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

    /**
     * DSL 查询 - 数据聚合，bucket 聚合
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
        request.source().aggregation(
                AggregationBuilders.terms(brandAggName)
                .field("brand.keyword")
                .size(20)
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
}
