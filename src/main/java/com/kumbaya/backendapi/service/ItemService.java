package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.entity.Item;
import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.CategoryRepository;
import com.kumbaya.backendapi.repository.ItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse validateItem(Item itemRequest) {
        if(StringUtils.isAnyEmpty(itemRequest.getName(), itemRequest.getDescription(), itemRequest.getPrice().toString())) {
            return new ApiResponse(false, "The creation request was not valid, please try again.");
        }

        if(itemRequest.getCategory() == null) {
            return new ApiResponse(false, "The given category is not available");
        }

        Optional<Category> category = categoryRepository.findById(itemRequest.getCategory().getId());

        if(!category.isPresent()) {
            return new ApiResponse(false, "The given category is not available");
        }

        return new ApiResponse(true);
    }
}
