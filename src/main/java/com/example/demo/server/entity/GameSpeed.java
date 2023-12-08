package com.example.demo.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class GameSpeed implements Serializable {
    private Long gameId;
    private LocalDateTime speedDateTime;
}
