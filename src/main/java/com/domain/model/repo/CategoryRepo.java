package com.domain.model.repo;

import org.springframework.data.repository.CrudRepository;

import com.domain.model.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    
}
