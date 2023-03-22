package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategoriesList();

}
