package com.example.demo.server.mapper;

import com.example.demo.server.entity.GameBrowse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.server.entity.dto.GameDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameBrowseMapper extends BaseMapper<GameBrowse> {

    List<GameDto> statisticsSpeed();
}




