package com.hello.World.service;

import com.hello.World.model.entity.Post;
import com.hello.World.repository.PostRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Post updatePost(long id, Post post) {
        Post postActual = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post"));
        postActual.setDescription(postActual.getDescription());
        postActual.setCreatedAt(postActual.getCreatedAt());
        return postRepository.saveAndFlush(postActual);
    }

    @Override
    public Post getPost(long id) {
        Optional<Post> postResult = postRepository.findById(id);
        return postResult.get();
    }
}
