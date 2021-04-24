package com.example.groceryshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

public class ListclassListAdapter extends ListAdapter<ListClass, ListViewHolder> {

    public ListclassListAdapter(@NonNull DiffUtil.ItemCallback<ListClass> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListClass current = getItem(position);
        holder.bind(current.getOwnerEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, listCloseup.class);
                intent.putExtra("listID", current.getListID());
                intent.putExtra("ownerEmail", current.getOwnerEmail());
                context.startActivity(intent);

                System.out.println(current.getListID());
            }
        });

    }


    static class ListDiff extends DiffUtil.ItemCallback<ListClass> {

        @Override
        public boolean areItemsTheSame(@NonNull ListClass oldItem, @NonNull ListClass newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListClass oldItem, @NonNull ListClass newItem) {
            return oldItem.getOwnerEmail().equals(newItem.getOwnerEmail());
        }
    }

}
