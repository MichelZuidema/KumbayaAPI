package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Category;
import com.kumbaya.backendapi.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByCategory(Category category);
}
