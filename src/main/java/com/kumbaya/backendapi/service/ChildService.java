package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import com.kumbaya.backendapi.entity.Child;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChildRepository childRepository;

    public ApiResponse validateChild(Child childRequest) {
        if(StringUtils.isAnyEmpty(childRequest.getFirstname(), childRequest.getLastname(), childRequest.getDob().toString(), childRequest.getGender())) {
            return new ApiResponse(false, "The registration request was not valid, please try again.");
        }

        if(childRequest.getParent() == null) {
            return new ApiResponse(false, "The given parent is not available");
        }

        Optional<User> parent = userRepository.findById(childRequest.getParent().getId());

        if(!parent.isPresent()) {
            return new ApiResponse(false, "The given parent is not available");
        }


        return new ApiResponse(true);
    }

    /**
     * This method checks if a child exists with the specific id
     *
     * @param childId Unique ID of the child who has to be checked
     * @return Boolean If child exists
     */
    public Boolean doesChildExist(Integer childId) {
        Optional<Child> childOptional = childRepository.findById(childId);

        return childOptional.isPresent();
    }
}
