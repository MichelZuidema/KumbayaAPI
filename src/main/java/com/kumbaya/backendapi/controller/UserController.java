package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.UserRepository;
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

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getUserById(@PathVariable Integer id) {
        if (doesUserExist(id)) {
            User user = userRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, user));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Gebruiker is niet gevonden!"));
    }

    /**
     * This method checks if a user exists with the specific id
     * @param userId Unique ID of the user who has to be checked
     * @return Boolean If user exists
     */
    public Boolean doesUserExist(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.isPresent();
    }
}
