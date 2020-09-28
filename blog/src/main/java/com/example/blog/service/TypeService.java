package com.example.blog.service;

import com.example.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    public Type saveType(Type type);

    public Type getType(Long id);

    public Page<Type> listType(Pageable pageable);

    public List<Type>listTypeTop(Integer size);

    public Type updateType(Long id,Type type);

    public void deleteType(Long id);

    public Type getTypeByName(String name);

    public List<Type> listType();
}
