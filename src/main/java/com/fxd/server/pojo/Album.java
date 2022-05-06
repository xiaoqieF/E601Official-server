package com.fxd.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private Long id;
    private String title;
    private String device;
    private int views;
    private int like;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String urls;

    private Long userId;
    private User user;
}
