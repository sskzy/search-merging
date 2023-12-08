package com.example.demo.search.handle;

import com.example.demo.search.handle.svper.SearchEngineHandle;

/**
 * @author : songtc
 * @since : 2023/10/24 11:55
 */
public interface SearchEngineFilterHandle<T> extends SearchEngineHandle<T> {

    /**
     * 搜索过滤器
     * 搜索的名称满足过滤配置则抛弃当前过滤名称
     *
     * @param name 搜索的名称
     * @return 是否满足过滤规则
     */
    Boolean filter(String name);
}
