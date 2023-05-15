package com.domain.demoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.domain.model.entities.Category;
import com.domain.model.entities.Posts;
import com.domain.model.repo.CategoryRepo;
import com.domain.model.repo.PostsRepo;

@DataJpaTest
class PostsServiceTest {
    
    @Autowired
    private PostsRepo postsRepo;

    @Autowired
    private CategoryRepo catRepo;

    @Test
        void save_insert_new_category() {
            // Given
            Category newCat = new Category();
            newCat.setName("Featured");
            // When
            Category persistedCat = this.catRepo.save(newCat);
            // Then
            assertNotNull(persistedCat);
            assertEquals(1, persistedCat.getId());
        }
 
    @Test
    void findAll_posts_list() {
        // When
        List<Posts> posts = this.postsRepo.findAll();
        // Then
        assertEquals(0, posts.size());
    }
}

