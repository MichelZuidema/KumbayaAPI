package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.CategoryRepository;
import com.kumbaya.backendapi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        if (doesCategoryExist(id)) {
            Category category = categoryRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, category));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Categorie is niet gevonden!"));
    }

    @GetMapping(path = "/name/{name}")
    public @ResponseBody
    ResponseEntity getCategoryByName(@PathVariable String name) {
        if (doesCategoryExist(name)) {
            Category category = categoryRepository.findCategoryByName(name).get();

            return ResponseEntity.ok(new ApiResponse(true, category));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Categorie is niet gevonden!"));
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

    /**
     * This method checks if a category exists with the specific id
     *
     * @param categoryId Unique ID of the category who has to be checked
     * @return Boolean If category exists
     */
    public Boolean doesCategoryExist(Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        return categoryOptional.isPresent();
    }

    /**
     * This method checks if a category exists with a specific name
     *
     * @param categoryName Unique name of the category who has to be checked
     * @return Boolean If category exists
     */
    public Boolean doesCategoryExist(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName);

        return categoryOptional.isPresent();
    }
}
