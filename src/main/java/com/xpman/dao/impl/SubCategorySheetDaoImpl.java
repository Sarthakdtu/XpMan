package com.xpman.dao.impl;

import com.xpman.constants.EntitySpreadSheet;
import com.xpman.dao.SubCategoryDao;
import com.xpman.model.SubCategory;
import com.xpman.spreadsheet.GoogleSheetOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubCategorySheetDaoImpl implements SubCategoryDao {

    private final Logger logger = LoggerFactory.getLogger(SubCategorySheetDaoImpl.class);

    @Override
    public List<SubCategory> getAll() throws GeneralSecurityException, IOException {
        List<List<Object>> rows = GoogleSheetOperation.getAll(EntitySpreadSheet.SUBCATEGORY);
        List<SubCategory> subCategorys = new ArrayList<>();
        int count = 2;
        for(List row : rows){
            SubCategory subCategory = SubCategory.builder()
                    .id(count)
                    .name((String) row.get(0))
                    .icon((String) row.get(1))
                    .category((String) row.get(2))
                    .build();
            subCategorys.add(subCategory);
            count += 1;
        }
        return subCategorys;
    }

    @Override
    public List<String> getAllCategorysName() throws GeneralSecurityException, IOException {
        List<List<Object>> rows = GoogleSheetOperation.getByColumnNumber(EntitySpreadSheet.CATEGORY, 1);
        List<String> categoryNames = new ArrayList<>();
        for(List row : rows){
            categoryNames.add((String) row.get(0));
        }
        return categoryNames;
    }

    @Override
    public void create(SubCategory subCategory) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.append(EntitySpreadSheet.SUBCATEGORY, subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Integer id) throws GeneralSecurityException, IOException {
        List<Object> row = GoogleSheetOperation.getByRowNumber(EntitySpreadSheet.SUBCATEGORY, id);
        List<SubCategory> subCategorys = new ArrayList<>();
        return SubCategory.builder()
                .id(id)
                .name((String) row.get(0))
                .icon((String) row.get(1))
                .build();
    }

    @Override
    public void updateSubCategoryById(SubCategory subCategory) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.updateByRowNumber(EntitySpreadSheet.SUBCATEGORY, subCategory, subCategory.getId());
    }

    @Override
    public void deleteSubCategoryById(Integer id) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.deleteByRowNumber(EntitySpreadSheet.SUBCATEGORY, id);
    }
}
