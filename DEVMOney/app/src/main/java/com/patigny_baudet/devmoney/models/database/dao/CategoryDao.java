package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.MapInfo;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Category;

import java.util.List;
import java.util.Map;

/**
 * Class for the DAO of the Category table.
 */
@Dao
public interface CategoryDao {

    /**
     * Gets the list of categories from the database.
     * @return the list of categories
     */
    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategoriesList();

    /**
     * Gets the sum of the amounts of all the operations per category.
     * @return the sum of the amounts of all the operations per category.
     */
    @MapInfo(keyColumn = "category_id", valueColumn = "total")
    @Query("SELECT Category.id AS category_id, SUM(Operation.amount) AS total FROM Category INNER JOIN Operation ON Operation.category_id = Category.id GROUP BY Category.id")
    LiveData<Map<Long, Float>> getExpensesTotalPerCategory();

}
