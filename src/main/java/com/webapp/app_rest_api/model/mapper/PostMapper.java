package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.entities.Post;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PostMapper {
    public Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDate(postDto.getDate());
        return post;
    }

    public PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDate(post.getDate());
        return postDto;
    }

    public Post map(Post post, Post postUpdated){
        postUpdated.setTitle(post.getTitle());
        postUpdated.setContent(post.getContent());
        postUpdated.setDate(Instant.now());
        return postUpdated;
    }
}
