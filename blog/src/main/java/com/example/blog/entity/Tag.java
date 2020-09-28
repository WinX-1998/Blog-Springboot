package com.example.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*标签类*/
    @Entity
    @Table(name = "t_tag")
    @Data
    @NoArgsConstructor
    public class Tag {
        /*主键自增*/
        @Id
        @GeneratedValue
        private Long id;
        private String name;
        @ManyToMany(mappedBy = "tags")
        private List<Blog> blogs= new ArrayList<Blog>();
}
