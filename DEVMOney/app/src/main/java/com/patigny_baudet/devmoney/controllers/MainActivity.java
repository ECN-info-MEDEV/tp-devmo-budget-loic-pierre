package com.patigny_baudet.devmoney.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.views.adapters.CategoryAdapter;
import com.patigny_baudet.devmoney.views.injection.Injection;
import com.patigny_baudet.devmoney.views.injection.ViewModelFactory;
import com.patigny_baudet.devmoney.views.viewModels.MainViewModel;

import java.util.List;
import java.util.Map;

/**
 * Class for the Main Activity.
 * This activity displays a dashboard.
 */
public class MainActivity extends AppCompatActivity {

    // UI COMPONENTS
    private RecyclerView recyclerView;
    private TextView totalExpensesTextView;
    private TextView totalIncomesTextView;
    private TextView totalOperationsTextView;

    // FOR DATA
    private MainViewModel mainViewModel;
    private CategoryAdapter categoryAdapter;

    /**
     * {@inheritDoc}
     *
     * Initializes the UI variables, setup the view model and the buttons
     */
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

    /**
     * Setup the view model of the activity
     */
    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
    }

    /**
     * Recovers the list of categories from the database
     */
    private void getCategoriesList() {
        this.mainViewModel.getCategoriesList().observe(this, this::updateCategoriesList);
    }

    /**
     * Recovers the sum of all the expenses from the database
     */
    private void getExpensesTotal() {
        this.mainViewModel.getExpensesTotal().observe(this, this::updateExpensesTotal);
    }

    /**
     * Recovers the sum of all the incomes from the database
     */
    private void getIncomesTotal() {
        this.mainViewModel.getIncomesTotal().observe(this, this::updateIncomesTotal);
    }

    /**
     * Recovers the total sum of all the operations from the database
     */
    private void getOperationsTotal() {
        this.mainViewModel.getOperationsTotal().observe(this, this::updateOperationsTotal);
    }


    //----------------------------
    // UI
    //----------------------------

    /**
     * Set up the recycler view with the category adapter
     */
    private void setupRecyclerView() {
        this.categoryAdapter = new CategoryAdapter();
        this.recyclerView.setAdapter(this.categoryAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Update the recycler view with the list of categories
     * @param categoriesList the list of categories
     */
    private void updateCategoriesList(List<Category> categoriesList) {
        this.mainViewModel.getExpensesTotalPerCategory().observe(this, d -> updateExpensesInCategoriesList(categoriesList, d));
    }

    /**
     * Update the recycler view with the amount for each category
     * @param categoriesList the list of categories
     * @param totalExpensesPerCategory the total expenses per category
     */
    private void updateExpensesInCategoriesList(List<Category> categoriesList, Map<Long, Float> totalExpensesPerCategory) {
        for (Category category : categoriesList) {
            if (totalExpensesPerCategory.get(category.getId()) > 0) {
                categoriesList.remove(category);
            }
        }
        this.categoryAdapter.updateCategoriesData(categoriesList, totalExpensesPerCategory);
    }

    /**
     * Update the expenses total textview with the expenses total
     * @param total the expenses total
     */
    private void updateExpensesTotal(Float total) {
        this.totalExpensesTextView.setText(String.format("%.2f €", -total));
    }

    /**
     * Update the incomes total textview with the incomes total
     * @param total the incomes total
     */
    private void updateIncomesTotal(Float total) {
        this.totalIncomesTextView.setText(String.format("%.2f €", total));
    }

    /**
     * Update the operations total textview with the operations total
     * @param total the operations total
     */
    private void updateOperationsTotal(Float total) {
        this.totalOperationsTextView.setText(String.format("%.2f €", total));
    }

}
