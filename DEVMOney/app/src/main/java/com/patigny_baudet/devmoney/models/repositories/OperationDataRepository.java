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

    // --- GET ---

    public LiveData<List<Operation>> getOperationsList() {
        return this.operationDao.getOperationsList();
    }

}
