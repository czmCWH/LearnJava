# 一、HttpClient
`HttpClient` 是 `Apache` 的一个子项目，是高效的、功能丰富 的 支持 `HTTP` 协议的客户端编程工具包。

    作用：用于在 `java` 项目中发送 `http` 请求，并接收响应数据。
    应用场景：对接第三方服务，例如：微信支付、短信服务、地图服务等


# 二、使用 HttpClient
开始导入依赖：
```xml
<!-- httpClient 插件，⚠️ 阿里云OSS依赖中已包含此插件-->
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.13</version>
</dependency>
```

步骤1、创建 HttpClient 对象；
```
HttpClients
HttpClient
CloseableHttpClient
HttpGet
HttpPost
```

步骤2、创建 Http 请求对象；
步骤3、调用 HttpClient 的 execute 方法发送请求；

代码实现：`TestHttpClient.java`。




