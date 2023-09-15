package com.github.prakasitnan.blogbe.service;

import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.api.v1.model.UserReq;
import com.github.prakasitnan.blogbe.exception.UserDuplicateException;
import com.github.prakasitnan.blogbe.model.MCategoryEntity;
import com.github.prakasitnan.blogbe.model.UserEntity;
import com.github.prakasitnan.blogbe.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    public UserEntity save(UserReq userReq) throws UserDuplicateException {
        UserEntity user = null;
        Optional<UserEntity> userIdOpt = userRepository.findByUserId(userReq.getUserId());
        if (userIdOpt.isPresent()) {
            user = userIdOpt.get();
            user.setUsername(userReq.getUsername());
            user.setPassword(passwordEncoder.encode(userReq.getPassword()));
            user.setName(userReq.getName());
            user.setEmail(userReq.getEmail());
            user.setStatusId(userReq.getStatusId());
            user.setUserRole(userReq.getUserRoleId());
            user.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        } else {
            Optional<UserEntity> usernameOpt = userRepository.findByUsername(userReq.getUsername());
            if (usernameOpt.isPresent()) {
                throw new UserDuplicateException("Username "+usernameOpt.get().getUsername()+" is duplicate ");
            }
            user = new UserEntity();
            user.setUsername(userReq.getUsername());
            user.setPassword(passwordEncoder.encode(userReq.getPassword()));
            user.setName(userReq.getName());
            user.setEmail(userReq.getEmail());
            user.setStatusId(userReq.getStatusId());
            user.setUserRole(userReq.getUserRoleId());
            user.setCreateDate(new Timestamp(System.currentTimeMillis()));
            user.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        }

        return userRepository.save(user);
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

        Page result = userRepository.findAll(filterUser(keyword), pageable);
        return new DataTableResult(result.getTotalPages(), (int)result.getTotalElements(), result.getSize() ,result.toList());
    }

    private Specification<UserEntity> filterUser(String keyword) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
