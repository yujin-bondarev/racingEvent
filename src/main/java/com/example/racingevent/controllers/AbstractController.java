package com.example.racingevent.controllers;

import com.example.racingevent.model.entity.AbstractEntity;
import com.example.racingevent.services.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<T extends AbstractEntity> {
    protected HttpHeaders headers;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @PreAuthorize("hasAnyRole(\"USER\", \"ADMIN\")")
    @GetMapping
    public ResponseEntity<List<T>> get() {
        List<T> entities = getService().read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole(\"USER\", \"ADMIN\")")
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable long id) {
        T entity = getService().readById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole(\"USER\", \"ADMIN\")")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<T>> getByName(@PathVariable String name) {
        List<T> entity = getService().readByName(name);
        if (entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody T entity) {
        try {
            getService().save(entity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestBody T entity) {
        try {
            getService().edit(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public abstract Service<T> getService();
}

