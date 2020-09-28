package com.example.blog.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*用户类*/
@Entity
@Table(name = "t_user")
@Data
@NoArgsConstructor
public class User {

    /*主键*/
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    /*头像*/
    private String avatar;
    /*类型：是否管理员*/
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(mappedBy = "user")
    private List<Blog>blogs=new ArrayList<Blog>();
}
