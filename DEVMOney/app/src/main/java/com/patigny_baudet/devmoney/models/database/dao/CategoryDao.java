package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.MapInfo;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Category;

import java.util.List;
import java.util.Map;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategoriesList();

    @MapInfo(keyColumn = "category_id", valueColumn = "total")
    @Query("SELECT Category.id AS category_id, SUM(Operation.amount) AS total FROM Category INNER JOIN Operation ON Operation.category_id = Category.id GROUP BY Category.id")
    LiveData<Map<Long, Float>> getExpensesTotalPerCategory();

}
