package com.patigny_baudet.devmoney.views.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class of the addOperation viewModel
 */
public class AddOperationViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    /**
     * Constructor of the addOperation view model
     * @param operationDataRepository the operation data repository
     * @param categoryDataRepository the category data repository
     */
    public AddOperationViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
        this.operationDataRepository = operationDataRepository;
        this.categoryDataRepository = categoryDataRepository;
    }

    /**
     * Gets the list of categories from the database.
     * @return the list of categories
     */
    public LiveData<List<Category>> getCategoriesList() {
        return categoryDataRepository.getCategoriesList();
    }

    /**
     * Creates a new operation in the database
     * @param operation the operation to create
     */
    public void createOperation(Operation operation) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> operationDataRepository.createOperation(operation));
    }
}
