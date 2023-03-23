package com.patigny_baudet.devmoney.controllers;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.views.adapters.CategoryAdapter;
import com.patigny_baudet.devmoney.views.injection.Injection;
import com.patigny_baudet.devmoney.views.injection.ViewModelFactory;
import com.patigny_baudet.devmoney.views.viewModels.AddOperationViewModel;
import com.patigny_baudet.devmoney.views.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddOperationActivity extends AppCompatActivity {

    // UI COMPONENTS
    private ImageButton returnButton;
    private EditText nameEditText;
    private EditText dateEditText;
    private EditText amountEditText;
    private EditText descriptionEditText;

    // FOR DATA
    private AddOperationViewModel addOperationViewModel;
    private Button addOperationButton;
    private Spinner dropdown;

    // -----------------
    // LIFE CYCLE
    // -----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);

        // Initialize UI variables
        this.returnButton = findViewById(R.id.activity_add_operation_return_button);
        this.addOperationButton = findViewById(R.id.activity_add_operation_add_button);
        this.nameEditText = findViewById(R.id.activity_add_operation_name_edittext);
        this.dateEditText = findViewById(R.id.activity_add_operation_date_edittext);
        this.amountEditText = findViewById(R.id.activity_add_operation_amount_edittext);
        this.descriptionEditText = findViewById(R.id.activity_add_operation_description_edittext);
        this.dropdown = findViewById(R.id.activity_add_operation_category_dropdown);


        // Setup data
        this.setupViewModel();
        this.getCategoriesList();

        // Setup buttons
        this.returnButton.setOnClickListener(v -> finishActivity());
        this.addOperationButton.setOnClickListener(v -> addOperation());
    }


    // -----------------
    // DATA
    // -----------------

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.addOperationViewModel = new ViewModelProvider(this, viewModelFactory).get(AddOperationViewModel.class);
    }

    private void getCategoriesList() {
        this.addOperationViewModel.getCategoriesList().observe(this, this::updateSpinner);
    }


    //----------------------------
    // ACTIONS
    //----------------------------

    // Finish

    @Override
    public void onBackPressed() {
        this.finishActivity();
    }

    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    // Add operation

    private void addOperation() {
        float amount;
        try {
            amount = Float.parseFloat(amountEditText.getText().toString());
        } catch (NumberFormatException e) {
            amount = 0;
        }
        Operation operation = new Operation(
                nameEditText.getText().toString(),
                descriptionEditText.getText().toString(),
                dateEditText.getText().toString(),
                amount,
                ((Category) dropdown.getSelectedItem()).getId()
        );
        this.addOperationViewModel.createOperation(operation);
        this.finishActivity();
    }


    //----------------------------
    // UI
    //----------------------------

    // Setup spinner

    private void updateSpinner(List<Category> categoriesList) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoriesList);
        dropdown.setAdapter(adapter);
    }
}
