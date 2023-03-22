package com.patigny_baudet.devmoney.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;
import com.patigny_baudet.devmoney.models.Operation;
import com.patigny_baudet.devmoney.views.viewHolders.CategoryViewHolder;
import com.patigny_baudet.devmoney.views.viewHolders.OperationViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    // FOR DATA
    private List<Category> categoriesList;

    // CONSTRUCTOR
    public CategoryAdapter() {
        this.categoriesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder viewHolder, int position) {
        viewHolder.updateWithCategory(this.categoriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void updateCategoriesData(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
        notifyDataSetChanged();
    }
}
