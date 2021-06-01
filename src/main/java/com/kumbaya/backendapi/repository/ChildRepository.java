package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Child;
import com.kumbaya.backendapi.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildRepository extends CrudRepository<Child, Integer> {
    List<Child> findByParent(User user);
}
