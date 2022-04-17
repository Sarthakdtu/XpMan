package com.xpman.dao;

import com.xpman.model.Category;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface CategoryDao {
    List<Category> getAll() throws GeneralSecurityException, IOException;

    void create(Category category) throws GeneralSecurityException, IOException;

    Category getCategoryById(Integer id) throws GeneralSecurityException, IOException;

    void updateCategoryById(Category category) throws GeneralSecurityException, IOException;

    void deleteCategoryById(Integer id) throws GeneralSecurityException, IOException;
}
