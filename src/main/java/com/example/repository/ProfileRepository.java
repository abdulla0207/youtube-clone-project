package com.example.repository;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {


   ProfileEntity findByEmailAndPassword(String email, String encode);

   Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findAllByRole(ProfileRole roleAdmin);
}
