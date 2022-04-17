package com.xpman.service.impl;

import com.xpman.dao.CategoryDao;
import com.xpman.model.Category;
import com.xpman.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> read() throws GeneralSecurityException, IOException {
       return categoryDao.getAll();
    }

    @Override
    public void write(Category category) throws GeneralSecurityException, IOException {
        categoryDao.create(category);
    }

    @Override
    public Category getCategoryById(Integer id) throws GeneralSecurityException, IOException {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public void updateCategoryById(Category category) throws GeneralSecurityException, IOException {
        categoryDao.updateCategoryById(category);
    }

    @Override
    public void deleteCategoryById(Integer id) throws GeneralSecurityException, IOException {
        categoryDao.deleteCategoryById(id);
    }
}
