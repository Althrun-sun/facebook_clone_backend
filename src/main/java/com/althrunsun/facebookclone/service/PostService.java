package com.althrunsun.facebookclone.service;

import com.althrunsun.facebookclone.model.Post;

import java.util.List;

public interface PostService {


    Post addPost(Post post) throws Exception;

    List<Post> getPost();


    boolean deletePost(String id);

    Post getPostById(String id);

    Post updatePost(String id, Post post);
}