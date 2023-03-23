package com.patigny_baudet.devmoney.models.repositories;

import androidx.lifecycle.LiveData;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.database.dao.OperationDao;

import java.util.List;

/**
 * Class for the repository of the operation table
 */
public class OperationDataRepository {

    private final OperationDao operationDao;

    public OperationDataRepository(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    /**
     * Gets the list of operations from the database.
     * @return the list of operations
     */
    public LiveData<List<Operation>> getOperationsList() {
        return this.operationDao.getOperationsList();
    }

    /**
     * Gets the total sum of expenses
     * @return the sum
     */
    public LiveData<Float> getExpensesTotal() {
        return operationDao.getExpensesTotal();
    }

    /**
     * Gets the total sum of incomes
     * @return the sum
     */
    public LiveData<Float> getIncomesTotal() {
        return operationDao.getIncomesTotal();
    }

    /**
     * Gets the total sum of operations
     * @return the sum
     */
    public LiveData<Float> getOperationsTotal() {
        return operationDao.getOperationsTotal();
    }

    /**
     * Insert a new operation in the database
     * @param operation the operation to insert
     * @return the id of the inserted operation
     */
    public void createOperation(Operation operation) {
        operationDao.insertOperation(operation);
    }

    /**
     * Deletes an operation in the database
     * @param operation_id the id of the operation to delete
     */
    public void deleteOperation(long operation_id) {
        operationDao.deleteOperation(operation_id);
    }

}
