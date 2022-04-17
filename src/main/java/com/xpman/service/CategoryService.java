package com.xpman.service;

import com.xpman.model.Category;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface CategoryService {

    List<Category> read() throws GeneralSecurityException, IOException;

    void write(Category category) throws GeneralSecurityException, IOException ;

    Category getCategoryById(Integer id) throws GeneralSecurityException, IOException ;

    void updateCategoryById(Category category) throws GeneralSecurityException, IOException ;

    void deleteCategoryById(Integer id) throws GeneralSecurityException, IOException ;
}
