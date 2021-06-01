package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.Profession;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.ProfessionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionService {
    @Autowired
    private ProfessionRepository professionRepository;

    public ApiResponse validateProfession(Profession professionRequest) {
        if(StringUtils.isEmpty(professionRequest.getName())) {
            return new ApiResponse(false, "The creation request was not valid, please try again.");
        }

        return new ApiResponse(true);
    }

    /**
     * This method checks if a profession exists with the specific id
     *
     * @param professionId Unique ID of the profession which has to be checked
     * @return Boolean If profession exists
     */
    public Boolean doesProfessionExist(Integer professionId) {
        Optional<Profession> professionOptional = professionRepository.findById(professionId);

        return professionOptional.isPresent();
    }
}
