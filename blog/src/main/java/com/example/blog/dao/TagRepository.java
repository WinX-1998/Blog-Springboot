package com.example.blog.dao;

import com.example.blog.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    public Tag findByName(String name);

    @Query("select t from Tag t")
    public List<Tag> findTop(Pageable pageable);
}
