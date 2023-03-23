package com.patigny_baudet.devmoney.models.repositories;

import androidx.lifecycle.LiveData;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.database.dao.CategoryDao;

import java.util.List;
import java.util.Map;

public class CategoryDataRepository {

    private final CategoryDao categoryDao;

    public CategoryDataRepository(CategoryDao categoryDao) { this.categoryDao = categoryDao; }

    public LiveData<List<Category>> getCategoriesList() {
        return this.categoryDao.getCategoriesList();
    }

    public LiveData<Map<Long, Float>> getExpensesTotalPerCategory() {
        return this.categoryDao.getExpensesTotalPerCategory();
    }

}
