package com.hello.World.controller;

import com.hello.World.model.dto.CommentDto;
import com.hello.World.model.entity.Comment;
import com.hello.World.repository.CommentRepository;
import com.hello.World.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ModelMapper modelMapper;
    private CommentService commentService;
    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
        Comment comment = commentService.createComment(commentRequest);
        CommentDto commentRepository = modelMapper.map(comment, CommentDto.class);
        return new ResponseEntity<CommentDto>(commentRepository, HttpStatus.CREATED);
    }
    @PutMapping("/create")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto){
        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
        Comment comment = commentService.updateComment(id, commentRequest);
        CommentDto commentRepository = modelMapper.map(comment, CommentDto.class);
        return ResponseEntity.ok().body(commentRepository);
    }
    @GetMapping
    public List<CommentDto> getAllComment(){
        return commentService.getAllComment()
                .stream().map(
                        comment -> modelMapper.map(comment, CommentDto.class)
                )
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long id){
        Comment comment = commentService.getComment(id);
        CommentDto commentRepository = modelMapper.map(comment, CommentDto.class);
        return ResponseEntity.ok().body(commentRepository);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CommentDto> getCommentByUser(@PathVariable("userId") Long userId){
        Comment comment = commentService.getComment(userId);
        CommentDto commentRepository = modelMapper.map(comment, CommentDto.class);
        return ResponseEntity.ok().body(commentRepository);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPost(@PathVariable("postId") Long postId){
        Comment comment = commentService.getComment(postId);
        CommentDto commentRepository = modelMapper.map(comment, CommentDto.class);
        return ResponseEntity.ok().body(commentRepository);
    }
}
