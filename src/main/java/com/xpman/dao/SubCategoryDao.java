package com.xpman.dao;

import com.xpman.model.SubCategory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SubCategoryDao {
    List<SubCategory> getAll() throws GeneralSecurityException, IOException;

    List<String> getAllCategorysName() throws GeneralSecurityException, IOException;

    void create(SubCategory subCategory) throws GeneralSecurityException, IOException;

    SubCategory getSubCategoryById(Integer id) throws GeneralSecurityException, IOException;

    void updateSubCategoryById(SubCategory subCategory) throws GeneralSecurityException, IOException;

    void deleteSubCategoryById(Integer id) throws GeneralSecurityException, IOException;
}
