package com.webapp.app_rest_api.controller;


import com.webapp.app_rest_api.model.entities.Post;
import com.webapp.app_rest_api.service.impl.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id){
        return postService.getPost(id);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable long id){
        postService.deletePost(id);
        return ("The post is successfully deleted");
    }

    @DeleteMapping
    public String deleteAllPost(){
        postService.deleteAllPost();
        return ("All posts are successfully deleted");
    }
}
