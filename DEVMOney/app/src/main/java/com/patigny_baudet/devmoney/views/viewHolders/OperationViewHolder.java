package com.patigny_baudet.devmoney.views.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.views.injection.Injection;
import com.patigny_baudet.devmoney.views.injection.ViewModelFactory;
import com.patigny_baudet.devmoney.views.viewModels.OperationsViewModel;

public class OperationViewHolder extends RecyclerView.ViewHolder {

    // FOR UI
    private final TextView nameTextView;
    private final TextView amountTextView;
    private final View categoryDot;
    private final ImageButton deleteButton;

    // FOR DATA
    private OperationsViewModel operationsViewModel;

    public OperationViewHolder(View view, Context context) {
        super(view);
        this.nameTextView = view.findViewById(R.id.operation_card_name);
        this.amountTextView = view.findViewById(R.id.operation_card_amount);
        this.categoryDot = view.findViewById(R.id.operation_card_category);
        this.deleteButton = view.findViewById(R.id.operation_card_delete);
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(context);
        this.operationsViewModel = new ViewModelProvider((ViewModelStoreOwner) context, viewModelFactory).get(OperationsViewModel.class);
    }

    public void updateWithOperation(Operation operation) {
        this.nameTextView.setText(operation.getName());
        this.amountTextView.setText(String.format("%.2f €", operation.getAmount()));
        this.deleteButton.setOnClickListener(v -> this.operationsViewModel.deleteOperation(operation.getId()));
    }
}
