//package com.github.prakasitnan.blogbe.service;
//
//import com.github.prakasitnan.blogbe.api.v1.model.CateSaveReq;
//import com.github.prakasitnan.blogbe.model.MCategoryEntity;
//import com.github.prakasitnan.blogbe.repository.MCategoryRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//
//import java.util.Optional;
//
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class CategoryServiceTest {
//    @Mock
//    private MCategoryRepository categoryRepository;
//
//    @InjectMocks
//    private CategoryService categoryService;
//
//    private MCategoryEntity mCategoryEntity;
//    private CateSaveReq cateSaveReq;
//
//    @BeforeEach
//    public void setup() {
//        mCategoryEntity = new MCategoryEntity();
//        mCategoryEntity.setCateName("Test1");
//        mCategoryEntity.setDescription("Test1");
//        mCategoryEntity.setStatusId(1L);
//
//        cateSaveReq = new CateSaveReq();
//        cateSaveReq.setCateName("Test1");
//        cateSaveReq.setDescription("Test1");
//        cateSaveReq.setStatusId(1L);
//    }
//
//    @DisplayName("Test Save Category")
//    @Test
//    public void test() {
//        // given
//        given(categoryRepository.findByCateId(mCategoryEntity.getCateId()))
//                .willReturn(null);
//        given(categoryRepository.save(mCategoryEntity))
//                .willReturn(mCategoryEntity);
//
//        System.out.println(categoryRepository);
//        System.out.println(categoryService);
//
//        // when
//        MCategoryEntity saveCategory = categoryService.save(cateSaveReq, 1L);
//        System.out.println(saveCategory);
//
//        // then
//        assertThat(saveCategory).isNotNull();
//    }
//
//}