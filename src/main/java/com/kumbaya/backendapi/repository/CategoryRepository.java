package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Optional<Category> findCategoryByName(String name);
}
