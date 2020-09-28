package com.example.blog.dao;

import com.example.blog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    public Type findByName(String name);


    @Query("select t from Type t")
    public List<Type> findTop(Pageable pageable);
}
