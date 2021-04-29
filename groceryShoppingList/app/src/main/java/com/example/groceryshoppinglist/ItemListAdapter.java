package com.example.groceryshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ItemListAdapter extends ListAdapter<Item, ItemViewHolder> {

    String ownerName;
    String currentUser;

    public ItemListAdapter(@NonNull DiffUtil.ItemCallback<Item> diffCallback, String name, String currentUser) {
        super(diffCallback);
        ownerName = name;
        this.currentUser = currentUser;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item current = getItem(position);
        holder.bind(current.getItemName(), current.getQuantity(), current.getCategory(), current.getIsChecked());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, editItems.class);
                intent.putExtra("ownerName", ownerName);
                intent.putExtra("itemID", current.getItemID());
                intent.putExtra("itemName", current.getItem());
                intent.putExtra("itemValue", current.getQuantity());
                intent.putExtra("listID", current.getListID());
                intent.putExtra("category", current.getCategory());
                intent.putExtra("currentUser", currentUser);
                context.startActivity(intent);

                System.out.println(current.getItemID());
            }
        });

        CheckBox check = holder.itemView.findViewById(R.id.checkBox);
        check.setOnClickListener( v ->{
            Context context = v.getContext();

            Intent intent = new Intent(context, listCloseup.class);
            intent.putExtra("ownerEmail", ownerName);
            intent.putExtra("itemID", current.getItemID());
            intent.putExtra("listID", current.getListID());
            intent.putExtra("currentUser", currentUser);
            context.startActivity(intent);


            if(check.isChecked()){
                check.setChecked(true);
                current.setIsChecked(true);
            } else {
                check.setChecked(false);
                current.setIsChecked(false);
            }

        });

    }

    static class ItemDiff extends DiffUtil.ItemCallback<Item> {

        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getItemName().equals(newItem.getItemName());
        }
    }
}
