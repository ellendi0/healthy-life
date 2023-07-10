package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.PostDto;
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

    public PostDto createPost(PostDto postDto) {
        return postMapper.mapToDto(postService.createPost(postMapper.mapToEntity(postDto)));
    }

    public PostDto getPost(Long id) {
        return postMapper.mapToDto(postService.getPost(id));
    }

    public List<PostDto> getAllPost() {
        return postService.getAllPost().stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        return postMapper.mapToDto(postService.updatePost(id, postMapper.mapToEntity(postDto)));
    }

    public PostDto deletePost(Long id) {
        PostDto postDto = postMapper.mapToDto(postService.getPost(id));
        postService.deletePost(id);
        return postDto;
    }

    public void deleteAllPost() {
        postService.deleteAllPost();
    }
}
