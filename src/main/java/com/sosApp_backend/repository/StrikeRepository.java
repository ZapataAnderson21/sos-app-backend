package com.sosApp_backend.repository;

import com.sosApp_backend.model.Strike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StrikeRepository extends JpaRepository<Strike, UUID> {

    @Query(value = "SELECT COUNT(*) FROM strike WHERE user_id = :userId", nativeQuery = true)
    int countStrikesByUser(UUID userId);

    @Query(value = "SELECT * FROM strike WHERE user_id = :userId", nativeQuery = true)
    List<Strike> findAllByUser(UUID userId);
}
