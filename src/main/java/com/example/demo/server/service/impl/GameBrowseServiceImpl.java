package com.example.demo.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.server.entity.GameBrowse;
import com.example.demo.server.entity.dto.GameDto;
import com.example.demo.server.mapper.GameBrowseMapper;
import com.example.demo.server.service.GameBrowseService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameBrowseServiceImpl extends ServiceImpl<GameBrowseMapper, GameBrowse>
        implements GameBrowseService {
    @Override
    public List<GameDto> statisticsBrowse() {
        return this.baseMapper.statisticsSpeed();
    }
}




