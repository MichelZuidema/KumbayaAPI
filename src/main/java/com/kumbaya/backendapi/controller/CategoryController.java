package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.CategoryRepository;
import com.kumbaya.backendapi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getCategoryById(@PathVariable Integer id) {
        if (categoryService.doesCategoryExist(id)) {
            Category category = categoryRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, category));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Category not found!"));
    }

    @GetMapping(path = "/name/{name}")
    public @ResponseBody
    ResponseEntity getCategoryByName(@PathVariable String name) {
        if (categoryService.doesCategoryExist(name)) {
            Category category = categoryRepository.findCategoryByName(name).get();

            return ResponseEntity.ok(new ApiResponse(true, category));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Category not found!"));
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addCategory(@RequestBody Category category) {
        ApiResponse validateCategory = categoryService.validateCategory(category);

        if(!validateCategory.getSuccess()) {
            return ResponseEntity.ok(new ApiResponse(false, validateCategory.getMessage()));
        }

        categoryRepository.save(category);

        return ResponseEntity.ok(new ApiResponse(true, "Category Added!", category));
    }
}
