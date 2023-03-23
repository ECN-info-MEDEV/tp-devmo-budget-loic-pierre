package com.patigny_baudet.devmoney.views.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddOperationViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    public AddOperationViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
        this.operationDataRepository = operationDataRepository;
        this.categoryDataRepository = categoryDataRepository;
    }

    public LiveData<List<Category>> getCategoriesList() {
        return categoryDataRepository.getCategoriesList();
    }

    public void createOperation(Operation operation) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> operationDataRepository.createOperation(operation));
    }
}
