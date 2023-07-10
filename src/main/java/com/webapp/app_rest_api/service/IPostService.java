package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Post;

import java.time.Instant;
import java.util.List;

public interface IPostService {
    public Post getPost(Long id);

    public List<Post> getAllPost();

    public Post createPost(Post post);

    public Post updatePost(Long id, Post post);

    public void deletePost(Long id);

    public void deleteAllPost();
}
