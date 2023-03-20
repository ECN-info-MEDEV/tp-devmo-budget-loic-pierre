package com.patigny_baudet.devmoney.models.repositories;

import com.patigny_baudet.devmoney.models.database.dao.CategoryDao;

public class CategoryDataRepository {

    private final CategoryDao categoryDao;

    public CategoryDataRepository(CategoryDao categoryDao) { this.categoryDao = categoryDao; }

}
