package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Child;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, Integer> {
}
