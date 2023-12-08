package com.example.demo.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GameBrowse implements Serializable {
    private Long gameId;
    private LocalDateTime browseDateTime;
}
