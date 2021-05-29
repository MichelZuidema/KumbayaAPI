package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.Profession;
import com.kumbaya.backendapi.model.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProfessionService {
    public ApiResponse validateProfession(Profession professionRequest) {
        if(StringUtils.isEmpty(professionRequest.getName())) {
            return new ApiResponse(false, "The creation request was not valid, please try again.");
        }

        return new ApiResponse(true);
    }
}
