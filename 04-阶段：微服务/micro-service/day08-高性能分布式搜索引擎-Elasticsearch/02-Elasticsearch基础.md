`elasticsearch` 处理海量数据时速度非常的快，远远高于关系型数据库，这是因为它底层采用的索引方式是倒排索引。

# 一、es 倒排索引
1. 文档(document)：数据库表中的每一条数据就是一个文档；
2. 词条(term)：对文档中的内容按照语义`分词`，得到的词语就是`词条`；（即：文档按照语义分成的词语）

## 正向索引
传统数据库(如MySQL)采用正向索引，它是基于文档ID创建索引，当根据ID查询时速度快。但是进行模糊查询时，需要逐行扫描进行匹配直到最后，因此进行模糊查询时性能很差。
es 倒排索引 正是为了 解决传统数据库采用 正向索引 进行模糊查询时耗时长、性能差的问题。

正向索引查询见图：`/img/01-es基础知识/02-关系型数据库正向索引.jpg`

## 倒排索引的原理
1. 它首先根据文档ID建立正向索引；
2. 然后对文档内容分词，对词条创建索引，并记录词条所在文档的id。
3. 查询时先根据词条查询到文档的id，而后根据文档id查询文档。(会查询2张表)
 
> 正向索引查询词条时，是先找文档，再判断是否包含词条；倒排索引是先根据词条在词条表中找文档ID，再根据文档ID在文档表中查询文档；
> 
> 倒排索引原理见图：`/img/01-es基础知识/03-Elasticsearch倒排索引.jpg`。


# 二、IK 分词器
`倒排索引` 有一个非常关键的步骤：`分词`，即如下2步：
1. 存数据建立倒排索引时需要对文档内容分词，得到词条然后根据词条建立索引；
2. 当用户输入搜索内容时，对输入的内容分词，拿到词条后进行查询；

`分词` 需要用到分词器，把一句话或一段话变成一个个词语。英文分词比较简单，按照空格分割即可。中文需要按照语义进行分析得到一个个词语，中文含义变化多端，这是非常复杂的。
通常我们使用国人的分词器。

中文分词往往需要根据语义分析，比较复杂，这就需要用到中文分词器，例如：`IK分词器`。`IK分词器` 是林良益在2006年开源发布的，其采用的正向迭代最细粒度切分算法一直沿用至今。
`IK分词器` 作者在 2012 年停止维护，后来由其它公司对其进行维护。


## 1、下载 `IK分词器` 插件
进入官网 <https://github.com/infinilabs/analysis-ik> 找到下载地址，进入 `analysis-ik/` 目录，
我们下载与 `Elasticsearch` 同一版本的包 `elasticsearch-analysis-ik-7.13.0.zip`，下载后并解压到 `ik` 目录。

> `IK分词器` 的版本号与 `Elasticsearch`相对应的。 

## 2、安装 `IK分词器` 插件
`IK分词器` 安装的方式比较简单，只要将 分词器 放入 elasticsearch 的插件目录即可：
```shell
# 1、查看 es 插件目录挂载的数据卷
$ docker volume ls
local     es-data
local     es-plugins
local     mq-plugins

# 2、查看 es-plugins 数据卷挂载的目录
$ docker volume inspect es-plugins
[
    {
        "CreatedAt": "2025-08-04T06:51:22Z",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/es-plugins/_data",
        "Name": "es-plugins",
        "Options": null,
        "Scope": "local"
    }
]

# 3、如果是 Linux 系统直接把下载的 IK分词器插件 复制到 /var/lib/docker/volumes/es-plugins/_data 即可
# 由于是在 MacOS 上此目录是无法直接访问的，所以我们删除 es 容器，采用  Bind Mount 直接挂载本地目录。
```
```shell
# 1、重新安装 Elasticsearch，并挂载本地目录
$ docker rm es
$ docker run -d \
--name es \
-e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
-e "discovery.type=single-node" \
-v /Users/chen/elasticsearch/data:/usr/share/elasticsearch/data \
-v /Users/chen/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
--privileged \
--network hm-net \
-p 9200:9200 \
-p 9300:9300 \
docker.elastic.co/elasticsearch/elasticsearch:7.13.0-arm64

# 2、准备好 ik 插件，把 ik 目录 复制到本机 /Users/chen/elasticsearch/plugins 目录下
$ pwd
/Users/chen/Desktop/ik
$ ls
commons-codec-1.9.jar			httpclient-4.5.2.jar
commons-logging-1.2.jar			httpcore-4.4.4.jar
config					plugin-descriptor.properties
elasticsearch-analysis-ik-7.13.0.jar	plugin-security.policy

# 3、重启 Elasticsearch 和 kibana 
$ docker restart es
$ docker restart kibana
```

