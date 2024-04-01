实现 

搜索执行器 SearchEngineHandle

```
搜索清洗 SearchEngineClearHandle 
搜索过滤 SearchEngineFilterHandle 
搜索合并 SearchEngineProcessHandle 
```

配置中装配

```
组装 搜索引擎 SearchEngine
添加 搜索执行器 execute
```

注入server服务中使用

```java
@Resource(name = "gameSearch")
private SearchEngineChannel<GameDto> searchEngineChannel;
```

