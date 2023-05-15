package com.domain.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.domain.model.entities.Posts;

public interface PostsRepo extends JpaRepository<Posts, Long> {
    
}
