package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.model.response.ApiResponse;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class CategoryService {
    public ApiResponse validateCategory(Category categoryRequest) {
        if(StringUtils.isEmpty(categoryRequest.getName())) {
            return new ApiResponse(false, "The creation request was not valid, please try again.");
        }

        return new ApiResponse(true);
    }
}
