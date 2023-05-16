package com.domain.controllers;


import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    @Cacheable("posts_data")
    public ResponseEntity create(@Valid @RequestBody Posts posts) {
        boolean isAuth = userAuthorize.getAuthAccess();
        if(isAuth==true){

            postsService.save(posts);

            apiResponse.setApiResponse(0, 0, 0, "success", true, posts);
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setApiResponse(0, 0, 0, "error", false, messageDesc.put("messages", "Unauthorized Access"));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(params = { "sort", "page", "size" })
    @Cacheable("get_data")
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
    @Cacheable("get_detail")
    public ResponseEntity findById(@PathVariable("id") Long id){
        boolean isAuth = userAuthorize.getAuthAccess();
        if(isAuth==true){
            Posts data = postsService.findOne(id);

            apiResponse.setApiResponse(1, 0, 0, "success", true, data);
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setApiResponse(0, 0, 0, "error", false, messageDesc.put("message", "Unauthorized Access"));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    @Cacheable("update_data")
    public ResponseEntity update(@Valid @RequestBody Posts posts) {
        boolean isAuth = userAuthorize.getAuthAccess();
        if(isAuth==true){
            postsService.save(posts);

            apiResponse.setApiResponse(0, 0, 0, "success", true, posts);
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setApiResponse(0, 0, 0, "error", false, messageDesc.put("message", "Unauthorized Access"));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    @Cacheable("delete_data")
    public ResponseEntity removeOne(@PathVariable("id") Long id){
        boolean isAuth = userAuthorize.getAuthAccess();
        if(isAuth==true){

            postsService.removeOne(id);

            apiResponse.setApiResponse(1, 0, 0, "success", true, messageDesc.put("message", "Data has been deleted"));
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setApiResponse(0, 0, 0, "error", false, messageDesc.put("message", "Unauthorized Access"));
        return ResponseEntity.ok(apiResponse);
    }
}
