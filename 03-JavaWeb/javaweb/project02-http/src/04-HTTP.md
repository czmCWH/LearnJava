浏览器 和 服务器交互时会遵循 HTTP 协议

# HTTP 协议

HTTP：Hyper Text Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间数据传输的规则。

特点:
1、基于TCP协议：面向连接，安全。
2、基于 请求-响应模型，即一次请求对应一次响应。
3、HTTP协议是无状态的协议，即对于事务处理没有记忆能力，每次 请求-响应 都是独立的。

缺点：多次请求间不能共享数据，
优点：速度快。


## HTTP 请求数据格式

Host：请求的主机名
User-Agent：浏览器版本，例如Chrome浏览器的标识类似 Mozilla/5.0 ... Chrome/79，IE浏览器的标识类似 Mozilla/5.0(Windows NT ...) like Gecko
Accept：表示浏览器能接收的资源类型，如 text/*，image/* 或者 */*表示所有;
Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；
Accept-Encoding：表示浏览器可以支持的压缩类型，例如 gzip，deflate等。
Content-Type：请求主体的数据类型。
Content-Length：请求主体的大小(单位:字节)。

* GET 请求和 POST 请求的区别：
 GET请求参数放在路径后面，有大小限制；POST请求请求参数放在请求体里面，没有大小限制。

### HTTP-请求数据获取
Web服务器 对 HTTP协议 的请求数据进行解析，并进行了封装(HttpServletRequest)，并在调用 Servlet方法 的时候传递给了 Servlet。这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷。



## HTTP 响应格式

* 常见的状态码：

1xx：响应中，是一个临时状态码，表示请求已经接收，告诉客户端应该继续请求 或者如果它已经完成则忽略它。（基本不出现）

2xx：成功，表示请求已经被成功接收，处理已完成。

3xx：重定向，重定向到其他地方;让客户端再发起一次请求以完成整个处理。

4xx：客户端错误，处理发生错误，责任在客户端。如：请求了不存在的资源、客户端未被授权、禁止访问等。

5xx：服务器错误，处理发生错误，责任在服务端。如：程序抛出异常等。

<https://cloud.tencent.com/developer/chapter/13553>

* HTTP 响应格式：

Content-Type，表示该响应内容的类型，例如：text/html，application/json。
Content-Length，表示该响应内容的长度(字节数)。
Content-Encoding，表示该响应压缩算法，例如：gzip。
Cache-Control，指示客户端应如何缓存，例如：max-age=300表示可以最多缓存300秒
Set-Cookie，告诉浏览器为当前页面所在的域设置 cookie。


### HTTP-响应数据设置
Web服务器对HTTP协议的响应数据进行了封装(HttpServletResponse)，并在调用Servlet方法的时候传递给了Servlet。这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷











