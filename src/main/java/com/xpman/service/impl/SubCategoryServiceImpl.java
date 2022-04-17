package com.xpman.service.impl;

import com.xpman.dao.SubCategoryDao;
import com.xpman.model.SubCategory;
import com.xpman.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryDao subCategoryDao;

    private final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Override
    public List<SubCategory> getAll() throws GeneralSecurityException, IOException {
       return subCategoryDao.getAll();
    }

    @Override
    public List<String> getAllCategorysName() throws GeneralSecurityException, IOException {
        return subCategoryDao.getAllCategorysName();
    }

    @Override
    public void create(SubCategory subCategory) throws GeneralSecurityException, IOException {
        subCategoryDao.create(subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Integer id) throws GeneralSecurityException, IOException {
        return subCategoryDao.getSubCategoryById(id);
    }

    @Override
    public void updateSubCategoryById(SubCategory subCategory) throws GeneralSecurityException, IOException {
        subCategoryDao.updateSubCategoryById(subCategory);
    }

    @Override
    public void deleteSubCategoryById(Integer id) throws GeneralSecurityException, IOException {
        subCategoryDao.deleteSubCategoryById(id);
    }
}
