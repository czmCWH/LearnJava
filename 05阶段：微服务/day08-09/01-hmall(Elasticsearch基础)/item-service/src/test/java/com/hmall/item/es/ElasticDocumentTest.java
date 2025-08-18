package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * 2、使用 Elasticsearch Java 客户端操作 文档
 */

@SpringBootTest(properties = "spring.profiles.active=local")
public class ElasticDocumentTest {
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


    @Autowired
    private IItemService itemService;

    /**
     * 1、使用 Elasticsearch Java 客户端实现 新增文档
     */
    @Test
    void testIndexDoc() throws IOException {
        // 0、准备文档数据
        // 0.1、根据商品id查询数据库
        Item item = itemService.getById(613358L);
        // 0.2、把数据库数据转换为 文档数据
        // ItemDoc 结构与 items：索引库 Mapping 映射一致
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

    /**
     * 2、使用 Elasticsearch Java 客户端实现 删除文档
     */
    @Test
    void testDeleteDocById() throws IOException {
        // 1、准备 Request，指定索引库名 items 和 文档ID 613358
        DeleteRequest request = new DeleteRequest("items","613358");
        // 2、发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 3、使用 Elasticsearch Java 客户端实现 查询文档
     */
    @Test
    void testGetDocById() throws IOException {
        // 1、准备 Request
        // items：索引库名；id：文档ID；
        GetRequest request = new GetRequest("items","613358");
        // 2、发送请求查询文档
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3、解析响应结果
        String source = response.getSourceAsString();   // 获取文档中的_source 原始部分数据
        ItemDoc doc = JSONUtil.toBean(source, ItemDoc.class);
        System.out.println("--- 查询结果, doc = " + doc);
    }

    /**
     * 4、修改文档数据有2种方式：
     * 方式1：全量更新，再次写入id一样的文档，就会删除旧文档，添加新文档。与新增的javaAPI完全一致。
     * 方式2：局部更新。只需更新指定部分字段
     * 如下实现局部更新
     */
    @Test
    void testUpdateDocById() throws IOException {
        // 1、创建 request 对象
        // items，指索引库名称；id：需要更新的文档id；
        UpdateRequest request = new UpdateRequest("items","613358");
        // 2、准备更新参数，每2个参数为一对 key-value。
        request.doc(
                "price", 25600
        );
        // 3、发送请求更新文档
        client.update(request, RequestOptions.DEFAULT);

    }
}
