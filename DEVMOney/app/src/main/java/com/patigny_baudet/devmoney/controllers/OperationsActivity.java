package com.patigny_baudet.devmoney.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.views.adapters.OperationAdapter;
import com.patigny_baudet.devmoney.views.injection.Injection;
import com.patigny_baudet.devmoney.views.injection.ViewModelFactory;
import com.patigny_baudet.devmoney.views.viewModels.OperationsViewModel;

import java.util.List;

/**
 * Class for the Operation Activity.
 * This activity displays the list of operations
 */
public class OperationsActivity extends AppCompatActivity {

    // FOR DATA
    private boolean dataLoaded;
    private OperationsViewModel operationsViewModel;
    private OperationAdapter operationAdapter;

    // UI COMPONENTS
    private RecyclerView recyclerView;


    // -----------------
    // LIFE CYCLE
    // -----------------

    /**
     * {@inheritDoc}
     *
     * Initializes the UI variables, setup the view model and the buttons
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_DEVMOney);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

        // Initialize UI variables
        this.recyclerView = findViewById(R.id.activity_operations_recycler_view);
        FloatingActionButton addButton = findViewById(R.id.activity_operations_add_button);
        ImageButton returnButton = findViewById(R.id.operation_card_delete);

        // Setup data
        this.setupRecyclerView();
        this.setupViewModel();
        this.dataLoaded = false;

        // Setup buttons
        addButton.setOnClickListener(v -> this.addOperation());
        returnButton.setOnClickListener(v -> finishActivity());
    }

    /**
     * {@inheritDoc}
     *
     * Recovers the operations list from the database
     */
    @Override
    public void onResume() {
        super.onResume();
        if (!this.dataLoaded) {
            this.getOperationsList();
        }
    }

    //----------------------------
    // ACTION
    //----------------------------

    // Add operation

    /**
     * Go to the addOperation activity
     */
    private void addOperation() {
        this.startActivity(new Intent(this, AddOperationActivity.class));
    }


    // Finish

    /**
     * Finishes the activity.
     */
    @Override
    public void onBackPressed() {
        this.finishActivity();
    }

    /**
     * Finishes the activity with a slide transition.
     */
    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }


    // -----------------
    // DATA
    // -----------------

    /**
     * Setup the view model of the activity
     */
    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.operationsViewModel = new ViewModelProvider(this, viewModelFactory).get(OperationsViewModel.class);
    }

    /**
     * Recovers the list of operations from the database
     */
    private void getOperationsList() {
        this.operationsViewModel.getOperationsList().observe(this, this::updateOperationsList);
        this.dataLoaded = true;
    }


    //----------------------------
    // UI
    //----------------------------

    /**
     * Set up the recycler view with the operation adapter
     */
    private void setupRecyclerView() {
        this.operationAdapter = new OperationAdapter();
        this.recyclerView.setAdapter(this.operationAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Update the recycler view with the list of operations
     * @param operationsList the list of operations
     */
    private void updateOperationsList(List<Operation> operationsList) {
        this.operationAdapter.updateOperationsData(operationsList);
    }
}
