package com.patigny_baudet.devmoney.views.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;
import com.patigny_baudet.devmoney.views.viewModels.AddOperationViewModel;
import com.patigny_baudet.devmoney.views.viewModels.MainViewModel;
import com.patigny_baudet.devmoney.views.viewModels.OperationsViewModel;

/**
 * Class for the ViewModelFactory
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final OperationDataRepository operationDataSource;
    private final CategoryDataRepository categoryDataSource;

    /**
     * Constructor of the view model factory
     * @param operationDataSource the operation data repository
     * @param categoryDataSource the category data repository
     */
    ViewModelFactory(OperationDataRepository operationDataSource, CategoryDataRepository categoryDataSource) {
        this.operationDataSource = operationDataSource;
        this.categoryDataSource = categoryDataSource;
    }

    /**
     * Creates the right view model depending on the model class
     * @param modelClass the view model class
     * @param <T>
     * @return the corresponding view model
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OperationsViewModel.class)) {
            return (T) new OperationsViewModel(operationDataSource, categoryDataSource);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(operationDataSource, categoryDataSource);
        } else if (modelClass.isAssignableFrom(AddOperationViewModel.class)) {
            return (T) new AddOperationViewModel(operationDataSource, categoryDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
