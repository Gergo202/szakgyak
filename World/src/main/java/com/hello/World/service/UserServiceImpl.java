package com.hello.World.service;

import com.hello.World.model.entity.User;
import com.hello.World.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(long id, User user) {
        User userActual = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"));
        userActual.setUsername(user.getUsername());
        userActual.setEmail(user.getEmail());
        userActual.setPassword(user.getPassword());
        return userRepository.saveAndFlush(userActual);
    }

    @Override
    public User getUser(long id) {
        Optional<User> userResult = userRepository.findById(id);
        return userResult.get();
    }
}
