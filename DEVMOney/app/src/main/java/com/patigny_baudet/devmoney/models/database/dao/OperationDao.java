package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Operation;

import java.util.List;

@Dao
public interface OperationDao {

    @Query("SELECT * FROM Operation")
    LiveData<List<Operation>> getOperationsList();

    @Query("SELECT SUM(amount) FROM Operation WHERE amount < 0")
    LiveData<Float> getExpensesTotal();

    @Query("SELECT SUM(amount) FROM Operation WHERE amount > 0")
    LiveData<Float> getIncomesTotal();

    @Query("SELECT SUM(amount) FROM Operation")
    LiveData<Float> getOperationsTotal();

    @Insert
    long insertOperation(Operation operation);

}
