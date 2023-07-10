package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.mapper.PostMapper;
import com.webapp.app_rest_api.model.entities.Post;
import com.webapp.app_rest_api.repository.PostRepository;
import com.webapp.app_rest_api.service.IPostService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final PostMapper mapper;

    public PostService(PostRepository postRepository, PostMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        post.setDate(Instant.now());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post postUpdated = getPost(id);
        return postRepository.save(mapper.map(post, postUpdated));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPost() {
        postRepository.deleteAll();
    }
}
