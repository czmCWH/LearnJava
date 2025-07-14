# 一、WebSocket
`WebSocket` 是基于 `TCP` 的一种新的网络协议。
它实现了浏览器与服务器`全双工通信`--浏览器和服务器只需要完成一次握手，两者之间就可以创建`持久性`的连接，并进行`双向`数据传输。

1、`HTTP` 协议和 `WebSocket` 的对比：
1. HTTP 是`短连接`，即建立连接过一段时间后会自动断开；
2. WebSocket 是`长连接`；
3. HTTP 通信是`单向`的，基于请求响应模式，即一次请求一次响应；
4. WebSocket 支持`双向`通信；
5. HTTP 和 WebSocket 底层都是 `TCP` 连接；

2、`WebSocket` 应用场景：视频弹幕、网页聊天、体育实况更新、股票基金报价实时更新等。

## WebSocket 的缺点

* 服务器长期维护长连接需要一定的成本;
* 各个浏览器支持程度不一;
* WebSocket是长连接，受网络限制比较大，如果网络断开了就需要处理好重连；

> WebSocket并不能完全取代HTTP，它只适合在特定的场景下使用。


# 二、WebSocket 入门案例
### 1、案例：
在浏览器可以发送信息给服务器，并且能够接收服务端主动发送过来的信息。

### 2、实现步骤：
1. 直接使用 websocket.html 页面作为 WebSocket 客户端；
2. 导入 WebSocket 的 maven 坐标；
    ```
   <dependency>
       <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
   </dependency>
   ```
3. 导入 WebSocket 服务端组件 WebSocketServer，用于和客户端通信；
4. 导入配置类 WebSocketConfiguration，注册 WebSocket 的服务端组件；
5. 导入定时任务类 WebSocketTask，定时向客户端推送数据；

> WebSocket 的 URL 协议标识符为：`ws://`

### 3、代码实现

服务端： `src/main/java/com/czm/websocket`，`WebSocketTask.java` 每间隔5秒给客户端发送消息。

客户端：`src/main/resources/static/WebSocket.html`

正常来说启动项目，可以直接访问：`http://localhost:8888/WebSocket.html` 可是却报错：`Whitelabel Error Page`。
所以，只能直接在本机电脑浏览器上访问此页面了


# 三、

来单提醒
客户催单
