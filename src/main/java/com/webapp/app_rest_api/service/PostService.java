package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.mapper.PostMapper;
import com.webapp.app_rest_api.model.entities.Post;
import com.webapp.app_rest_api.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private PostMapper mapper;

    public PostService(PostRepository postRepository, PostMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    public Post getPost(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        post.setDate(Instant.now());
        return postRepository.save(post);
    }

    public Post updatePost(long id, Post post) {
        Post postUpdated = getPost(id);
        return postRepository.save(mapper.map(post, postUpdated));
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public void deleteAllPost() {
        postRepository.deleteAll();
    }
}
