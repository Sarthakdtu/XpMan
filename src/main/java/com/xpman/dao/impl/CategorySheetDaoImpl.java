package com.xpman.dao.impl;

import com.xpman.constants.EntitySpreadSheet;
import com.xpman.dao.CategoryDao;
import com.xpman.model.Category;
import com.xpman.spreadsheet.GoogleSheetOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySheetDaoImpl implements CategoryDao {

    private final Logger logger = LoggerFactory.getLogger(CategorySheetDaoImpl.class);

    @Override
    public List<Category> getAll() throws GeneralSecurityException, IOException {
        List<List<Object>> rows = GoogleSheetOperation.getAll(EntitySpreadSheet.CATEGORY);
        List<Category> categorys = new ArrayList<>();
        int count = 2;
        for(List row : rows){
            Category category = Category.builder()
                    .id(count)
                    .name((String) row.get(0))
                    .icon((String) row.get(1))
                    .build();
            categorys.add(category);
            count += 1;
        }
        return categorys;
    }

    @Override
    public void create(Category category) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.append(EntitySpreadSheet.CATEGORY, category);
    }

    @Override
    public Category getCategoryById(Integer id) throws GeneralSecurityException, IOException {
        List<Object> row = GoogleSheetOperation.getByRowNumber(EntitySpreadSheet.CATEGORY, id);
        List<Category> categorys = new ArrayList<>();
        return Category.builder()
                .id(id)
                .name((String) row.get(0))
                .icon((String) row.get(1))
                .build();
    }

    @Override
    public void updateCategoryById(Category category) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.updateByRowNumber(EntitySpreadSheet.CATEGORY, category, category.getId());
    }

    @Override
    public void deleteCategoryById(Integer id) throws GeneralSecurityException, IOException {
        GoogleSheetOperation.deleteByRowNumber(EntitySpreadSheet.CATEGORY, id);
    }
}
