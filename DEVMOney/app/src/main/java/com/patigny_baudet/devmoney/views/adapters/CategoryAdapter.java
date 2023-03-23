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
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for the Adapter of the categories
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    // FOR DATA
    private List<Category> categoriesList;
    private Map<Long, Float> expensesPerCategory;

    // CONSTRUCTOR

    /**
     * Constructor of the category adapter
     */
    public CategoryAdapter() {
        this.categoriesList = new ArrayList<>();
        this.expensesPerCategory = new HashMap<>();
    }

    /**
     * Initializes the viewHolder for the category
     * @param parent the parent of the viewHolder
     * @param viewType unused
     * @return the new viewHolder
     */
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(view);
    }

    /**
     * Updates the category with its details
     * @param viewHolder the viewHolder
     * @param position the position in the list
     */
    @Override
    public void onBindViewHolder(CategoryViewHolder viewHolder, int position) {
        Category category = this.categoriesList.get(position);
        viewHolder.updateWithCategory(category, expensesPerCategory.get(category.getId()));
    }

    /**
     * Gets the number of categories
     * @return the number of categories
     */
    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    /**
     * Update the category list when the database content changes
     * @param categoriesList the list of categories
     * @param expensesPerCategory the list of expenses per category
     */
    public void updateCategoriesData(List<Category> categoriesList, Map<Long, Float> expensesPerCategory) {
        this.categoriesList = categoriesList;
        this.expensesPerCategory = expensesPerCategory;
        notifyDataSetChanged();
    }
}
