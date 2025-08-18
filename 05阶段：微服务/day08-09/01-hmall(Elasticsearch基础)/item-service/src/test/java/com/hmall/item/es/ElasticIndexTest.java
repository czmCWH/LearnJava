package com.hmall.item.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 1、使用 Elasticsearch Java 客户端操作 --- 索引库
 */

public class ElasticIndexTest {
    private RestHighLevelClient client;

    // 单元测试初始化
    @BeforeEach
    void setUp() {
        // 初始化
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

    // 单元测试结束后，销毁 client
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
     * 1、创建索引库 items 并设置 mapping 映射
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

    /**
     * 2、查询 items 索引库结构信息
     */
    @Test
    void testGetIndex() throws IOException {
        // 1、准备 request 对象，items 表示索引库名称
        GetIndexRequest request = new GetIndexRequest("items");

        // 查询索引库结构信息
        GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);

        // 2、发送请求
        boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("/items 索引库是否存在，isExists = " + isExists);
    }

    /**
     * 3、删除索引库
     */
    @Test
    void testDeleteIndex() throws IOException {
        // 1、准备 request 对象，items 表示索引库名称
        DeleteIndexRequest request = new DeleteIndexRequest("items");
        // 2、发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }


    // 准备商品索引库的 Mapping 映射。通过在 kibana -> Dev Tools 控制台书写 Mapping，这样比较方便。
    private static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": { \n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"price\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"image\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"category\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"brand\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"sold\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"commentCount\": {\n" +
            "        \"type\": \"integer\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"isAD\": {\n" +
            "        \"type\": \"boolean\"\n" +
            "      },\n" +
            "      \"updateTime\": {\n" +
            "        \"type\": \"date\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

}
