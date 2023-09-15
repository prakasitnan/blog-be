package com.github.prakasitnan.blogbe.service;

import com.github.prakasitnan.blogbe.api.v1.model.CateSaveReq;
import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.model.MCategoryEntity;
import com.github.prakasitnan.blogbe.repository.MCategoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class CategoryService {

    private final MCategoryRepository categoryRepository;

    public CategoryService(MCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public MCategoryEntity save(CateSaveReq cate, Long userId) {
        MCategoryEntity category = new MCategoryEntity();
        category.setCateName(cate.getCateName());
        category.setDescription(cate.getDescription());
        category.setStatusId(1L);
        category.setCreateBy(userId);
        category.setCreateDate(new Timestamp(System.currentTimeMillis()));
        category.setUpdateBy(userId);
        category.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }

    public MCategoryEntity findByCateId(Long cateId) {
        return categoryRepository.findByCateId(cateId);
    }

    public MCategoryEntity updateStatus(Long statusId, Long cateId) {
        return categoryRepository.updateStatusByCateId(statusId, cateId);
    }

    public DataTableResult getDataTable(String keyword, Integer sortIndex, String direction, Integer page, Integer limit, Integer draw) {
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

        Page result = categoryRepository.findAll(filterCate(keyword), pageable);
        return new DataTableResult(result.getTotalPages(), (int)result.getTotalElements(), result.getSize(),result.toList());
    }

    private Specification<MCategoryEntity> filterCate(String keyword) {
        return new Specification<MCategoryEntity>() {
            @Override
            public Predicate toPredicate(Root<MCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
