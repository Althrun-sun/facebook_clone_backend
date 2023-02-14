package com.althrunsun.facebookclone.service;

import com.althrunsun.facebookclone.entity.PostEntity;
import com.althrunsun.facebookclone.model.Post;
import com.althrunsun.facebookclone.repository.PostEntityRepository;
import com.althrunsun.facebookclone.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    //    public PostServiceImpl(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    private PostEntityRepository postEntityRepository;

    public PostServiceImpl(PostEntityRepository postEntityRepository,PostRepository postRepository) {
        this.postEntityRepository = postEntityRepository;
        this.postRepository = postRepository;
    }


    @Override
    public Post addPost(Post post) throws Exception {
        try {

            PostEntity postEntity = new PostEntity();
            BeanUtils.copyProperties(post, postEntity);
            if (post.getFile() != null && !post.getFile().equalsIgnoreCase("null"))
                postEntity.setImage(post.getFile());
            else
                postEntity.setImage(null);

            postEntity = postEntityRepository.save(postEntity);

            post.setId(postEntity.getId());
            post.setFile(null);
            post.setImage(postEntity.getImage());

        } catch (Exception e) {
            throw new Exception("Could not save Post: " + e);
        }
        return post;
    }

    @Override
    public List<Post> getPost() {
        List<PostEntity> postEntities
                = postEntityRepository.findAll();

        List<Post> posts = new ArrayList<>();
        posts = postEntities.stream()
                .map((postEntity) ->
                        Post.builder()
                                .id(postEntity.getId())
                                .timeStamp(postEntity.getTimeStamp())
                                .email(postEntity.getEmail())
                                .name(postEntity.getName())
                                .post(postEntity.getPost())
                                .image(postEntity.getImage())
                                .profilePic(postEntity.getProfilePic())
                                .build()
                ).collect(Collectors.toList());
        return posts;
    }


    @Override
    public boolean deletePost(String id) {
        PostEntity post = postRepository.findById(id).get();
        postRepository.delete(post);
        return true;
    }

    @Override
    public Post getPostById(String id) {
        PostEntity postEntity = postEntityRepository.findById(id).get();
        Post post = new Post();
        BeanUtils.copyProperties(postEntity,post);
        return post;
    }

    @Override
    public Post updatePost(String id, Post post) {
        PostEntity postEntity = postEntityRepository.findById(id).get();
        postEntity.setPost(post.getPost());
//        postEntity.setImage(post.getImage());
        postEntityRepository.save(postEntity);
        return post;
    }


}