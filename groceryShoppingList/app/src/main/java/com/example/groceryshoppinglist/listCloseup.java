package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class listCloseup extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;
    private TextView itemItemView;
    private TextView itemCategory;


    public void addNewItem(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");
        int listID = intent.getIntExtra("listID", 0);

        Intent newItem = new Intent(this, addNewItem.class);
        newItem.putExtra("userName", name);
        newItem.putExtra("listID", listID);

        startActivityForResult(newItem, NEW_ITEM_ACTIVITY_REQUEST_CODE);
    }


    public void backBtn(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");

        Intent backBtn = new Intent(this, Overview.class);
        backBtn.putExtra("username", name);

        startActivity(backBtn);
    }


    public void checkBtn(View view){
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");
        int listID = intent.getIntExtra("listID", 0);

        itemItemView = (TextView) findViewById(R.id.textView);
        itemCategory = (TextView) findViewById(R.id.itemCategory);
        CheckBox box = (CheckBox) findViewById(R.id.checkBox);
        ItemViewModelFactory factory = new ItemViewModelFactory(this.getApplication(), listID);
        mItemViewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);

        if(box.isChecked()){
            System.out.println(itemItemView.getText().toString());
            System.out.println(box.getText().toString());
            System.out.println(itemCategory.getText().toString());
            mItemViewModel.updateIsChecked(true, itemItemView.getText().toString(), box.getText().toString(), itemCategory.getText().toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");
        int listID = intent.getIntExtra("listID", 0);

        if(name != null) {
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name));
        } else {
            String name1 = intent.getStringExtra("username");
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name1));
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff(), name);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemViewModelFactory factory = new ItemViewModelFactory(this.getApplication(), listID);
        mItemViewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);


        mItemViewModel.getItemsByListID(listID).observe(this, items -> {
            //Update the cached copy of the items in the adapter.
            adapter.submitList(items);
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //change listID here
        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(0, data.getStringExtra("item"), data.getStringExtra("quantity"), data.getIntExtra("listIDFromAdd", 0), data.getStringExtra("category"), false);
            mItemViewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}