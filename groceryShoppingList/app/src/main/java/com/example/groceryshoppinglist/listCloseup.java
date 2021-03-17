package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);
    }
}