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

/**
 * Class for the AddOperation Activity.
 * This activity allows to create new operations and add those to the database
 */
public class AddOperationActivity extends AppCompatActivity {

    // UI COMPONENTS
    private EditText nameEditText;
    private EditText dateEditText;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private Spinner dropdown;

    // FOR DATA
    private AddOperationViewModel addOperationViewModel;


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
        setContentView(R.layout.activity_add_operation);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);

        // Initialize UI variables
        ImageButton returnButton = findViewById(R.id.activity_add_operation_return_button);
        Button addOperationButton = findViewById(R.id.activity_add_operation_add_button);
        this.nameEditText = findViewById(R.id.activity_add_operation_name_edittext);
        this.dateEditText = findViewById(R.id.activity_add_operation_date_edittext);
        this.amountEditText = findViewById(R.id.activity_add_operation_amount_edittext);
        this.descriptionEditText = findViewById(R.id.activity_add_operation_description_edittext);
        this.dropdown = findViewById(R.id.activity_add_operation_category_dropdown);


        // Setup data
        this.setupViewModel();
        this.getCategoriesList();

        // Setup buttons
        returnButton.setOnClickListener(v -> finishActivity());
        addOperationButton.setOnClickListener(v -> addOperation());
    }


    // -----------------
    // DATA
    // -----------------

    /**
     * Setup the view model of the activity
     */
    private void setupViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.addOperationViewModel = new ViewModelProvider(this, viewModelFactory).get(AddOperationViewModel.class);
    }

    /**
     * Recovers the list of categories from the database
     */
    private void getCategoriesList() {
        this.addOperationViewModel.getCategoriesList().observe(this, this::updateSpinner);
    }


    //----------------------------
    // ACTIONS
    //----------------------------

    // Finish

    /**
     * Finishes the activity
     */
    @Override
    public void onBackPressed() {
        this.finishActivity();
    }

    /**
     * Finishes the activity with a slide transition
     */
    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    // Add operation

    /**
     * Add the new operation to the database.
     */
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

    /**
     * Update the dropdown menu of the spinner with the list of categories.
     * @param categoriesList the list of categories
     */
    private void updateSpinner(List<Category> categoriesList) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoriesList);
        dropdown.setAdapter(adapter);
    }
}
