package com.webapp.app_rest_api.controller;


import com.webapp.app_rest_api.controller.facade.PostFacade;
import com.webapp.app_rest_api.dto.PostDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostFacade postFacade;

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
    public PostDto createPost(@Valid @RequestBody PostDto postDto){
        return postFacade.createPost(postDto);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@Valid@PathVariable Long id, @RequestBody PostDto postDto){
        return postFacade.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public PostDto deletePost(@PathVariable Long id){
        System.out.println("The post with id " + id + " was deleted.");
        return postFacade.deletePost(id);
    }

    @DeleteMapping
    public String deleteAllPost(){
        postFacade.deleteAllPost();
        return ("All posts are successfully deleted");
    }
}
