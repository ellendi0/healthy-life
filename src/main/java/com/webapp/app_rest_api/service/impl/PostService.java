package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Post;
import com.webapp.app_rest_api.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service

public class PostService implements com.webapp.app_rest_api.service.IPostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPost(long id){
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
    }

    @Override
    public List<Post> getAllPost(int pageNo, int pageSize) {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        return postList;
    }

    @Override
    public Post createPost(Post post) {
        post.setDate(Instant.now());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, Post post) {
        Post putPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        putPost.setTitle(post.getTitle());
        putPost.setContent(post.getContent());
        post.setDate(Instant.now());
        return postRepository.save(putPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
