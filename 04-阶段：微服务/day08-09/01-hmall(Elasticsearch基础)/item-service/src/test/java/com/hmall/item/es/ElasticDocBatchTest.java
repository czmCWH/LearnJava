package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
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
import java.util.List;

/**
 * 3、使用 Elasticsearch Java 客户端 批量处理文档
 */

@SpringBootTest(properties = "spring.profiles.active=local")
public class ElasticDocBatchTest {
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
     * 1、把 查询数据库中的所有数据 写入 items 索引库
     * @throws IOException
     */
    @Test
    void testBulkDoc() throws IOException {
        int pageNum = 1, pageSize = 500;
        while (true) {
            // 1、从数据库中分页查询商品，准备文档数据
            Page<Item> page = itemService.lambdaQuery()
                    .eq(Item::getStatus, 1)
                    .page(Page.of(pageNum, pageSize));
            List<Item> records = page.getRecords();
            if (records == null || records.isEmpty()) {
                // 查询数据为空，结束循环
                return;
            }

            // 2、创建 Bulk Request
            BulkRequest request = new BulkRequest();

            // 3、准备请求参数
            for (Item item : records) {
                ItemDoc doc = BeanUtil.copyProperties(item, ItemDoc.class);
                // 添加要批量提交的数据
                // items，表示索引库名称；id：文档ID
                request.add(new IndexRequest("items")
                        .id(item.getId().toString())
                        .source(JSONUtil.toJsonStr(doc), XContentType.JSON));
            }

            // 批量添加
            // items，表示索引库名称；id：文档ID
//            request.add(new IndexRequest("items").id("1").source("json", XContentType.JSON));
//            request.add(new IndexRequest("items").id("2").source("json", XContentType.JSON));
//            request.add(new IndexRequest("items").id("3").source("json", XContentType.JSON));

            // 批量删除
            // items，表示索引库名称；id：文档ID
//            request.add(new DeleteRequest("items").id("1"));
//            request.add(new DeleteRequest("items").id("2"));
//            request.add(new DeleteRequest("items").id("3"));



            // 4、发送 Bulk 请求
            client.bulk(request, RequestOptions.DEFAULT);

            // 5、翻页
            pageNum++;
        }
    }
}
