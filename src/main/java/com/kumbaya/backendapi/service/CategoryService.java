package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse validateCategory(Category categoryRequest) {
        if(StringUtils.isEmpty(categoryRequest.getName())) {
            return new ApiResponse(false, "The creation request was not valid, please try again.");
        }

        return new ApiResponse(true);
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
