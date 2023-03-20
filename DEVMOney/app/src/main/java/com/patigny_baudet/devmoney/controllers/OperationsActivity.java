package com.patigny_baudet.devmoney.controllers;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
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


    // -----------------
    // LIFE CYCLE
    // -----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        // Initialize UI variables
        this.recyclerView = findViewById(R.id.activity_operation_recycler_view);
        this.addButton = findViewById(R.id.activity_operation_add_button);

        // Setup data
        this.setupRecyclerView();
        this.setupViewModel();
        this.dataLoaded = false;

        // Setup buttons
        addButton.setOnClickListener(v -> this.startActivity(new Intent(this, AddOperationActivity.class)));
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

    private void addOperation() {

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
        this.operationAdapter.updateJapCharacterData(operationsList);
    }
}
