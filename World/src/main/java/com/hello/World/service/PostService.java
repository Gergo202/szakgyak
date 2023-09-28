package com.hello.World.service;

import com.hello.World.model.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();
    Post createPost(Post post);
    Post updatePost(long id, Post post);
    Post getPost(long id);
}
