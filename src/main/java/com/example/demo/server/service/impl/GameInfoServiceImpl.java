package com.example.demo.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.server.entity.GameInfo;
import com.example.demo.server.service.GameInfoService;
import com.example.demo.server.mapper.GameInfoMapper;
import org.springframework.stereotype.Service;


@Service
public class GameInfoServiceImpl extends ServiceImpl<GameInfoMapper, GameInfo>
    implements GameInfoService{

}




