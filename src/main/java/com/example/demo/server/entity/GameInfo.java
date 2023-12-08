package com.example.demo.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


@Data
public class GameInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String gameName;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
