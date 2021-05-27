package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
