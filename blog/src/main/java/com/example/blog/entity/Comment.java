package com.example.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*评论类*/
@Entity
@Table(name = "t_comment")
@Data
@NoArgsConstructor
public class Comment {

    /*主键自增*/
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private Boolean adminComment;
    private String content;
    /*头像*/
    private String avatar;
    /*时间的格式*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment>replyComments=new ArrayList<Comment>();
    @ManyToOne
    private Comment parentComment;
}
