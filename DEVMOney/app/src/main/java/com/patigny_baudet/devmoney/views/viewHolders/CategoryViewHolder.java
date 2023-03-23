package com.patigny_baudet.devmoney.views.viewHolders;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;

/**
 * Class of the Category View Holder
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    // FOR UI
    private final TextView nameTextView;
    private final TextView amountTextView;
    private final View categoryDot;

    /**
     * Constructor of the category view holder. Initializes the UI variables.
     * @param view the view corresponding to the view holder
     */
    public CategoryViewHolder(View view) {
        super(view);
        this.nameTextView = view.findViewById(R.id.category_card_name);
        this.amountTextView = view.findViewById(R.id.category_card_amount);
        this.categoryDot = view.findViewById(R.id.category_card_category);
    }

    /**
     * Update the view content with the category details.
     * @param category the category to show
     * @param totalExpenses the total expenses for this category
     */
    public void updateWithCategory(Category category, Float totalExpenses) {
        this.nameTextView.setText(category.getName());
        this.amountTextView.setText(String.format("%.2f €", totalExpenses));
        this.categoryDot.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(category.getColor())));
    }
}
