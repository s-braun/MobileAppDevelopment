package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class editItems extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ItemViewModel mItemViewModel;
    private int listID;

    //intake spinner data
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        final Spinner CategorySpinner = (Spinner) findViewById(R.id.CategoriesSpn);
        CategorySpinner.setOnItemSelectedListener(this);
        String category = parent.getItemAtPosition(pos).toString();
        Intent move = new Intent (this, addNewItem.class);
        move.putExtra("item_category", category);
        return;
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        //populate dropdown menu
        Spinner NumSpinner = (Spinner) findViewById(R.id.editSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra("ownerName");
        int itemID = intent.getIntExtra("itemID", 0);
        String itemName = intent.getStringExtra("itemName");
        String itemValue = intent.getStringExtra("itemValue");
        listID = intent.getIntExtra("listID", 0);
        String category = intent.getStringExtra("category");
        String currentUser = intent.getStringExtra("currentUser");

        int convertCategory = 0;
        switch (category){
            case "Produce": convertCategory = 1;
            break;
            case "Dairy": convertCategory = 2;
                break;
            case "Dry Goods": convertCategory = 3;
                break;
            case "Meat": convertCategory = 4;
                break;
            case "Frozen": convertCategory = 5;
                break;
            case "Bakery": convertCategory = 6;
                break;
        }

        //make text boxes intake data
        final EditText editItem = findViewById(R.id.editItem);
        final EditText editAmount = findViewById(R.id.editItemAmount);
        final Spinner editCategory = findViewById(R.id.editSpinner);

        //set listOwner name
        TextView itemOwnerName = (TextView) findViewById(R.id.listownerName4);
        itemOwnerName.setText(String.valueOf(ownerName));

        // Set value to the intent value
        editItem.setText(String.valueOf(itemName));
        editAmount.setText(String.valueOf(itemValue));
        editCategory.setSelection(convertCategory);


        ItemViewModelFactory factory = new ItemViewModelFactory(this.getApplication(), listID);
        mItemViewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);


        //configure edit items button
        final Button ConfirmButton = findViewById(R.id.confirmEditBtn22);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // Get input values
                String editedName = editItem.getText().toString();
                String editedAmount = editAmount.getText().toString();
                String editedCategory = editCategory.getSelectedItem().toString();

                // Update the values in the database!
                mItemViewModel.updateItemValues(itemID, editedName, editedAmount, editedCategory);

                Intent intent = new Intent (editItems.this, listCloseup.class);
                intent.putExtra("listID", listID);
                intent.putExtra("ownerEmail", ownerName);
                intent.putExtra("currentUser", currentUser);
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
                intent.putExtra("currentUser", currentUser);
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
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });
    }

}