## 3、测试 IK 分词器
方法1：访问Kibana：http://localhost:5601，进入 `【Dev tools】` 输入如下命令。

```shell
POST /_analyze
{
  "analyzer": "ik_smart",
  "text": "好好学习java太棒了"
}
```
语法解析：
* `POST /_analyze`：发送 POST 请求，请求路径为 `/_analyze`，kibana 会补充为完整路径 `http://localhost:9200/_analyze`。
* 请求参数 json 风格：analyzer 分词器类型， 默认为 `standard` 类型。`text` 要分词的内容。
* ik 插件的 `analyzer`(分词器)：`ik_smart`、`ik_max_word` 和 `tokenizer`(标记器)：`ik_smart`、`ik_max_word`

### IK 分词器的模式
`ik_smart`，智能切分，粗粒度；
`ik_max_word`，最细切分，细粒度IK分词器；

## 4、拓展 IK 分词器词库中的词条
`analysis-ik` 内部有一个词典，里面包含了中文最常见的词语，当 `ik分词器` 进行分词时，它会遍历 `text` 中的每一个汉字，
在遍历过程中分析匹配到它内部词典中的词语，如果匹配到了就作为一个词条。
因此`analysis-ik` 分词是依赖于词典的，只有词典中有的词才能分出来，而有些新词不会出现在该词典中（例如：`泰裤辣`），因此我们需要拓展该词典。

我们可以在 analysis-ik 插件的 `/config/` 目录下的 `IKAnalyzer.cfg.xml` 文件中拓展分词器词库中的词条。

### 步骤1，扩展 `ext.dic` 和 `ext_stop.dic` 文件

> 扩展 `ext.dic`、`ext_stop.dic` 文件 和 `IKAnalyzer.cfg.xml` 文件在同一个目录中。

```IKAnalyzer.cfg.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">ext.dic</entry>
	 <!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords">ext_stop.dic</entry>
	<!--用户可以在这里配置远程扩展字典 -->
	<!-- <entry key="remote_ext_dict">words_location URL路径</entry> -->
	<!--用户可以在这里配置远程扩展停止词字典-->
	<!-- <entry key="remote_ext_stopwords">words_location URL路径</entry> -->
</properties>

```
在 `ext.dic` 添加拓展词，`analysis-ik` 插件解析时会把拓展词作为一个词条，如下所示：
```ext.dic
泰裤辣
奥利给
```
在 `ext_stop.dic` 添加停止词，停止词表示停止，即不会被匹配，如下所示：
```ext_stop.dic
奥
嘤
```

### 步骤2，重启 `Elasticsearch` 并测试
```shell
# 在 MacOS 上操作，当修改目录中的文件时，会生成 .DS_Store 导致 es 重启失败，需要删除 .DS_Store。
$ cd /Users/chen/elasticsearch/plugins 
$ rm -f .DS_Store
$ cd ik
$ rm -f .DS_Store

$ docker restart es
```


# 三、ES 基础概念 - 重点
1. 数据库中表每一条数据 对应着 `elasticsearch` 中的文档，`elasticsearch` 中的文档数据会被序列化为 `json` 格式后存储在 `elasticsearch` 中。
2. elasticsearch 根据 文档类型 管理数据，相同类型的文档集合称为 `索引(index)、索引库`，如：商品索引、用户索引、订单索引；
3. `索引(index)、索引库` 和 数据库表 类似，对存入的数据结构也有约束，保证存储的数据具有相同的类型。`映射 mapping`：索引中文档的字段约束信息，类似表的结构约束。
4. Elasticsearch 使用 DSL 语句来定义搜索条件，DSL 是JSON风格的请求语句。

ES 基础概念与 MySQL 的对应关系见图：`/img/01-es基础知识/04-Elasticsearch基础概念.jpg`。