package com.hamzaod.school.repository;

import com.hamzaod.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByEmail(String email);
    boolean existsByEmail(String email);
}
