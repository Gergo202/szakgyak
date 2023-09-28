package com.hello.World.controller;

import com.hello.World.model.dto.PostDto;
import com.hello.World.model.entity.Post;
import com.hello.World.repository.PostRepository;
import com.hello.World.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private ModelMapper modelMapper;
    private PostService postService;
    private PostRepository postRepository;
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        Post postRequest = modelMapper.map(postDto, Post.class);
        Post post = postService.createPost(postRequest);
        PostDto postRepository = modelMapper.map(post, PostDto.class);
        return new ResponseEntity<PostDto>(postRepository, HttpStatus.CREATED);
    }
    @PutMapping("/create")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id,
                                              @RequestBody PostDto postDto){
        Post postRequest = modelMapper.map(postDto, Post.class);
        Post post = postService.updatePost(id, postRequest);
        PostDto postRepository = modelMapper.map(post, PostDto.class);
        return ResponseEntity.ok().body(postRepository);
    }
    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost()
                .stream().map(
                        post -> modelMapper.map(post, PostDto.class)
                )
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long id){
        Post post = postService.getPost(id);
        PostDto postRepository = modelMapper.map(post, PostDto.class);
        return ResponseEntity.ok().body(postRepository);
    }
    /*@GetMapping("/{userId}")
    public ResponseEntity<PostDto> getPostUser(@PathVariable("postId") Long postId){
        Post post = postService.getPostUser(postId);
        PostDto postRepository = modelMapper.map(post, PostDto.class);
        return ResponseEntity.ok().body(postRepository);
    }*/
}
