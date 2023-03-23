package com.patigny_baudet.devmoney.models.repositories;

import androidx.lifecycle.LiveData;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.database.dao.CategoryDao;

import java.util.List;
import java.util.Map;

/**
 * Class for the repository of the category table
 */
public class CategoryDataRepository {

    private final CategoryDao categoryDao;

    public CategoryDataRepository(CategoryDao categoryDao) { this.categoryDao = categoryDao; }

    /**
     * Gets the list of categories from the database.
     * @return the list of categories
     */
    public LiveData<List<Category>> getCategoriesList() {
        return this.categoryDao.getCategoriesList();
    }

    /**
     * Gets the sum of the amounts of all the operations per category.
     * @return the sum of the amounts of all the operations per category.
     */
    public LiveData<Map<Long, Float>> getExpensesTotalPerCategory() {
        return this.categoryDao.getExpensesTotalPerCategory();
    }

}
