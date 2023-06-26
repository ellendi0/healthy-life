package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
}
