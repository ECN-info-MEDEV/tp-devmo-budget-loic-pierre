package com.patigny_baudet.devmoney.views.viewHolders;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.patigny_baudet.devmoney.R;
import com.patigny_baudet.devmoney.models.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    // FOR UI
    private final TextView nameTextView;
    private final TextView amountTextView;
    private final View categoryDot;

    public CategoryViewHolder(View view) {
        super(view);
        this.nameTextView = view.findViewById(R.id.category_card_name);
        this.amountTextView = view.findViewById(R.id.category_card_amount);
        this.categoryDot = view.findViewById(R.id.category_card_category);
    }

    public void updateWithCategory(Category category, Float totalExpenses) {
        this.nameTextView.setText(category.getName());
        this.amountTextView.setText(String.format("%.2f €", totalExpenses));
        this.categoryDot.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(category.getColor())));
    }
}
