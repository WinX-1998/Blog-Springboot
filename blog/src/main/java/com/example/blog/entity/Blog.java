package com.example.blog.entity;




import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_blog")
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    /*标题*/
    private String title;
    /*内容*/
    private String content;
    /*首图*/
    private String firstPicture;
    /*标记原创、转载*/
    private String flag;
    /*浏览数*/
    private Integer view;
    /*赞赏，转载声明，评论，推荐是否打开*/
    private boolean appreciation;
    private boolean shareContent;
    /*评论*/
    private boolean commentabled;
    private boolean recommend;
    /*发布还是保存草稿*/
    private boolean published;
    /*创建或者更新时间*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /*外键关系,这里是多的一端*/
    @ManyToOne
    private Type type;
    /*级联新增，增加一个博客里面有新的tag时会相应在tag表中新增*/
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag>tags=new ArrayList<Tag>();
    /*不在数据库映射*/
    @Transient
    private String tagIds;

    public void init(){
        this.tagIds=tagsToIds(this.getTags()) ;
    }

    private String tagsToIds(List<Tag>tags){
        if(!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for(Tag tag:tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }


    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog",fetch = FetchType.EAGER)
    private List<Comment>comments=new ArrayList<Comment>();

    /*博客放在首页的描述内容*/
    private String description;
}
