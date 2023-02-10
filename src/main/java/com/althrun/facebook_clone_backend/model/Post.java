package com.althrun.facebook_clone_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    private String id;
    private  String post;
    private  String name;
    private String email;
    private String image;
    private String profilePic;
    private String timeStamp;
    private String file;
}
