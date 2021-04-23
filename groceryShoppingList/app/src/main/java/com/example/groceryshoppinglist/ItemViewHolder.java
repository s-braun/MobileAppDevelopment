package com.example.groceryshoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView itemItemView;
    private final CheckBox itemValue;

    private ItemViewHolder(View itemView) {
        super(itemView);
        itemItemView = itemView.findViewById(R.id.textView);
        itemValue = itemView.findViewById(R.id.checkBox);
    }

    public void bind(String name, String value) {
        itemItemView.setText(name);
        itemValue.setText(value);
    }

    static ItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }
}
