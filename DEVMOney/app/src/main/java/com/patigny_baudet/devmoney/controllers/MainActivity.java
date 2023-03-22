package com.patigny_baudet.devmoney.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.patigny_baudet.devmoney.R;

public class MainActivity extends AppCompatActivity {

    // UI COMPONENTS
    private Button operationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI variables
        this.operationsButton = findViewById(R.id.activity_main_operations_button);


        this.operationsButton.setOnClickListener(v -> this.startActivity(new Intent(this, OperationsActivity.class)));

    }
}
