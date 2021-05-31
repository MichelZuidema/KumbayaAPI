package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.repository.UserRepository;
import com.kumbaya.backendapi.model.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiResponse validateUser(User userRequest) {
        if (StringUtils.isAnyEmpty(userRequest.getFirstname(), userRequest.getLastname(), userRequest.getDob().toString(), userRequest.getGender(), userRequest.getEmail(), userRequest.getPassword())) {
            return new ApiResponse(false, "The registration request was not valid, please try again.");
        }

        if (EmailValidator.getInstance().isValid(userRequest.getEmail())) {
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                return new ApiResponse(false, "The given email is already in use!");
            } else {
                return new ApiResponse(true);
            }
        } else {
            return new ApiResponse(false, "The given email was not valid, please try again.");
        }
    }

    /**
     * This method checks if a user exists with the specific id
     * @param userId Unique ID of the user who has to be checked
     * @return Boolean If user exists
     */
    public Boolean doesUserExist(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.isPresent();
    }
}
