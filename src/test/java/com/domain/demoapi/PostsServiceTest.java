package com.domain.demoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.domain.model.entities.Posts;
import com.domain.model.repo.PostsRepo;

@DataJpaTest
class PostsServiceTest {
    
    @Autowired
    private PostsRepo postsRepo;


    @Test
        void save_insert_new_post() {
            // Given
            Posts newPost = new Posts();
            newPost.setTitle("Test Judul 1");
            newPost.setDescription("Deksripsi test");
            newPost.setContent("Kontent Judul 1");
            // When
            Posts persistedPost = this.postsRepo.save(newPost);
            // Then
            assertNotNull(persistedPost);
            assertEquals(1, persistedPost.getId());
        }
 
    @Test
    void findAll_posts_list() {
        // When
        List<Posts> posts = this.postsRepo.findAll();
        // Then
        assertEquals(0, posts.size());
    }
}

