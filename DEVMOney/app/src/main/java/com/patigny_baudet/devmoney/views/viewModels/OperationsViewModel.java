package com.patigny_baudet.devmoney.views.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class of the operations viewModel
 */
public class OperationsViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    /**
     * Constructor of the operations view model
     * @param operationDataRepository the operation data repository
     * @param categoryDataRepository the category data repository
     */
    public OperationsViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
        this.operationDataRepository = operationDataRepository;
        this.categoryDataRepository = categoryDataRepository;
    }

    /**
     * Gets the list of operations from the database.
     * @return the list of operations
     */
    public LiveData<List<Operation>> getOperationsList() { return operationDataRepository.getOperationsList(); }

    /**
     * Deletes an operation in the database
     * @param operation_id the id of the operation to delete
     */
    public void deleteOperation(long operation_id) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> operationDataRepository.deleteOperation(operation_id));
    }
}
