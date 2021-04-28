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
    private int swap = 0;

    public ItemListAdapter(@NonNull DiffUtil.ItemCallback<Item> diffCallback, String name) {
        super(diffCallback);
        ownerName = name;
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
                context.startActivity(intent);

                System.out.println(current.getItemID());
            }
        });


        /*CheckBox check = holder.itemView.findViewById(R.id.checkBox);
        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                Intent intent2 = new Intent("ITEM_ID").putExtra("itemID", current.getItemID());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);

                if(swap == 0){
                    Intent intent1 = new Intent("CHECKED").putExtra("isChecked", true);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
                    swap = 1;
                    check.setChecked(true);
                } else {
                    Intent intent1 = new Intent("CHECKED").putExtra("isChecked", false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
                    swap = 0;
                    check.setChecked(false);
                }

            }
        });*/

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
