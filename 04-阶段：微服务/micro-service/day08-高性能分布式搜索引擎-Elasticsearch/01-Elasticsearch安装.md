使用数据库模糊搜索时 响应耗时 随着数据量的增加而 变长、性能差，如果使用 搜索引擎 则耗时短、性能高。

主流的搜索引擎技术排名：
1. Elasticsearch：开源的分布式搜索引擎；
2. Splunk：商业项目；
3. Solr：Apache 的开源搜索引擎；

# 一、Elasticsearch 简介
`Lucene` 是一个 `java` 语言的搜索引擎类库，是 `Apach` 公司的顶级项目，由 `DougCutting` 于1999年研发。简称 `es`。
官网 <https://lucene.apache.org/>

`Lucene` 的优势：
1. 易扩展；
2. 高性能；(基于倒排索引)

* 2004年 Shay Banon 基于 Lucene 开发了 Compass；
* 2010年 Shay Banon 重写了 Compass，取名 为 Elasticsearch；
* 官网：<https://www.elastic.co/cn/>，目前常用 7.x.x 版本；

`Elasticsearch` 具备下列优势：
1. 支持分布式，可水平扩展；
2. 提供 `Restful 接口`，可被任何语言调用；（⚠️ 通过向es发送http请求的方式，即可调用es实现数据的增删改查 ⚠️）


`Elasticsearch` 结合 `kibana`、`Logstash`、`Beats`，是一整套技术栈，被叫做 `ELK`。被广泛应用在日志数据分析、实时监控等领域。
`ELK` 技术栈见图：`/img/01-es基础知识/01-Elasticsearch技术栈.jpg`


# 二、安装 Elasticsearch
1. Elasticsearch：核心功能：数据存储、搜索 和 运算分析；
2. kibana：用于操作 Elasticsearch 的可视化控制台；（图形化展示可以方便的操作 Elasticsearch）

Elasticsearch Docker 镜像和标签的列表：<https://www.docker.elastic.co/>
源文件位于 <https://github.com/elastic/elasticsearch/tree/7.12/distribution/docker>

> ⚠️：Elasticsearch 与 kibana 的版本号必须一致！！！

## 1、使用 docker 命令安装 Elasticsearch
在 MacOS 上使用 docker 进行安装：<https://www.elastic.co/guide/en/elasticsearch/reference/7.12/docker.html>

导入 `es.tar`、`kibana.tar` 镜像。

```shell
# 1、下载 Elasticsearch 镜像
$ docker pull docker.elastic.co/elasticsearch/elasticsearch:7.13.0-arm64

# 2、安装 Elasticsearch 
$ docker run -d \
--name es \
-e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \   # 设置环境变量，配置JVM 使用最大和最小内存。es用java写的，底层使用的是 JVM 默认为 1GB，进行修改避免本机电脑扛不住。
-e "discovery.type=single-node" \   # 设置环境变量 运行模式：集群模式 or 单体模式，此处设置为 单体模式
-v es-data:/usr/share/elasticsearch/data \    # 数据存储目录 挂载数据卷
-v es-plugins:/usr/share/elasticsearch/plugins \    # 插件目录 挂载数据卷
--privileged \
--network hm-net \
-p 9200:9200 \  # 客户端访问端口
-p 9300:9300 \  # 集群间通讯端口
docker.elastic.co/elasticsearch/elasticsearch:7.13.0-arm64
```

## 2、使用 docker 命令安装 kibana
命令安装 kibana，它提供了一个开发控制台（`Dev Tools`），在其中对 Elasticsearch 的 Restful 的 API 接口提供了语法提示，便于我们操作 es。
```shell
# 1、拉取 kibana 镜像
$ docker pull docker.elastic.co/kibana/kibana:7.13.0

# 2、安装 kibana
$ docker run -d \
--name kibana \
-e ELASTICSEARCH_HOSTS=http://es:9200 \   # 配置环境变量 设置 访问es地址，通过容器名 es 访问
--network hm-net \
-p 5601:5601 \
docker.elastic.co/kibana/kibana:7.13.0
```

## 3、访问 Elastic 服务
`Elasticsearch`：http://localhost:9200
`Kibana`：http://localhost:5601
