package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addNewItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

    public void cancelNewItem(View view){
        Intent cancel = new Intent(this, listCloseup.class);

        startActivity(cancel);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        //populate dropdown menu
        Spinner NumSpinner = (Spinner) findViewById(R.id.CategoriesSpn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumSpinner.setAdapter(adapter);

        String category = getIntent().getStringExtra("item_category");

        //make text boxes intake data
        final EditText itemName = findViewById(R.id.itemName);
        final EditText itemAmount = findViewById(R.id.ItemAmount);
        String item = itemName.getText().toString();
        String amount = itemAmount.getText().toString();

        //configure confirm button
        final Button ConfirmButton = findViewById(R.id.confirmItemBtn);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (addNewItem.this, listCloseup.class);
                intent.putExtra("item_name", item);
                intent.putExtra("item_amount", amount);
                intent.putExtra("item_category", category);
                startActivity(intent);
            }
        });

        //configure cancel button
        final Button CancelButton = findViewById(R.id.cancelButton);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (addNewItem.this, listCloseup.class);
                startActivity(intent);
            }
        });
       // String[] choiceNames={"Categories","Produce","Dairy","Dry Goods","Meat","Frozen", "Bakery"};

        //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,choiceNames);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}