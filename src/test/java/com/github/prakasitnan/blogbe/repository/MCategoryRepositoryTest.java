//package com.github.prakasitnan.blogbe.repository;
//
//import com.github.prakasitnan.blogbe.model.MCategoryEntity;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class MCategoryRepositoryTest{
//
//    @Autowired
//    private MCategoryRepository categoryRepository;
//
//    @Test
//    public void givenCategoryObject_whenSave_thenReturnSavedCategory() {
//
//        MCategoryEntity category = new MCategoryEntity();
//        category.setCateName("test");
//        category.setDescription("test");
//
//        MCategoryEntity savedCategory = categoryRepository.save(category);
//
//        Assertions.assertNotNull(savedCategory);
//        Assertions.assertNotNull(savedCategory.getCateId());
//    }
//
//}