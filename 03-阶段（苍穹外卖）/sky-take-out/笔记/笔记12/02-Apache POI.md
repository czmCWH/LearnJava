# 导出数据
`Apache POI` 是一个处理 `Miscrosoft office` 各种文件格式的开源项目。
简单来说就是，我们可以使用 `POI` 在 `Java` 程序中对 `Miscrosoft Office` 各种文件进行读写操作。

一般情况下，POI 都是用于操作 Excel 文件。

还有许多其它框架，例如：Easy Excel。

1、导入依赖

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
</dependency>
```

> 代码实现：`src/test/java/com/czm/TestPOI.java`
> /controller/admin/ReportController.java ---> export