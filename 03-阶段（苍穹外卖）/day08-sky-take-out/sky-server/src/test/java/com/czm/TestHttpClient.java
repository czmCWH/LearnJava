package com.czm;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示使用 HttpClient
 */

//@SpringBootTest       // ⚠️ 单元测试时，需要使用 Spring IOC 容器环境时，则需添加 @SpringBootTest 注解
public class TestHttpClient {

    /*
     案例实现：通过 HttpClient 发送 get、post 请求
     需求1：通过HttpClient 发送get请求，请求苍穹外卖用户端的查看店铺营业状态接口。/user/shop/status
     需求2：通过HttpClient 发送post请求，请求苍穹外卖管理端的员工登录接口。/admin/employee/login

     ⚠️：调试需要注意：先启动 SkyServerApplication 工程，再执行 @Test 的方法！！！
     */


    @Test
    public void testGet() throws Exception {
        // 1、创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2、创建 HttpGet 请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8888/user/shop/status");

        // 3、调用 HttpClient 对象的 execute 方法，发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4、解析响应数据
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("--- statusCode: " + statusCode);
        HttpEntity entity = response.getEntity();
        String dataStr = EntityUtils.toString(entity);
        System.out.println("--- result: " + dataStr);

        // 5、释放资源
        response.close();
        httpClient.close();
    }

    @Test
    public void testPost() throws Exception {
        // 1、创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2、创建 HttpPost 请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8888/admin/employee/login");

        // 3、构造请求体参数
//        HttpEntity httpEntity = new StringEntity("{\"username\":\"admin\",\"password\":\"123456\"}");
        Map map = new HashMap();
        map.put("username", "admin");
        map.put("password", "123456");
        StringEntity httpEntity = new StringEntity(JSON.toJSONString(map));

        // ⚠️ 设置 http 响应的数据类型，如果返回的数据类型与之不匹配，则报错 415.
        httpEntity.setContentEncoding("UTF-8");
        httpEntity.setContentType("application/json");

        httpPost.setEntity(httpEntity);

        // 4、发送请求，解析响应数据
        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("--- statusCode: " + statusCode);
        HttpEntity entity = response.getEntity();
        String dataStr = EntityUtils.toString(entity);
        System.out.println("--- result: " + dataStr);

        // 5、释放资源
        response.close();
        httpClient.close();
    }
}
