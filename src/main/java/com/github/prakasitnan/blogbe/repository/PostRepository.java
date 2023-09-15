package com.github.prakasitnan.blogbe.repository;

import com.github.prakasitnan.blogbe.model.MCategoryEntity;
import com.github.prakasitnan.blogbe.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByPostId(Long postId);
    Page findAll(Specification<PostEntity> filterBy, Pageable pageable);

    @Query(value = "UPDATE PostEntity c SET c.statusId = ?1 WHERE c.postId = ?2")
    MCategoryEntity updateStatusByPostId(Long statusId, Long cateId);
}
