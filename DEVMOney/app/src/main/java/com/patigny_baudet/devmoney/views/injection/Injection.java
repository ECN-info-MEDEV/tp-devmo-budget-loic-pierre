package com.patigny_baudet.devmoney.views.injection;

import android.content.Context;

import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.models.database.DEVMOneyDatabase;
import com.patigny_baudet.devmoney.models.repositories.CategoryDataRepository;
import com.patigny_baudet.devmoney.models.repositories.OperationDataRepository;

/**
 * Class for the injection from the database
 */
public class Injection {

    /**
     * Provider for the operation data repository
     * @param context the context of the application
     * @return the operation data repository
     */
    private static OperationDataRepository provideOperationDataSource(Context context) {
        DEVMOneyDatabase database = DEVMOneyDatabase.getInstance(context);
        return new OperationDataRepository(database.operationDao());
    }

    /**
     * Provider for the category data repository
     * @param context the context of the application
     * @return the category data repository
     */
    private static CategoryDataRepository provideCategoryDataSource(Context context) {
        DEVMOneyDatabase database = DEVMOneyDatabase.getInstance(context);
        return new CategoryDataRepository(database.categoryDao());
    }

    /**
     * Creates a ViewModelFactory with the operation data repository and the category data repository
     * @param context the context of the application
     * @return the ViewModelFactory
     */
    public static ViewModelFactory provideViewModelFactory(Context context) {
        OperationDataRepository operationDataSource = provideOperationDataSource(context);
        CategoryDataRepository categoryDataSource = provideCategoryDataSource(context);
        return new ViewModelFactory(operationDataSource, categoryDataSource);
    }
}
