package com.github.prakasitnan.blogbe.repository;

import com.github.prakasitnan.blogbe.model.MCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MCategoryRepository extends JpaRepository<MCategoryEntity, Long> {
    MCategoryEntity findByCateId(Long cateId);

    @Transactional
    @Query(value = "UPDATE MCategoryEntity c SET c.statusId = ?1 WHERE c.cateId = ?2")
    MCategoryEntity updateStatusByCateId(Long statusId, Long cateId);
    Page findAll(Specification<MCategoryEntity> filterBy, Pageable pageable);
}
