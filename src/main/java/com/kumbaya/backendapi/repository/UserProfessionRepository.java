package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Profession;
import com.kumbaya.backendapi.entity.User;
import com.kumbaya.backendapi.entity.UserProfession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserProfessionRepository extends CrudRepository<UserProfession, Integer> {
    List<UserProfession> findProfessionByUserId(Integer userId);
}
