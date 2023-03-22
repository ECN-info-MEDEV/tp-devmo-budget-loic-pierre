package com.patigny_baudet.devmoney.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.views.viewHolders.OperationViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OperationAdapter extends RecyclerView.Adapter<OperationViewHolder>{

    // FOR DATA
    private List<Operation> operationsList;

    // CONSTRUCTOR
    public OperationAdapter() {
        this.operationsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.operation_card, parent, false);
        return new OperationViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OperationViewHolder viewHolder, int position) {
        viewHolder.updateWithOperation(this.operationsList.get(position));
    }

    @Override
    public int getItemCount() {
        return operationsList.size();
    }

    public void updateJapCharacterData(List<Operation> operationsList) {
        this.operationsList = operationsList;
        notifyDataSetChanged();
    }
}
