package com.example.repository;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {


    ProfileEntity findByEmailAndPassword(String email, String encode);

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findAllByRole(ProfileRole roleAdmin);

    ProfileEntity findByPassword(String encode);


    @Transactional
    @Modifying
    @Query("update ProfileEntity set password = ?2 where id = ?1")
    int change(Integer id, String newPassword);


    @Transactional
    @Modifying
    @Query("update ProfileEntity set name = ?1, surname = ?2   where id = ?3")
    int updateUserById(String name, String surname, Integer currentUserId);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.attachId = ?1   where p.id = ?2")
    int updateAttach(String attachId, Integer currentUserId);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.email = ?1   where p.id = ?2")
    int updateEmail(String email,Integer currentUserId);
}















