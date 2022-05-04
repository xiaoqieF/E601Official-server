package com.fxd.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private Integer blogId;
    private Integer parentCommentId;
    private List<Comment> replyComments = new ArrayList<>();
}