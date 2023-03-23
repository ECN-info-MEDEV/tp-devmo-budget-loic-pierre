package com.patigny_baudet.devmoney.models.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.patigny_baudet.devmoney.models.Operation;

import java.util.List;

/**
 * Class for the DAO of the Operation table.
 */
@Dao
public interface OperationDao {

    /**
     * Gets the list of operations from the database.
     * @return the list of operations
     */
    @Query("SELECT * FROM Operation")
    LiveData<List<Operation>> getOperationsList();

    /**
     * Gets the total sum of expenses
     * @return the sum
     */
    @Query("SELECT SUM(amount) FROM Operation WHERE amount < 0")
    LiveData<Float> getExpensesTotal();

    /**
     * Gets the total sum of incomes
     * @return the sum
     */
    @Query("SELECT SUM(amount) FROM Operation WHERE amount > 0")
    LiveData<Float> getIncomesTotal();

    /**
     * Gets the total sum of operations
     * @return the sum
     */
    @Query("SELECT SUM(amount) FROM Operation")
    LiveData<Float> getOperationsTotal();

    /**
     * Insert a new operation in the database
     * @param operation the operation to insert
     * @return the id of the inserted operation
     */
    @Insert
    long insertOperation(Operation operation);

    /**
     * Deletes an operation in the database
     * @param operation_id the id of the operation to delete
     */
    @Query("DELETE FROM Operation WHERE id = :operation_id")
    void deleteOperation(long operation_id);

}
