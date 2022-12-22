package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>,
        PagingAndSortingRepository<CategoryEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update CategoryEntity set visible  =false where id = ?1")
    void deteleCategory(Integer id);

    @Transactional
    @Modifying
    @Query("update CategoryEntity set name=?1 where id = ?2")
    void update(String name, Integer id);
}
