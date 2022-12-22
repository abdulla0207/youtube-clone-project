package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer>,
        PagingAndSortingRepository<CategoryEntity,Integer> {

}
