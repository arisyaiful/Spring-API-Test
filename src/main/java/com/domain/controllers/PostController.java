package com.domain.controllers;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ApiResponse;
import com.domain.dto.ResponseData;
import com.domain.helpers.UserAuthorize;
import com.domain.model.entities.Posts;
import com.domain.model.repo.PostsRepo;
import com.domain.services.PostsService;


@RestController
@RequestMapping("/api/posts")
@SuppressWarnings("rawtypes")
public class PostController {

    private static ApiResponse apiResponse = new ApiResponse();
    private static Map<String, String> messageDesc = new LinkedHashMap<>();

    @Autowired
    UserAuthorize userAuthorize;
    
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepo postsRepo;


    @PostMapping
    public ResponseEntity<ResponseData<Posts>> create(@Valid @RequestBody Posts posts, Errors errors) {
        ResponseData<Posts> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(postsService.save(posts));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping(params = { "sort", "page", "size" })
    public ResponseEntity findAll(@RequestParam("sort") String sort, @RequestParam("page") int page, 
    @RequestParam("size") int size){
        boolean isAuth = userAuthorize.getAuthAccess();
        if(isAuth==true){ 
 
            Page<Posts> posts = postsRepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort)));
            
            apiResponse.setApiResponse(posts.getSize(), page, size, "success", true, posts);
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setApiResponse(0, 0, 0, "error", false, messageDesc.put("message", "Unauthorized Access"));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public Posts findById(@PathVariable("id") Long id){
        return postsService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Posts>> update(@Valid @RequestBody Posts posts, Errors errors) {
       
        ResponseData<Posts> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        responseData.setStatus(true);
        responseData.setPayload(postsService.save(posts));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        postsService.removeOne(id);
    }
}
