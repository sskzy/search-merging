package com.example.demo.search.emun;

/**
 * @author : songtc
 * @since : 2023/10/24 11:57
 */
public enum DatabaseType {
    /**
     * redis
     */
    REDIS("redis", 1),

    /**
     * mysql
     */
    MYSQL("mysql", 2),

    /**
     * elasticSearch
     */
    ELASTICSEARCH("elasticSearch", 3),

    /**
     * clickhouse
     */
    CLICKHOUSE("clickhouse",4);

    private final String name;
    private final Integer code;

    DatabaseType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
