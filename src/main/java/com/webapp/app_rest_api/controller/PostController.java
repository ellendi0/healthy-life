package com.webapp.app_rest_api.controller;


import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.Post;
import com.webapp.app_rest_api.service.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private IPostService IPostService;

    public PostController(IPostService IPostService) {
        this.IPostService = IPostService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id){
        return new ResponseEntity<>(IPostService.getPost(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        return new ResponseEntity<>(IPostService.getAllPost( ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return new ResponseEntity<>(IPostService.createPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post post){
        return new ResponseEntity<>(IPostService.updatePost(id, post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        IPostService.deletePost(id);
        return new ResponseEntity<>("The post is successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPost(){
        IPostService.deleteAllPost();
        return new ResponseEntity<>("All posts are successfully deleted", HttpStatus.OK);
    }
}
