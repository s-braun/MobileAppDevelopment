package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addNewList extends AppCompatActivity {

    public void addUser(View view) {
        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.btnAdd1);

        addBtn.setImageDrawable(getResources().getDrawable(R.drawable.checkbox_on_background));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_list);
    }
}