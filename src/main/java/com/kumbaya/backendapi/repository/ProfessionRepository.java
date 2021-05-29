package com.kumbaya.backendapi.repository;

import com.kumbaya.backendapi.entity.Profession;
import org.springframework.data.repository.CrudRepository;

public interface ProfessionRepository extends CrudRepository<Profession, Integer> {
}
