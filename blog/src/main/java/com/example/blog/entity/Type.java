package com.example.blog.entity;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_type")
@Data
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<Blog>();
}
