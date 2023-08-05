package com.webapp.app_rest_api.controller;


import com.webapp.app_rest_api.controller.facade.PostFacade;
import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostFacade postFacade;

    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping("{id}")
    public PostDto getPostById(@AuthenticationPrincipal User user,
                               @PathVariable Long id){
        return postFacade.getPost(user, id);
    }

    @GetMapping
    public List<PostDto> getAllPost(@AuthenticationPrincipal User user){
        return postFacade.getAllPost(user);
    }

    @PostMapping
    public PostDto createPost(@AuthenticationPrincipal User user,
                              @Valid @RequestBody PostDto postDto){
        return postFacade.createPost(user, postDto);
    }

    @PutMapping("{id}")
    public PostDto updatePost(@AuthenticationPrincipal User user,
                              @Valid @PathVariable Long id,
                              @RequestBody PostDto postDto){
        return postFacade.updatePost(user, id, postDto);
    }

    @DeleteMapping("{id}")
    public PostDto deletePost(@AuthenticationPrincipal User user,
                              @PathVariable Long id){
        return postFacade.deletePost(user, id);
    }

    @DeleteMapping
    public String deleteAllPost(@AuthenticationPrincipal User user){
        return postFacade.deleteAllPost(user);
    }
}
