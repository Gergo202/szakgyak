package com.hello.World.service;

import com.hello.World.model.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();
    Comment createComment(Comment comment);
    Comment updateComment(long id, Comment comment);
    Comment getComment(long id);
}
