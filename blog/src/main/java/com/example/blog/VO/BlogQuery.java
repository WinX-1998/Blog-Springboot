package com.example.blog.VO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogQuery {
    public String title;
    public Long typeId;
    public boolean recommend;
}
