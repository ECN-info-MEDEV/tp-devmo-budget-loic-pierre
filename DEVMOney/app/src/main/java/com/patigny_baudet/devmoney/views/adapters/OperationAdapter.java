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

/**
 * Class for the Adapter of the operations
 */
public class OperationAdapter extends RecyclerView.Adapter<OperationViewHolder>{

    // FOR DATA
    private List<Operation> operationsList;

    // CONSTRUCTOR
    /**
     * Constructor of the operation adapter
     */
    public OperationAdapter() {
        this.operationsList = new ArrayList<>();
    }

    /**
     * Initializes the viewHolder for the operation
     * @param parent the parent of the viewHolder
     * @param viewType unused
     * @return the new viewHolder
     */
    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.operation_card, parent, false);
        return new OperationViewHolder(view, context);
    }

    /**
     * Updates the operation with its details
     * @param viewHolder the viewHolder
     * @param position the position in the list
     */
    @Override
    public void onBindViewHolder(OperationViewHolder viewHolder, int position) {
        viewHolder.updateWithOperation(this.operationsList.get(position));
    }

    /**
     * Gets the number of operations
     * @return the number of operations
     */
    @Override
    public int getItemCount() {
        return operationsList.size();
    }

    /**
     * Update the operation list when the database content changes
     * @param operationsList the list of operations
     */
    public void updateOperationsData(List<Operation> operationsList) {
        this.operationsList = operationsList;
        notifyDataSetChanged();
    }
}
