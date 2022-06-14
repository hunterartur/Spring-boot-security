package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Services<User> {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(User object) {
        repository.save(object);
    }

    @Override
    public void update(User object) {
        repository.save(object);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
