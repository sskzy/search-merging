package com.example.demo;

import com.example.demo.search.channel.SearchEngineChannel;
import com.example.demo.server.entity.dto.GameDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource(name = "gameSearch")
    private SearchEngineChannel<GameDto> searchEngineChannel;

    @Test
    void contextLoads() {
    }

}
