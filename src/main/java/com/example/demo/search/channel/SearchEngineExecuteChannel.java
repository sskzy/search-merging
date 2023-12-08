package com.example.demo.search.channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : songtc
 * @since : 2023/10/24 14:09
 */
public class SearchEngineExecuteChannel<T> {

    private List<T> executes = new ArrayList<>();

    public SearchEngineExecuteChannel<T> add(T t) {
        executes.add(t);
        return this;
    }

    public SearchEngineExecuteChannel<T> addFirst(T t) {
        List<T> newExecutes = new ArrayList<>();
        newExecutes.add(t);
        newExecutes.addAll(executes);

        executes = newExecutes;
        return this;
    }

    public SearchEngineExecuteChannel<T> addAll(List<T> t) {
        executes.addAll(t);
        return this;
    }

    public List<T> getExecutes() {
        return executes;
    }
}
