package com.althrun.facebook_clone_backend.service;

import com.althrun.facebook_clone_backend.model.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post) throws Exception;

    List<Post> getPost();
}
