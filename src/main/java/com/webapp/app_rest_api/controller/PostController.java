package com.webapp.app_rest_api.controller;


import com.webapp.app_rest_api.controller.facade.PostFacade;
import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.entities.Post;
import com.webapp.app_rest_api.service.impl.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostFacade postFacade;

    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id){
        return postFacade.getPost(id);
    }

    @GetMapping
    public List<PostDto> getAllPost(){
        return postFacade.getAllPost();
    }

    @PostMapping
    public PostDto createPost(@RequestBody Post post){
        return postFacade.createPost(post);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody Post post){
        return postFacade.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public PostDto deletePost(@PathVariable long id){
        System.out.println("The post with id " + id + " was deleted.");
        return postFacade.deletePost(id);
    }

    @DeleteMapping
    public String deleteAllPost(){
        postFacade.deleteAllPost();
        return ("All posts are successfully deleted");
    }
}
