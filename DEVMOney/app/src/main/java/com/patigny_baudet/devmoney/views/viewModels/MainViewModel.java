package com.patigny_baudet.devmoney.views.viewModels;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.List;
import java.util.Map;

public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    public MainViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
        this.operationDataRepository = operationDataRepository;
        this.categoryDataRepository = categoryDataRepository;
    }

    public LiveData<List<Category>> getCategoriesList() {
        return categoryDataRepository.getCategoriesList();
    }

    public LiveData<Map<Long, Float>> getExpensesTotalPerCategory() {
        return categoryDataRepository.getExpensesTotalPerCategory();
    }

    public LiveData<Float> getExpensesTotal() { return operationDataRepository.getExpensesTotal(); }

    public LiveData<Float> getIncomesTotal() { return operationDataRepository.getIncomesTotal(); }

    public LiveData<Float> getOperationsTotal() { return operationDataRepository.getOperationsTotal(); }
}
