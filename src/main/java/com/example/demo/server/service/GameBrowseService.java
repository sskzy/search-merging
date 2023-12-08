package com.example.demo.server.service;

import com.example.demo.server.entity.GameBrowse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.server.entity.dto.GameDto;

import java.util.List;


public interface GameBrowseService extends IService<GameBrowse> {
    List<GameDto> statisticsBrowse();
}
