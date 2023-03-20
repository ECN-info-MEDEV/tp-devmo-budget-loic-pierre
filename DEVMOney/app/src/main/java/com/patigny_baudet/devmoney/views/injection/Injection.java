package com.patigny_baudet.devmoney.views.injection;

import android.content.Context;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.database.DEVMOneyDatabase;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

public class Injection {

    private static OperationDataRepository provideOperationDataSource(Context context) {
        DEVMOneyDatabase database = DEVMOneyDatabase.getInstance(context);
        return new OperationDataRepository(database.operationDao());
    }

    private static CategoryDataRepository provideCategoryDataSource(Context context) {
        DEVMOneyDatabase database = DEVMOneyDatabase.getInstance(context);
        return new CategoryDataRepository(database.categoryDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        OperationDataRepository operationDataSource = provideOperationDataSource(context);
        CategoryDataRepository categoryDataSource = provideCategoryDataSource(context);
        return new ViewModelFactory(operationDataSource, categoryDataSource);
    }
}
