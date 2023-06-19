package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.mapper.PostMapper;
import com.webapp.app_rest_api.model.Post;
import com.webapp.app_rest_api.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service

public class PostService implements com.webapp.app_rest_api.service.IPostService {

    private PostRepository postRepository;
    private PostMapper mapper;

    public PostService(PostRepository postRepository, PostMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public Post getPost(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll().stream().toList();
    }

    @Override
    public Post createPost(Post post) {
        post.setDate(Instant.now());
        return postRepository.save(post);
    }


    @Override
    public Post updatePost(long id, Post post) {
        Post postUpdated = getPost(id);
        postUpdated.setTitle(postUpdated.getTitle());
        postUpdated.setContent(postUpdated.getContent());
        postUpdated.setDate(Instant.now());
        return postRepository.save(postUpdated);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPost() {
        postRepository.deleteAll();
    }
}
