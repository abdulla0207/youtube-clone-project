package com.example.repository;

import com.example.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {
    List<EmailHistoryEntity> findAllByEmail(String email);

    @Query(value = "select * from email_history where (select cast(created_date as date)) = ?1", nativeQuery = true)
    List<EmailHistoryEntity> getEmailByDate(LocalDate localDate);
}
