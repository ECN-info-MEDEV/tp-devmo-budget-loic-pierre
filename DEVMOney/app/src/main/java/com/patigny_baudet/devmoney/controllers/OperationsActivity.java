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

public class OperationsActivity extends AppCompatActivity {

    // FOR DATA
    private boolean dataLoaded;
    private OperationsViewModel operationsViewModel;
    private OperationAdapter operationAdapter;

    // UI COMPONENTS
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private ImageButton returnButton;


    // -----------------
    // LIFE CYCLE
    // -----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        // Initialize UI variables
        this.recyclerView = findViewById(R.id.activity_operations_recycler_view);
        this.addButton = findViewById(R.id.activity_operations_add_button);
        this.returnButton = findViewById(R.id.operation_card_delete);

        // Setup data
        this.setupRecyclerView();
        this.setupViewModel();
        this.dataLoaded = false;

        // Setup buttons
        addButton.setOnClickListener(v -> this.addOperation());
        returnButton.setOnClickListener(v -> finishActivity());
    }

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

    private void addOperation() {
        this.startActivity(new Intent(this, AddOperationActivity.class));
    }


    // Finish

    @Override
    public void onBackPressed() {
        this.finishActivity();
    }

    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }


    // -----------------
    // DATA
    // -----------------

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.operationsViewModel = new ViewModelProvider(this, viewModelFactory).get(OperationsViewModel.class);
    }

    private void getOperationsList() {
        this.operationsViewModel.getOperationsList().observe(this, this::updateOperationsList);
        this.dataLoaded = true;
    }


    //----------------------------
    // UI
    //----------------------------

    private void setupRecyclerView() {
        this.operationAdapter = new OperationAdapter();
        this.recyclerView.setAdapter(this.operationAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateOperationsList(List<Operation> operationsList) {
        this.operationAdapter.updateOperationsData(operationsList);
    }
}
