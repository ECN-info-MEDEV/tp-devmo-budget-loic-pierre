package com.patigny_baudet.devmoney.views.viewHolders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Operation;

public class OperationViewHolder extends RecyclerView.ViewHolder {

    // FOR DATA
    private Operation operation;
    private final Context context;

    // FOR UI
    private final TextView operationNameTextview;

    public OperationViewHolder(View characterView, Context context) {
        super(characterView);
        this.context = context;
        this.operationNameTextview = characterView.findViewById(R.id.operation_card_name);
    }

    public void updateWithOperation(Operation operation) {
        this.operationNameTextview.setText(operation.getName());
        this.operation = operation;
    }
}
