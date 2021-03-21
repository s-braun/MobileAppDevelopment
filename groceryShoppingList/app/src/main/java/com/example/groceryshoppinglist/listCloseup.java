package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class listCloseup extends AppCompatActivity {

    public void addNewItem(View view){
                Intent newItem = new Intent(this, addNewItem.class);

                startActivity(newItem);
    }

    public void editItem(View view){
        Intent editItem = new Intent(this, editItems.class);

        startActivity(editItem);
    }

    public void backBtn(View view){
        Intent backBtn = new Intent(this, Overview.class);

        startActivity(backBtn);
    }

    /* Item functions until we create the list dynamically */

    public void editItem1(View view){

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item1);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem1);
        String itemValue = value1.getText().toString();

        editItem.putExtra("item1Name", itemName);
        editItem.putExtra("item1Value", itemValue);

        startActivity(editItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");

        TextView ownerName = (TextView) findViewById(R.id.listownerName2);

        ownerName.setText(String.valueOf(name));
    }
}