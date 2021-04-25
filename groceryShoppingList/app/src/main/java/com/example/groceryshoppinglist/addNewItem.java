package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addNewItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mEditItemView;
    private EditText mEditQuantity;
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
        setContentView(R.layout.activity_add_new_item);

        //populate dropdown menu
        Spinner NumSpinner = (Spinner) findViewById(R.id.CategoriesSpn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumSpinner.setAdapter(adapter);

        String category = getIntent().getStringExtra("item_category");

        // get values from intent!
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);
        listID = intent.getIntExtra("listID", 0);

        // Set the owner name at the top of screen
        TextView listOwnerName = (TextView)findViewById(R.id.listownerName5);
        listOwnerName.setText(name);


        mEditItemView = findViewById(R.id.itemName);
        mEditQuantity = findViewById(R.id.ItemAmount);
        Spinner spinner = (Spinner)findViewById(R.id.CategoriesSpn);

        //configure confirm button
        final Button ConfirmButton = findViewById(R.id.confirmItemBtn);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditItemView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String item = mEditItemView.getText().toString();
                    String quantity = mEditQuantity.getText().toString();
                    String category = spinner.getSelectedItem().toString();
                    replyIntent.putExtra("item", item);
                    replyIntent.putExtra("quantity", quantity);
                    replyIntent.putExtra("category", category);
                    replyIntent.putExtra("listIDFromAdd", listID);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        //configure cancel button
        final Button CancelButton = findViewById(R.id.cancelButton);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (addNewItem.this, listCloseup.class);
                intent.putExtra("userName", name);
                intent.putExtra("listCount", listCount);
                startActivity(intent);
            }
        });

    }
}