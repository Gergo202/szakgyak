package com.hello.World.controller;

import com.hello.World.model.dto.UserDto;
import com.hello.World.model.entity.User;
import com.hello.World.repository.UserRepository;
import com.hello.World.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    private UserService userService;
    private UserRepository userRepository;


    public UserController(UserRepository userRepository,
                          UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.createUser(userRequest);
        UserDto userRepository = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<UserDto>(userRepository, HttpStatus.CREATED);
    }

    @PutMapping("/create")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id,
                                              @RequestBody UserDto userDto){
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.updateUser(id, userRequest);
        UserDto userRepository = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userRepository);
    }

    @GetMapping("/allusers")
    public List<UserDto> getAllUser(){

        return userService.getAllUser()
                .stream().map(
                        users -> modelMapper.map(users, UserDto.class)
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        UserDto userRepository = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userRepository);
    }
}
