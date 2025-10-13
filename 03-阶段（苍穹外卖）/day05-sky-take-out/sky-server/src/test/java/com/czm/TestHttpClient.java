package com.czm;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHttpClient {

    @Test
    public void testGet() throws Exception {
        // 1、创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2、创建 HttpGet 对象
        HttpGet httpGet = new HttpGet("http://localhost:8888/user/shop/status");

        // 3、调用 HttpClient 对象的 execute 方法，发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4、解析响应数据
//        response.getstatus
    }

    @Test
    public void testPost(){

    }
}
