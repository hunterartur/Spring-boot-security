package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface Services<T> {
    User getById(Long id);
    List<T> getAll();
    void save(T object);
    void update(T object);
    void removeById(Long id);
}
