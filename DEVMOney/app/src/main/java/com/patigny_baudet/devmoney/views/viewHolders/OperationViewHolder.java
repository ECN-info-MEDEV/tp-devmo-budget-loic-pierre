package com.patigny_baudet.devmoney.views.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Operation;

public class OperationViewHolder extends RecyclerView.ViewHolder {

    // FOR UI
    private final TextView nameTextView;
    private final TextView amountTextView;
    private final View categoryDot;

    public OperationViewHolder(View view, Context context) {
        super(view);
        this.nameTextView = view.findViewById(R.id.operation_card_name);
        this.amountTextView = view.findViewById(R.id.operation_card_amount);
        this.categoryDot = view.findViewById(R.id.operation_card_category);
    }

    public void updateWithOperation(Operation operation) {
        this.nameTextView.setText(operation.getName());
        this.amountTextView.setText(String.format("%.2f €", operation.getAmount()));
    }
}
