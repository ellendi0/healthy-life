package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.model.mapper.PostMapper;
import com.webapp.app_rest_api.service.impl.PostService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFacade {
    private final PostService postService;
    private final PostMapper postMapper;

    public PostFacade(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    public PostDto createPost(User user, PostDto postDto) {
        return postMapper.mapToDto(postService.createPost(user.getPersonalInfo(), postMapper.mapToEntity(postDto)));
    }

    public PostDto getPost(User user, Long id) {
        return postMapper.mapToDto(postService.getPost(user.getPersonalInfo(), id));
    }

    public List<PostDto> getAllPost(User user) {
        return postService.getAllPost(user.getPersonalInfo()).stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    public PostDto updatePost(User user, Long id, PostDto postDto) {
        return postMapper.mapToDto(postService.updatePost(user.getPersonalInfo(), id, postMapper.mapToEntity(postDto)));
    }

    public PostDto deletePost(User user, Long id) {
        PostDto postDto = postMapper.mapToDto(postService.getPost(user.getPersonalInfo(), id));
        postService.deletePost(user.getPersonalInfo(), id);
        System.out.println("The post with id " + id + " was deleted.");
        return postDto;
    }

    public String deleteAllPost(User user) {
        postService.deleteAllPost(user.getPersonalInfo());
        return ("All posts are successfully deleted");
    }
}
