package com.domain.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.domain.model.entities.Posts;
import com.domain.model.repo.PostsRepo;

@Service
@Transactional
public class PostsService {
    
    @Autowired
    private PostsRepo postsRepo;


    public Posts save(Posts product) {
        return postsRepo.save(product);
    }

    public Posts findOne(Long id) {
        Optional<Posts> product = postsRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public void removeOne(Long id){
        postsRepo.deleteById(id);
    }


   

}
