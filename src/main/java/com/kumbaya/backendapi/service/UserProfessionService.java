package com.kumbaya.backendapi.service;

import com.kumbaya.backendapi.entity.UserProfession;
import com.kumbaya.backendapi.repository.UserProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfessionService {
    @Autowired
    private UserProfessionRepository userProfessionRepository;

    public Boolean doesUserProfessionExist(Integer userId, Integer professionId) {
        for(UserProfession userProfession : userProfessionRepository.findProfessionByUserId(userId))
            if(userProfession.getProfession().getId().equals(professionId))
                return true;

        return false;
    }

    public UserProfession getUserProfessionByUserIdAndProfessionId(Integer userId, Integer professionId) {
        for(UserProfession userProfession : userProfessionRepository.findProfessionByUserId(userId))
            if(userProfession.getProfession().getId().equals(professionId))
                return userProfession;

        return null;
    }
}
