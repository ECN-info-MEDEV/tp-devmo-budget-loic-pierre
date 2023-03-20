package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Operation;

import java.util.List;

@Dao
public interface OperationDao {

    @Query("SELECT * FROM Operation")
    LiveData<List<Operation>> getOperationsList();

}
