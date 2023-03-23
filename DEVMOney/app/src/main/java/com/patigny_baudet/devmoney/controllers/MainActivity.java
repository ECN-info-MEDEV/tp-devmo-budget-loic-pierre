package com.patigny_baudet.devmoney.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.views.adapters.CategoryAdapter;
import com.patigny_baudet.devmoney.views.injection.Injection;
import com.patigny_baudet.devmoney.views.injection.ViewModelFactory;
import com.patigny_baudet.devmoney.views.viewModels.MainViewModel;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // UI COMPONENTS
    private RecyclerView recyclerView;
    private TextView totalExpensesTextView;
    private TextView totalIncomesTextView;
    private TextView totalOperationsTextView;

    // FOR DATA
    private boolean dataLoaded;
    private MainViewModel mainViewModel;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_DEVMOney);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI variables
        Button operationsButton = findViewById(R.id.activity_main_operations_button);
        this.recyclerView = findViewById(R.id.activity_main_expenses_recycler_view);
        this.totalExpensesTextView = findViewById(R.id.activity_main_total_balance_expenses);
        this.totalIncomesTextView = findViewById(R.id.activity_main_total_balance_income);
        this.totalOperationsTextView = findViewById(R.id.activity_main_total_balance_total_value);

        // Setup data
        this.setupViewModel();
        this.setupRecyclerView();
        this.getCategoriesList();
        this.getExpensesTotal();
        this.getIncomesTotal();
        this.getOperationsTotal();


        // Setup buttons
        operationsButton.setOnClickListener(v -> this.startActivity(new Intent(this, OperationsActivity.class)));

    }

    // -----------------
    // DATA
    // -----------------

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
    }

    private void getCategoriesList() {
        this.mainViewModel.getCategoriesList().observe(this, this::updateCategoriesList);
    }

    private void getExpensesTotal() {
        this.mainViewModel.getExpensesTotal().observe(this, this::updateExpensesTotal);
    }

    private void getIncomesTotal() {
        this.mainViewModel.getIncomesTotal().observe(this, this::updateIncomesTotal);
    }

    private void getOperationsTotal() {
        this.mainViewModel.getOperationsTotal().observe(this, this::updateOperationsTotal);
    }


    //----------------------------
    // UI
    //----------------------------

    private void setupRecyclerView() {
        this.categoryAdapter = new CategoryAdapter();
        this.recyclerView.setAdapter(this.categoryAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateCategoriesList(List<Category> categoriesList) {
        this.mainViewModel.getExpensesTotalPerCategory().observe(this, d -> updateExpensesInCategoriesList(categoriesList, d));
    }

    private void updateExpensesInCategoriesList(List<Category> categoriesList, Map<Long, Float> totalExpensesPerCategory) {
        for (Category category : categoriesList) {
            if (totalExpensesPerCategory.get(category.getId()) > 0) {
                categoriesList.remove(category);
            }
        }
        this.categoryAdapter.updateCategoriesData(categoriesList, totalExpensesPerCategory);
    }

    private void updateExpensesTotal(Float total) {
        this.totalExpensesTextView.setText(String.format("%.2f €", -total));
    }

    private void updateIncomesTotal(Float total) {
        this.totalIncomesTextView.setText(String.format("%.2f €", total));
    }

    private void updateOperationsTotal(Float total) {
        this.totalOperationsTextView.setText(String.format("%.2f €", total));
    }

}
