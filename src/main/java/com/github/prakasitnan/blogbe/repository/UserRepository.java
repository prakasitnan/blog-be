package com.github.prakasitnan.blogbe.repository;

import com.github.prakasitnan.blogbe.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(Long userId);
    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update UserEntity u set u.lastLogin = current_timestamp where u.userId = ?1")
    void updateLastActivity(Long userId);

    Page findAll(Specification<UserEntity> filterBy, Pageable pageable);
}
