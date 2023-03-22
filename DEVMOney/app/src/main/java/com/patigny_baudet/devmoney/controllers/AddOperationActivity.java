package com.patigny_baudet.devmoney.controllers;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.patigny_baudet.devmoney.R;

public class AddOperationActivity extends AppCompatActivity {

    // UI COMPONENTS
    protected ImageButton returnButton;

    // -----------------
    // LIFE CYCLE
    // -----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);

        // Initialize UI variables
        this.returnButton = findViewById(R.id.activity_add_operation_return_button);

        this.returnButton.setOnClickListener(v -> finishActivity());
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
}
