package com.example.blog.service;

import com.example.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    public Tag saveTag(Tag tag);

    public Tag getTag(Long id);

    public Page<Tag> listTag(Pageable pageable);

    public List<Tag>listTag();

    public List<Tag>listTag(String ids);

    public List<Tag>listTagTop(Integer size);

    public Tag updateTag(Long id, Tag tag);

    public void deleteTag(Long id);

    public Tag getTagByName(String name);
}
