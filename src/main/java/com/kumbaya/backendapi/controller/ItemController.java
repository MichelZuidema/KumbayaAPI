package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.entity.Item;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.CategoryRepository;
import com.kumbaya.backendapi.repository.ItemRepository;
import com.kumbaya.backendapi.service.CategoryService;
import com.kumbaya.backendapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getItemById(@PathVariable Integer id) {
        if (itemService.doesItemExist(id)) {
            Item item = itemRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, item));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Item not found!"));
    }

    @GetMapping(path = "/{categoryId}/items")
    public @ResponseBody
    ResponseEntity getItemsByCategory(@PathVariable Integer categoryId) {
        if(categoryService.doesCategoryExist(categoryId)) {
            Category category = categoryRepository.findById(categoryId).get();

            List<Item> items = itemRepository.findByCategory(category);

            return ResponseEntity.ok(new ApiResponse(true, items));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Category not found!"));
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addItem(@RequestBody Item item) {
        ApiResponse validateItem = itemService.validateItem(item);

        if (!validateItem.getSuccess()) {
            return ResponseEntity.ok(new ApiResponse(false, validateItem.getMessage()));
        }

        itemRepository.save(item);

        return ResponseEntity.ok(new ApiResponse(true, "Item Added!", item));
    }
}
