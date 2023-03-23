package com.patigny_baudet.devmoney.views.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

import java.util.List;
import java.util.Map;

/**
 * Class of the main viewModel
 */
public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final OperationDataRepository operationDataRepository;
    private final CategoryDataRepository categoryDataRepository;

    /**
     * Constructor of the main view model
     * @param operationDataRepository the operation data repository
     * @param categoryDataRepository the category data repository
     */
    public MainViewModel(OperationDataRepository operationDataRepository, CategoryDataRepository categoryDataRepository) {
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
     * Gets the sum of the amounts of all the operations per category.
     * @return the sum of the amounts of all the operations per category.
     */
    public LiveData<Map<Long, Float>> getExpensesTotalPerCategory() {
        return categoryDataRepository.getExpensesTotalPerCategory();
    }

    /**
     * Gets the total sum of expenses
     * @return the sum
     */
    public LiveData<Float> getExpensesTotal() { return operationDataRepository.getExpensesTotal(); }

    /**
     * Gets the total sum of incomes
     * @return the sum
     */
    public LiveData<Float> getIncomesTotal() { return operationDataRepository.getIncomesTotal(); }

    /**
     * Gets the total sum of operations
     * @return the sum
     */
    public LiveData<Float> getOperationsTotal() { return operationDataRepository.getOperationsTotal(); }
}
