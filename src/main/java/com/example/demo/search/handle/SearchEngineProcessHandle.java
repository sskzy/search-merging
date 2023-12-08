package com.example.demo.search.handle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.search.handle.svper.SearchEngineHandle;

/**
 * @author : songtc
 * @since : 2023/10/24 11:55
 */
public interface SearchEngineProcessHandle<T> extends SearchEngineHandle<T> {

    /**
     * 搜索执行器
     * 从数据库中获取指定规则的数据
     *
     * @param name 搜素名称
     * @return 搜索结果
     */
    Page<T> process(String name);
}
