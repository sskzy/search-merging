package com.example.demo.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.server.entity.GameSpeed;
import com.example.demo.server.entity.dto.GameDto;

import java.util.List;


public interface GameSpeedService extends IService<GameSpeed> {

    List<GameDto> statisticsSpeed();
}
