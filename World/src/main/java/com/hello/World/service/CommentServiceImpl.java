package com.hello.World.service;

import com.hello.World.model.entity.Comment;
import com.hello.World.repository.CommentRepository;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment updateComment(long id, Comment comment) {
        Comment commentActual = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment"));
        commentActual.setComment(commentActual.getComment());
        commentActual.setCreatedAt(commentActual.getCreatedAt());
        return commentRepository.saveAndFlush(commentActual);
    }

    @Override
    public Comment getComment(long id) {
        Optional<Comment> commentResult = commentRepository.findById(id);
        return commentResult.get();
    }
}
