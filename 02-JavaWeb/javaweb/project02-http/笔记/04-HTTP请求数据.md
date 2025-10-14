# 一、HTTP 协议
HTTP：Hyper Text Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间 数据传输的规则。

HTTP 协议的特点:
1. 基于 TCP协议：面向连接，安全。
2. 基于 请求-响应模型，即一次请求对应一次响应。
3. HTTP 协议是无状态的协议，对于事务处理没有记忆能力，即每次 `请求-响应` 都是独立的，不会相互影响。

HTTP 协议的缺点：
  多次请求间不能共享数据（在 web 开发可以通过会话技术解决）。

HTTP 协议的优点：
  速度快。




# 二、HTTP 协议数据

## 2.1、HTTP协议 - 请求数据格式
HTTP协议的请求数据格式分为如下3个部分：

1、请求行：指请求数据第一行，由（请求方法、请求路径（或，资源路径）、协议版本）组成，如：`GET /hello?name=jack HTTP/1.1`。

2、请求头：从请求数据第二行开始，以 key:value 格式的数据。
  Host：请求的主机名，如：`Host: localhost:8080`
  User-Agent：浏览器版本，例如Chrome浏览器的标识类似 Mozilla/5.0 ... Chrome/79，IE浏览器的标识类似 Mozilla/5.0(Windows NT ...) like Gecko
  Accept：表示浏览器能接收的资源类型，如 `text/*`，`image/*` 或者 `*/*`表示所有;
  Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；
  Accept-Encoding：表示浏览器可以支持的压缩类型，例如 gzip，deflate等。
  Content-Type：请求主体的数据类型。如：`application/json;charset=UTF-8`
  Content-Length：请求主体的大小(单位:字节)。

3、请求体：POST 请求存放请求数据的地方。它与请求头之间间隔了一个空行。

> GET 请求和 POST 请求的区别：
> `GET请求` 请求参数放在请求行中，并且有大小限制；Get请求没有请求体。
> `POST请求` 请求参数放在请求体里面，没有大小限制。

### HTTP-请求数据获取
Web服务器（Tomcat） 对 HTTP协议的请求数据 进行解析，并进行了封装( `HttpServletRequest`)，并在调用 `Servlet方法` 的时候传递给了 `Servlet`
（或，在调用 Controller 方法的时候传递给了该方法）。
这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷。

HttpServletRequest 对象里面封装了所有的请求信息。


## 2.1、HTTP协议 - 响应数据格式
HTTP协议的响应数据格式分为如下3个部分：

1、响应行：响应数据第一行，由（协议、状态码、状态码的描述）组成。如：`HTTP/1.1 200`。
2、响应头：从响应数据第二行开始，`key: value` 格式的数据：
  `Content-Type`，表示该响应内容的类型，例如：text/html，application/json。
  `Content-Length`，表示该响应内容的长度(字节数)。
  `Content-Encoding`，表示该响应压缩算法，例如：gzip。
  `Cache-Control`，指示客户端应如何缓存，默认不缓存，例如：`max-age=300`表示可以最多缓存300秒,300秒发送的请求将不会重新发送请求。
  `Set-Cookie`，告诉浏览器为当前页面所在的域设置 `cookie`。
3、响应体：最后一部分，存放响应数据。它和响应头之间间隔一行。

### HTTP状态码：
常见状态码见图：`img/03
状态码详解：<https://cloud.tencent.com/developer/chapter/13553>

### web服务器端设置 HTTP 的响应数据

`Web服务器` 对HTTP协议的响应数据进行了封装(`HttpServletResponse`)，并在调用 `Servlet` 方法的时候传递给了 `Servlet`
（或，在调用 Controller 方法的时候传递给了该方法）。
这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷

> 注意⚠️：
> 响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。

方式1，基于 HttpServletResponse 对象来设置。
方式2，基于 Spring 提供的 ResponseEntity 对象来设置。







