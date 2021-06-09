package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Profession;
import com.kumbaya.backendapi.entity.UserProfession;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.ProfessionRepository;
import com.kumbaya.backendapi.repository.UserProfessionRepository;
import com.kumbaya.backendapi.repository.UserRepository;
import com.kumbaya.backendapi.service.ProfessionService;
import com.kumbaya.backendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/userprofession")
public class UserProfessionController {
    @Autowired
    private UserProfessionRepository userProfessionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    ResponseEntity getProfessionsByUserId(@PathVariable Integer id) {
        if(userService.doesUserExist(id)) {
            List<UserProfession> userProfessionList = userProfessionRepository.findProfessionByUserId(id);

            return ResponseEntity.ok(new ApiResponse(true, userProfessionList));
        }

        return ResponseEntity.ok(new ApiResponse(false, "User not found!"));
    }

    @PostMapping(path = "/add/{userId}/{professionId}")
    public @ResponseBody
    ResponseEntity addProfessionToUser(@PathVariable Integer userId, @PathVariable Integer professionId) {
        if(userService.doesUserExist(userId)) {
            if(professionService.doesProfessionExist(professionId)) {
                UserProfession userProfession = new UserProfession();
                userProfession.setUser(userRepository.findById(userId).get());
                userProfession.setProfession(professionRepository.findById(professionId).get());
                userProfessionRepository.save(userProfession);

                return ResponseEntity.ok(new ApiResponse(true,"Profession added!",  userProfession));
            } else {
                return ResponseEntity.ok(new ApiResponse(false, "Profession not found!"));
            }
        }

        return ResponseEntity.ok(new ApiResponse(false, "User not found!"));
    }
}