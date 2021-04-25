package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class listCloseup extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;


    public void addNewItem(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");

        Intent newItem = new Intent(this, addNewItem.class);
        newItem.putExtra("userName", name);
        int listID = intent.getIntExtra("listID", 0);
        newItem.putExtra("listID", listID);

        startActivityForResult(newItem, NEW_ITEM_ACTIVITY_REQUEST_CODE);
    }

    public void editItem(View view){
        Intent editItem = new Intent(this, editItems.class);

        startActivity(editItem);
    }

    public void backBtn(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");

        Intent backBtn = new Intent(this, Overview.class);
        backBtn.putExtra("username", name);

        startActivity(backBtn);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("ownerEmail");
        int listID = intent.getIntExtra("listID", 0);
        /*
        String itemName = intent.getStringExtra("edited_name");
        String itemValue = intent.getStringExtra("edited_amount");
        int id = intent.getIntExtra("editedID", 0);




        String item5Name = intent.getStringExtra("item_name");
        String amount = intent.getStringExtra("item_amount");
        */



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

        /*mItemViewModel.getAllItems().observe(this, items -> {
            //Update the cached copy of the items in the adapter.
            adapter.submitList(items);
        });*/

        /*mItemViewModel.getItemsByCategory("Produce").observe(this, items -> {
            //Update the cached copy of the items in the adapter.
            adapter.submitList(items);
        });*/

        //change category and listID here
        mItemViewModel.getItemsByListID(listID).observe(this, items -> {
            //Update the cached copy of the items in the adapter.
            adapter.submitList(items);
        });



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //change listID here
        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(0, data.getStringExtra("item"), data.getStringExtra("quantity"), data.getIntExtra("listIDFromAdd", 0), data.getStringExtra("category"));
            mItemViewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}