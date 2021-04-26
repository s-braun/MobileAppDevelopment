package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class editItems extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private int listID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra("ownerName");
        int itemID = intent.getIntExtra("itemID", 0);
        String itemName = intent.getStringExtra("itemName");
        String itemValue = intent.getStringExtra("itemValue");
        listID = intent.getIntExtra("listID", 0);
        String category = intent.getStringExtra("category");

        //make text boxes intake data
        final EditText editItem = findViewById(R.id.editItem);
        final EditText editAmount = findViewById(R.id.editItemAmount);
        final EditText editCategory = findViewById(R.id.editItemCategory);

        //set listOwner name
        TextView itemOwnerName = (TextView) findViewById(R.id.listownerName4);
        itemOwnerName.setText(String.valueOf(ownerName));

        // Set value to the intent value
        editItem.setText(String.valueOf(itemName));
        editAmount.setText(String.valueOf(itemValue));
        editCategory.setText(String.valueOf(category));


        ItemViewModelFactory factory = new ItemViewModelFactory(this.getApplication(), listID);
        mItemViewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);


        //configure edit items button
        final Button ConfirmButton = findViewById(R.id.confirmEditBtn22);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // Get input values
                String editedName = editItem.getText().toString();
                String editedAmount = editAmount.getText().toString();
                String editedCategory = editCategory.getText().toString();

                // Update the values in the database!
                mItemViewModel.updateItemValues(itemID, editedName, editedAmount, editedCategory);

                Intent intent = new Intent (editItems.this, listCloseup.class);
                intent.putExtra("listID", listID);
                intent.putExtra("ownerEmail", ownerName);
                startActivity(intent);
            }
        });

        //configure delete item button
        final Button deleteButton = findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // Update the values in the database!
                mItemViewModel.deleteItem(itemID);

                Intent intent = new Intent (editItems.this, listCloseup.class);
                intent.putExtra("listID", listID);
                intent.putExtra("ownerEmail", ownerName);
                startActivity(intent);
            }
        });

        //configure cancel button
        final Button CancelButton = findViewById(R.id.cancelButton2);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (editItems.this, listCloseup.class);
                intent.putExtra("listID", listID);
                intent.putExtra("ownerEmail", ownerName);
                startActivity(intent);
            }
        });
    }

}