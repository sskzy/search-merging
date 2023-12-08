package com.example.demo.server.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.search.SearchEngine;
import com.example.demo.search.channel.SearchEngineChannel;
import com.example.demo.search.channel.SearchEngineExecuteChannel;
import com.example.demo.search.emun.DatabaseType;
import com.example.demo.search.handle.SearchEngineFilterHandle;
import com.example.demo.search.handle.SearchEngineProcessHandle;
import com.example.demo.search.handle.svper.SearchEngineHandle;
import com.example.demo.search.utils.MergeUtils;
import com.example.demo.server.entity.dto.GameDto;
import com.example.demo.server.service.GameBrowseService;
import com.example.demo.server.service.GameSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : songtc
 * @since : 2023/11/03 17:23
 */
@Configuration
public class SearchEngineConfig {

    @Resource
    GameBrowseService gameBrowseService;

    @Resource
    GameSpeedService gameSpeedService;

    /**
     * 组装搜索引擎
     */
    @Bean
    public SearchEngineChannel<GameDto> SearchEngineChannel() {
        return new SearchEngineChannel<GameDto>()
                .add(getGame1SearchEngine())
                .add(getGame2SearchEngine());
    }

    /**
     * mysql1
     */
    public SearchEngine<GameDto> getGame1SearchEngine() {
        return new SearchEngine<GameDto>()
                .setDatabaseType(DatabaseType.MYSQL)
                .setExecute(new SearchEngineExecuteChannel<SearchEngineHandle<GameDto>>()
                        .add(new SearchEngineFilterHandle<GameDto>() {
                            @Override
                            public Boolean filter(String name) {
                                // 过滤
                                return !StringUtils.hasLength(name);
                            }
                        })
                        .add(new SearchEngineProcessHandle<GameDto>() {
                                 @Override
                                 public Page<GameDto> process(String name) {
                                     List<GameDto> dtos1 = gameBrowseService.statisticsBrowse();
                                     List<GameDto> dtos2 = gameSpeedService.statisticsSpeed();

                                     for (int i = 0; i < dtos1.size(); i++) {
                                         MergeUtils.shallowCopyChoiceFieldState(dtos2.get(i), dtos1.get(i));
                                     }
                                     return new Page<GameDto>().setRecords(dtos1);
                                 }
                             }
                        ));
    }

    /**
     * mysql2
     */
    public SearchEngine<GameDto> getGame2SearchEngine() {
        return new SearchEngine<GameDto>()
                .setDatabaseType(DatabaseType.MYSQL)
                .setExecute(new SearchEngineExecuteChannel<SearchEngineHandle<GameDto>>()
                        .add(new SearchEngineProcessHandle<GameDto>() {
                                 @Override
                                 public Page<GameDto> process(String name) {
                                     List<GameDto> dtos1 = gameBrowseService.statisticsBrowse();
                                     List<GameDto> dtos2 = gameSpeedService.statisticsSpeed();

                                     for (int i = 0; i < dtos1.size(); i++) {
                                         MergeUtils.shallowCopyChoiceFieldState(dtos2.get(i), dtos1.get(i));
                                     }
                                     return new Page<GameDto>().setRecords(dtos1);
                                 }
                             }
                        ));
    }
}
