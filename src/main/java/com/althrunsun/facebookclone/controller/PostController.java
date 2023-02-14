package com.althrunsun.facebookclone.controller;

import com.althrunsun.facebookclone.model.Post;
import com.althrunsun.facebookclone.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post addPost(@RequestParam Map<String,String> requestParams
    ) throws Exception {
        String strPost = requestParams.get("post");
        String email = requestParams.get("email");
        String name = requestParams.get("name");
        String file = requestParams.get("file");
        String profilePic = requestParams.get("profilePic");

        Post post = Post.builder()
                .file(file)
                .name(name)
                .email(email)
                .post(strPost)
                .profilePic(profilePic)
                .timeStamp(new Date().toString())
                .build();
        post = postService.addPost(post);
        return post;
    }

    @GetMapping
    public List<Post> getPost() {
        return postService.getPost();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePost(@PathVariable String id){
        boolean deleted =false;
        deleted = postService.deletePost(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",deleted);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id){
        Post post = null;
        post =postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id,@RequestBody Post post){
        post = postService.updatePost(id,post);
        return ResponseEntity.ok(post);
    }
}