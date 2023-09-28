package com.hello.World.service;

import com.hello.World.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User createUser(User user);
    User updateUser(long id, User user);
    User getUser(long id);

}
