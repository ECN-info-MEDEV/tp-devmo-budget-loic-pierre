package com.patigny_baudet.devmoney.models.repositories;

import androidx.lifecycle.LiveData;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.database.dao.OperationDao;

import java.util.List;

public class OperationDataRepository {

    private final OperationDao operationDao;

    public OperationDataRepository(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    public LiveData<List<Operation>> getOperationsList() {
        return this.operationDao.getOperationsList();
    }

    public LiveData<Float> getExpensesTotal() {
        return operationDao.getExpensesTotal();
    }

    public LiveData<Float> getIncomesTotal() {
        return operationDao.getIncomesTotal();
    }

    public LiveData<Float> getOperationsTotal() {
        return operationDao.getOperationsTotal();
    }

    public void createOperation(Operation operation) {
        operationDao.insertOperation(operation);
    }

}
