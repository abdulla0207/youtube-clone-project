package com.example.repository;

import com.example.entity.TagEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TagRepository extends CrudRepository<TagEntity,Integer> {
    @Transactional
    @Modifying
    @Query("update TagEntity set visible  =false where id = ?1")
    void deteleTag(Integer id);

    @Transactional
    @Modifying
    @Query("update TagEntity set name=?1 where id = ?2")
    void update(String name, Integer id);
}
