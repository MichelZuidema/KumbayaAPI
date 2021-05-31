package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.UserRepository;
import com.kumbaya.backendapi.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getUserById(@PathVariable Integer id) {
        if (userService.doesUserExist(id)) {
            User user = userRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, user));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Gebruiker is niet gevonden!"));
    }

    @PostMapping(path = "/signup")
    public @ResponseBody ResponseEntity signup(@RequestBody User user){
        ApiResponse validateUser = userService.validateUser(user);

        if(!validateUser.getSuccess()) {
            return ResponseEntity.ok(new ApiResponse(false, validateUser.getMessage()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User Added!", user));
    }
}
