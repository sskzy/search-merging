package com.example.demo.search.handle;

import com.example.demo.search.handle.svper.SearchEngineHandle;

/**
 * @author : songtc
 * @since : 2023/10/24 11:55
 */
public interface SearchEngineClearHandle<T> extends SearchEngineHandle<T> {

    /**
     * 搜过清洗器
     * 负责搜索词的搜索预处理(例: 分词、去除特殊字符等)
     *
     * @param name 搜索名称
     * @return 经过清晰的搜索名称
     */
    String clear(String name);
}
