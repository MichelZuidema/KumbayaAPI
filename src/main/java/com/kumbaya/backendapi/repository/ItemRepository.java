package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
