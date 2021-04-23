package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editItems extends AppCompatActivity {

    private ItemViewModel mItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        /*//get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        String itemName = intent.getStringExtra("itemName");
        String itemValue = intent.getStringExtra("itemValue");
        int id = intent.getIntExtra("id", 0);
        int listCount = intent.getIntExtra("listCount", 0);

        //set listOwner name
        TextView ownerName = (TextView) findViewById(R.id.listownerName4);
        ownerName.setText(String.valueOf(name));*/

        Intent intent = getIntent();
        int itemID = intent.getIntExtra("itemID", 0);

        String itemName = intent.getStringExtra("itemName");
        String itemValue = intent.getStringExtra("itemValue");
        int listID = intent.getIntExtra("listID", 0);
        String category = intent.getStringExtra("category");

        ItemViewModelFactory factory = new ItemViewModelFactory(this.getApplication(), category, listID);
        mItemViewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);


        //make text boxes intake data
        final EditText editItem = findViewById(R.id.editItem);
        final EditText editAmount = findViewById(R.id.editItemAmount);
        editItem.setText(String.valueOf(itemName));
        editAmount.setText(String.valueOf(itemValue));

        //configure edit items button
        final Button ConfirmButton = findViewById(R.id.confirmEditBtn22);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String editedName = editItem.getText().toString();
                String editedAmount = editAmount.getText().toString();

                System.out.println("ID in edit: " + itemID);

                Item item = new Item(itemID, editedName, editedAmount, listID, category);

                mItemViewModel.updateItem(item);


                Intent intent = new Intent (editItems.this, listCloseup.class);
                /*intent.putExtra("edited_name", editedName);
                intent.putExtra("edited_amount", editedAmount);
                intent.putExtra("editedID", id);
                intent.putExtra("userName", name);
                intent.putExtra("listCount", listCount);*/
                startActivity(intent);
            }
        });

        //configure cancel button
        final Button CancelButton = findViewById(R.id.cancelButton2);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (editItems.this, listCloseup.class);
                startActivity(intent);
            }
        });
    }
}