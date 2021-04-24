package com.example.groceryshoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private final TextView listItemView;

    private ListViewHolder(View listView){
        super(listView);
        listItemView = listView.findViewById(R.id.listName);
    }

    public void bind(String ownerEmail) {
        listItemView.setText("Grocery list of " + ownerEmail);
    }

    static ListViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list, parent, false);
        return new ListViewHolder(view);
    }
}
