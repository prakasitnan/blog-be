package com.github.prakasitnan.blogbe.service;

import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.api.v1.model.PostSaveReq;
import com.github.prakasitnan.blogbe.model.MCategoryEntity;
import com.github.prakasitnan.blogbe.model.PostEntity;
import com.github.prakasitnan.blogbe.repository.PostRepository;
import com.github.prakasitnan.blogbe.service.files.FilesService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private final FilesService filesService;

    public PostService(PostRepository postRepository, FilesService filesService) {
        this.postRepository = postRepository;
        this.filesService = filesService;
    }

    public PostEntity save(PostSaveReq postReq, Long userId) {

        PostEntity postEntity = null;
        Optional<PostEntity> postOpt = postRepository.findByPostId(postReq.getPostId());
        if (postOpt.isPresent()) {
            postEntity = postOpt.get();
            postEntity.setPostTitle(postReq.getPostTitle());
            postEntity.setPostDetail(postReq.getPostDetail());
            if (postReq.getImgBannerDelete() != null) {
                if (filesService.deleteFile(postReq.getImgBannerDelete())) {
                    postEntity.setImgBanner(null);
                    postEntity.setImgBannerUrl(null);
                }
            }
            if (postReq.getImgBanner() != null) {
                String fileLocation = filesService.upload(postReq.getImgBanner(), "post");
                postEntity.setImgBanner(fileLocation);
                postEntity.setImgBannerUrl("/api/v1/streaming/p/"+ Base64.getEncoder().encodeToString(fileLocation.getBytes()) +"/dl/y");
            }
            postEntity.setCateId(postReq.getCateId());
            postEntity.setStatusId(postReq.getStatusId());
            postEntity.setCreateBy(userId);
            postEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            postEntity.setUpdateBy(userId);
            postEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        } else {
            postEntity = new PostEntity();
            postEntity.setPostTitle(postReq.getPostTitle());
            postEntity.setPostDetail(postReq.getPostDetail());
            if (postReq.getImgBanner() != null) {
                String fileLocation = filesService.upload(postReq.getImgBanner(), "post");
                postEntity.setImgBanner(fileLocation);
                postEntity.setImgBannerUrl("/api/v1/streaming/p/"+ Base64.getEncoder().encodeToString(fileLocation.getBytes()) +"/dl/y");
            }
            postEntity.setCateId(postReq.getCateId());
            postEntity.setStatusId(postReq.getStatusId());
            postEntity.setCreateBy(userId);
            postEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            postEntity.setUpdateBy(userId);
            postEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        }
        return postRepository.save(postEntity);
    }

    public PostEntity findByPostId(Long postId) {
        return postRepository.findByPostId(postId).orElse(null);
    }

    public MCategoryEntity updateStatus(Long statusId, Long cateId) {
        return postRepository.updateStatusByPostId(statusId, cateId);
    }

    public DataTableResult getDataTable(String keyword, Integer sortIndex, String direction, Integer page, Integer limit, Integer draw) {
        System.out.println("page : "+ page + " limit : "+ limit);
        String sortColName = null;
        switch (sortIndex) {
            case 1:
                sortColName = "createDate";
            break;
            default:
                sortColName = "createDate";
            break;
        }

        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortColName).descending() : Sort.by(sortColName).ascending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page result = postRepository.findAll(filterCate(keyword), pageable);
        return new DataTableResult(result.getTotalPages(), (int)result.getTotalElements(), result.getSize(), result.toList());
    }

    private Specification<PostEntity> filterCate(String keyword) {
        return new Specification<PostEntity>() {
            @Override
            public Predicate toPredicate(Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> conditions = new ArrayList<>();

                if (StringUtils.hasText(keyword)) {
                    String searchKeyword = "%"+keyword+"%";
                    Predicate searchKey = criteriaBuilder.or(criteriaBuilder.like(root.get(""), searchKeyword));
                    conditions.add(searchKey);
                }

                Predicate[] predicates = new Predicate[conditions.size()];
                conditions.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        };
    }
}
