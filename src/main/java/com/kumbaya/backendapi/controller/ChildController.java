package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Child;
import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.ChildRepository;
import com.kumbaya.backendapi.repository.UserRepository;
import com.kumbaya.backendapi.service.ChildService;
import com.kumbaya.backendapi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/child")
public class ChildController {
    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChildService childService;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getChildById(@PathVariable Integer id) {
        if (childService.doesChildExist(id)) {
            Child child = childRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, child));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Child not found!"));
    }

    @GetMapping(path = "parent/{parentId}")
    public @ResponseBody
    ResponseEntity getChildByParent(@PathVariable Integer parentId) {
        if (userService.doesUserExist(parentId)) {
            User parent = userRepository.findById(parentId).get();

            List<Child> children = childRepository.findByParent(parent);

            return ResponseEntity.ok(new ApiResponse(true, children));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Parent not found!"));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Child> getAllChildren()
    {
        return childRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity addChild(@RequestBody Child child) {
        ApiResponse validateChild = childService.validateChild(child);

        if(!validateChild.getSuccess()) {
            return ResponseEntity.ok(new ApiResponse(false, validateChild.getMessage()));
        }

        childRepository.save(child);

        return ResponseEntity.ok(new ApiResponse(true, "Child Added!", child));
    }
}
