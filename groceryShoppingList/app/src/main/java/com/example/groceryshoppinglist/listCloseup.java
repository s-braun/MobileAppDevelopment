package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class listCloseup extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;



    public void addNewItem(View view){
        //get values from input
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("listCount", 0);
        String name = intent.getStringExtra("userName");

        Intent newItem = new Intent(this, addNewItem.class);
        newItem.putExtra("userName", name);
        newItem.putExtra("listCount", listCount);

        startActivity(newItem);
    }

    public void editItem(View view){
        Intent editItem = new Intent(this, editItems.class);

        startActivity(editItem);
    }

    public void backBtn(View view){
        //get values from input
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("listCount", 0);
        String name = intent.getStringExtra("userName");

        Intent backBtn = new Intent(this, Overview.class);
        backBtn.putExtra("listCount", listCount);
        backBtn.putExtra("username", name);

        startActivity(backBtn);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY));
            mItemViewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        String itemName = intent.getStringExtra("edited_name");
        String itemValue = intent.getStringExtra("edited_amount");
        int id = intent.getIntExtra("editedID", 0);


        String item5Name = intent.getStringExtra("item_name");
        String amount = intent.getStringExtra("item_amount");


        if(name != null) {
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name));
        } else {
            String name1 = intent.getStringExtra("username");
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name1));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel.getAllItems().observe(this, items -> {
            //Update the cached copy of the items in the adapter.
            adapter.submitList(items);
        });




    }
}