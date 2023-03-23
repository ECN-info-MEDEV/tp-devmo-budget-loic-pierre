package com.patigny_baudet.devmoney.views.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OperationsViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    public OperationsViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
        this.operationDataRepository = operationDataRepository;
        this.categoryDataRepository = categoryDataRepository;
    }

    public LiveData<List<Operation>> getOperationsList() { return operationDataRepository.getOperationsList(); }

    public void deleteOperation(long operation_id) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> operationDataRepository.deleteOperation(operation_id));
    }
}
