package com.example.blog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*留言类*/
@Entity
@Table(name = "t_message")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    /*主键自增*/
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private Boolean adminMessage;
    private String content;
    /*头像*/
    private String avatar;
    /*时间的格式*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @OneToMany(mappedBy = "parentMessage")
    private List<Message>replyMessages=new ArrayList<Message>();
    @ManyToOne
    private Message parentMessage;
}
