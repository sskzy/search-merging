package com.example.demo.search;

import com.example.demo.search.channel.SearchEngineExecuteChannel;
import com.example.demo.search.emun.DatabaseType;
import com.example.demo.search.handle.svper.SearchEngineHandle;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : songtc
 * @since : 2023/10/24 11:42
 */
@Data
@Accessors(chain = true)
public class SearchEngine<T> {

    private DatabaseType databaseType;

    private SearchEngineExecuteChannel<SearchEngineHandle<T>> execute;
}
