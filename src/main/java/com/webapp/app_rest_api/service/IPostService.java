package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.PostDto;
import com.webapp.app_rest_api.model.Post;

import java.util.List;

public interface IPostService {
    Post getPost(long id);
    List<Post> getAllPost();
    Post createPost(Post post);
    Post updatePost(long id, Post post);
    void deletePost(long id);
    void deleteAllPost();

}
