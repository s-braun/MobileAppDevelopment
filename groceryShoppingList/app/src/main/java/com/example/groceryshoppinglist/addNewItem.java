package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class addNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        String[] choiceNames={"Categories","Produce","Dairy","Dry Goods","Meat","Frozen", "Bakery"};

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,choiceNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}