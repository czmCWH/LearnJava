# Nginx 服务器（了解）

nginx 是一款 HTTP Web 服务器、反向代理、内容缓存、负载均衡器、TCP/UDP 代理服务器和邮件代理服务器。

# 一、通过 Homebrew 上安装 Nginx 服务器

```shell
# 安装 nginx
$ brew install nginx
# 卸载
$ brew uninstall nginx

# 启动
$ brew services start nginx

# 停止
$ brew services stop nginx

# 重启nginx
$ brew services restart nginx

# 重新加载配置文件
$ nginx -s reload


# 验证nginx配置文件是否正确
$ nginx -t

```

查看 nginx 的配置信息：

```shell
$ brew info nginx

==> nginx: stable 1.27.4 (bottled), HEAD		# nginx 安装的版本
HTTP(S) server and reverse proxy, and IMAP/POP3 proxy server
https://nginx.org/
Installed
/opt/homebrew/Cellar/nginx/1.27.4 (27 files, 2.5MB) *
  Poured from bottle using the formulae.brew.sh API on 2025-03-21 at 17:29:47
From: https://github.com/Homebrew/homebrew-core/blob/HEAD/Formula/n/nginx.rb
License: BSD-2-Clause		# nginx 的安装目录
==> Dependencies
Required: openssl@3 ✔, pcre2 ✔
==> Options
--HEAD
	Install HEAD version
==> Caveats
Docroot is: /opt/homebrew/var/www	# 网页存放根目录

The default port has been set in /opt/homebrew/etc/nginx/nginx.conf to 8080 so that
nginx can run without sudo.		# 配置默认端口

# nginx将加载此目录下所有文件
nginx will load all files in /opt/homebrew/etc/nginx/servers/.	

To restart nginx after an upgrade:
  brew services restart nginx
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/nginx/bin/nginx -g daemon\ off\;
==> Analytics
install: 12,268 (30 days), 36,432 (90 days), 166,435 (365 days)
install-on-request: 12,240 (30 days), 36,322 (90 days), 165,996 (365 days)
build-error: 16 (30 days)
```

# 二、部署前端工程到 Nginx 服务器

把 Vue 项目打包后，直接把它的所有内容放到 Nginx 的根目录下 `/opt/homebrew/var/www`，就可以通过 http://localhost:8080/ 访问前端工程了。

当用户访问前端页面时，浏览器首先向 Nginx 服务器发起请求，Nginx 返回存储在其上的静态资源（如 HTML、CSS、JavaScript 文件），完成前端页面的加载。
前端页面发起API请求时，此时，Nginx 会根据`nginx.conf`中配置的反向代理规则，将特定路径的请求转发至后端Java服务器，而非直接暴露后端地址。

* 反向代理是一种网络架构技术，通过反向代理服务器为后端服务器做代理。通常，Nginx服务器是 Java 服务器的代理服务器。

* 反向代理的好处：
	- 安全（用户不必访问目标服务器地址，避免目标服务器被攻击）；
	- 负载均衡器（采用轮询的方式，让每台后端服务器处理差不多的请求量）；
	- 内容缓存；

> ⚠️
> Tomcat 服务器：部署后端web项目。承受并发只有 200～300。
> Nginx 服务器：部署前端项目。承受并发有 2万～3万。

Nginx 反向代理 配置示例如下：

```config
server {
	listen				90;
	server_name 	localhost;
	client_max_body_size	10m;
	
	location / {
		root html;
		index index.html index.htm;
		try_files $uri $uri/ /index.html;	
	}
	
	location ^~ /api/ {
		rewrite ^/api/(.*)$ /$1 break;
		proxy_pass "http": //localhost:8080;
	}	
}
```
1. `location`：用于定义匹配特定 `uri` 请求的规则。 
2. `^~ /api/`：表示精确匹配，即只匹配以 `/api/` 开头的路径。
3. `rewrite`：该指令用于重写匹配到的 `uri` 路径
4. `proxy_pass`：该指令用于代理转发，它将匹配到的请求转发给位于后端的指令服务器。
