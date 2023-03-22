package com.patigny_baudet.devmoney.views.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;
import com.patigny_baudet.devmoney.views.viewModels.MainViewModel;
import com.patigny_baudet.devmoney.views.viewModels.OperationsViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final OperationDataRepository operationDataSource;
    private final CategoryDataRepository categoryDataSource;

    ViewModelFactory(OperationDataRepository operationDataSource, CategoryDataRepository categoryDataSource) {
        this.operationDataSource = operationDataSource;
        this.categoryDataSource = categoryDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OperationsViewModel.class)) {
            return (T) new OperationsViewModel(operationDataSource, categoryDataSource);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(operationDataSource, categoryDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
