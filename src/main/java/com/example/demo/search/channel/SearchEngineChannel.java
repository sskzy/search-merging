package com.example.demo.search.channel;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.search.SearchEngine;
import com.example.demo.search.handle.SearchEngineClearHandle;
import com.example.demo.search.handle.SearchEngineFilterHandle;
import com.example.demo.search.handle.SearchEngineProcessHandle;
import com.example.demo.search.handle.svper.SearchEngineHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : songtc
 * @since : 2023/10/24 11:56
 */
public class SearchEngineChannel<T> {

    // 内置引擎列表
    private List<SearchEngine<T>> searchEngines = new ArrayList<>();

    /**
     * 搜索功能 (触发式: 结果数据立刻返回)
     * 获取搜索结果立刻返回
     *
     * @param name 搜索名称
     */
    public Page<T> searchTrigger(String name) {
        Page<T> page = new Page<>();

        here:
        for (SearchEngine<T> searchEngine : searchEngines) {
            SearchEngineExecuteChannel<SearchEngineHandle<T>> executeChannel = searchEngine.getExecute();
            for (SearchEngineHandle<T> execute : executeChannel.getExecutes()) {
                // 过滤
                if (execute instanceof SearchEngineFilterHandle) {
                    if (((SearchEngineFilterHandle<T>) execute).filter(name)) {
                        break here;
                    }
                }
                // 清理
                if (execute instanceof SearchEngineClearHandle) {
                    name = ((SearchEngineClearHandle<T>) execute).clear(name);
                }
                // 查询
                if (execute instanceof SearchEngineProcessHandle) {
                    page = ((SearchEngineProcessHandle<T>) execute).process(name);
                    if (page != null && CollectionUtils.isNotEmpty(page.getRecords())) {
                        break here;
                    }
                }
            }
        }
        return page;
    }

    /**
     * 搜索功能 (追加式: 追加拼接所有结果数据)
     * 获取搜素结果拼接返回
     *
     * @param name 搜索名称
     */
    public Page<T> searchAppend(String name) {
        Page<T> page = new Page<>();

        here:
        for (SearchEngine<T> searchEngine : searchEngines) {
            SearchEngineExecuteChannel<SearchEngineHandle<T>> executeChannel = searchEngine.getExecute();
            for (SearchEngineHandle<T> execute : executeChannel.getExecutes()) {
                // 过滤
                if (execute instanceof SearchEngineFilterHandle) {
                    if (((SearchEngineFilterHandle<T>) execute).filter(name)) {
                        break here;
                    }
                }
                // 清理
                if (execute instanceof SearchEngineClearHandle) {
                    name = ((SearchEngineClearHandle<T>) execute).clear(name);
                }
                // 查询
                if (execute instanceof SearchEngineProcessHandle) {
                    Page<T> newPage = ((SearchEngineProcessHandle<T>) execute).process(name);
                    if (newPage != null) {
                        page.setRecords(new ArrayList<T>() {{
                            addAll(page.getRecords());
                            addAll(newPage.getRecords());
                        }});
                    }
                }
            }
        }
        return page;
    }

    /** 往后添加搜索引擎 */
    public SearchEngineChannel<T> add(SearchEngine<T> searchEngine) {
        searchEngines.add(searchEngine);
        return this;
    }

    /** 往前添加搜索引擎 */
    public SearchEngineChannel<T> addFirst(SearchEngine<T> searchEngine) {
        List<SearchEngine<T>> newSearchEngines = new ArrayList<>();
        newSearchEngines.add(searchEngine);
        newSearchEngines.addAll(searchEngines);

        searchEngines = newSearchEngines;
        return this;
    }

    public SearchEngineChannel<T> addAll(SearchEngine<T> searchEngine) {
        searchEngines.add(searchEngine);
        return this;
    }
}
