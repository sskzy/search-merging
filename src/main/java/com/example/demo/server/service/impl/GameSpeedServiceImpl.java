package com.example.demo.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.server.entity.GameSpeed;
import com.example.demo.server.entity.dto.GameDto;
import com.example.demo.server.service.GameSpeedService;
import com.example.demo.server.mapper.GameSpeedMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameSpeedServiceImpl extends ServiceImpl<GameSpeedMapper, GameSpeed>
    implements GameSpeedService{

    @Override
    public List<GameDto> statisticsSpeed() {
        return this.baseMapper.statisticsSpeed();
    }
}




