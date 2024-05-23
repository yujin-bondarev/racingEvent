package com.example.racingevent.services;

import com.example.racingevent.model.entity.AbstractEntity;
import com.example.racingevent.model.entity.Viewer;

import java.util.List;

public interface Service<T extends AbstractEntity> {

    T readById(Long id);

    List<T> readByName(String name);

    List<T> read();

    void save(T entity);

    void delete(Long id);

    void edit(T entity);
}
